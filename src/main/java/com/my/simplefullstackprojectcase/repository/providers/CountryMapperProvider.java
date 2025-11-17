package com.my.simplefullstackprojectcase.repository.providers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * Created by Oulis Evangelos on 11/17/25.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CountryMapperProvider {

	public static String buildSearchQueryCountriesStatsAdvanced(final CountryAdvancedFilter f) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT CO.name co_name, R.name r_name, C.name c_name, S.year s_year, ");
		sql.append("S.population s_population, S.gdp s_gdp FROM countries C ");
		commonBuildSearchQueryCountriesStatsAdvanced(f, sql);

		sql.append(" ORDER BY ${orderBy} ");
		sql.append(" LIMIT ${limit} OFFSET ${offset} ");

		return sql.toString();
	}

	public static String buildCountSearchQueryCountriesStatsAdvanced(final CountryAdvancedFilter f) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(1) FROM countries C ");
		commonBuildSearchQueryCountriesStatsAdvanced(f, sql);

		return sql.toString();
	}

	private static void commonBuildSearchQueryCountriesStatsAdvanced(final CountryAdvancedFilter f, final StringBuilder sql) {
		sql.append("LEFT JOIN regions R ON C.region_id = R.region_id ");
		sql.append("LEFT JOIN continents CO ON CO.continent_id = R.continent_id ");
		sql.append("LEFT JOIN country_stats S ON C.country_id = S.country_id ");
		sql.append("WHERE 1=1 ");

		if (f.getYearFrom() != null && f.getYearFrom() > 0) {
			sql.append(" AND S.year >= ${yearFrom} ");
		}

		if (f.getYearTo() != null && f.getYearTo() > 0) {
			sql.append(" AND S.year <= ${yearTo} ");
		}

		if (f.getRegion() != null && StringUtils.hasText(f.getRegion())) {
			sql.append(" AND C.region_id <= ${region} ");
		}
	}
}
