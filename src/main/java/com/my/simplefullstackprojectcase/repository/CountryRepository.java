package com.my.simplefullstackprojectcase.repository;

import com.my.simplefullstackprojectcase.apiclient.model.CountryInfo;
import com.my.simplefullstackprojectcase.apiclient.model.CountryInfoAdvanced;
import com.my.simplefullstackprojectcase.entity.CountryEntity;
import com.my.simplefullstackprojectcase.repository.providers.CountryAdvancedFilter;
import com.my.simplefullstackprojectcase.repository.providers.CountryMapperProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by Oulis Evangelos on 11/16/25.
 */
@Mapper
public interface CountryRepository {

	@Select("SELECT * FROM countries ORDER BY ${orderBy}")
	List<CountryEntity> findAll(@Param("orderBy") String orderBy);

	@Select("SELECT C.*, S.* FROM country_stats C LEFT JOIN countries S ON C.country_id = S.country_id " +
			"ORDER BY ${orderBy}")
	@Results(
			id = "allCountriesStatsMap",
			value = {
					@Result(property = "name", column = "name", id = false),
					@Result(property = "countryCode", column = "country_code3", id = false),
					@Result(property = "year", column = "year", id = false),
					@Result(property = "population", column = "population", id = false),
					@Result(property = "gdp", column = "gdp", id = false)
			}
	)
	List<CountryInfo> findAllStats(@Param("orderBy") String orderBy);

	@SelectProvider(type = CountryMapperProvider.class, method = "buildSearchQueryCountriesStatsAdvanced")
	@Results(
			id = "allCountriesStatsAdvanced",
			value = {
					@Result(property = "continentName", column = "co_name", id = false),
					@Result(property = "regionName", column = "r_name", id = false),
					@Result(property = "countryName", column = "c_name", id = false),
					@Result(property = "year", column = "s_year", id = false),
					@Result(property = "population", column = "s_population", id = false),
					@Result(property = "gdp", column = "s_gdp", id = false)
			}
	)
	List<CountryInfoAdvanced> findCountriesStatsAdvanced(CountryAdvancedFilter f);

	@SelectProvider(type = CountryMapperProvider.class, method = "buildCountSearchQueryCountriesStatsAdvanced")
	long countSearchQueryCountriesStatsAdvanced(CountryAdvancedFilter f);
}
