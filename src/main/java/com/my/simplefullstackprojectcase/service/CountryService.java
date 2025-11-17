package com.my.simplefullstackprojectcase.service;

import com.my.simplefullstackprojectcase.apiclient.model.CountryInfoAdvancedList;
import com.my.simplefullstackprojectcase.apiclient.model.CountryInfoList;
import com.my.simplefullstackprojectcase.apiclient.model.CountryList;
import com.my.simplefullstackprojectcase.apiclient.model.LanguageList;
import com.my.simplefullstackprojectcase.apiclient.model.RegionList;
import com.my.simplefullstackprojectcase.mapper.CountryMapper;
import com.my.simplefullstackprojectcase.mapper.LanguageMapper;
import com.my.simplefullstackprojectcase.mapper.RegionMapper;
import com.my.simplefullstackprojectcase.repository.CountryRepository;
import com.my.simplefullstackprojectcase.repository.LanguageRepository;
import com.my.simplefullstackprojectcase.repository.RegionRepository;
import com.my.simplefullstackprojectcase.repository.providers.CountryAdvancedFilter;
import com.my.simplefullstackprojectcase.util.SearchUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Created by Oulis Evangelos on 11/16/25.
 */
@Service
@RequiredArgsConstructor
public class CountryService {

	private final CountryRepository countryRepository;
	private final LanguageRepository languageRepository;
	private final RegionRepository regionRepository;
	private final CountryMapper countryMapper;
	private final LanguageMapper languageMapper;
	private final RegionMapper regionMapper;
	private final SearchUtil searchUtil;

	public CountryList getAllCountries(final String orderBy) {
		return new CountryList().countries(
				countryRepository.findAll(searchUtil.getCountyListOrderBy(orderBy)).stream()
						.map(countryMapper::map).collect(Collectors.toList())
		);
	}

	public CountryInfoList getCountriesStats(final String orderBy) {
		return new CountryInfoList().countries(
				countryRepository.findAllStats(searchUtil.getCountyListOrderBy(orderBy))
		);
	}

	public CountryInfoAdvancedList getCountriesStatsAdvanced(final String orderBy, final Integer yearFrom, final Integer yearTo,
	                                                         final String region, final Integer page, final Integer size) {
		final CountryAdvancedFilter f = new CountryAdvancedFilter();
		f.setYearFrom(yearFrom);
		f.setYearTo(yearTo);
		f.setRegion(region);
		f.setLimit(size > 0 ? size : 10);
		f.setOffset(page > 0 ? (page - 1) * size : 0);
		f.setOrderBy(searchUtil.getCountyListOrderBy(orderBy));

		final long count = countryRepository.countSearchQueryCountriesStatsAdvanced(f);
		return new CountryInfoAdvancedList().countries(countryRepository.findCountriesStatsAdvanced(f))
				.totalElements(count)
				.totalPages((int) Math.ceil((double) count / size))
				.pageSize(size);
	}

	public LanguageList getCountryLanguages(final String countryCode) {
		return new LanguageList().languages(
				languageRepository.findAllByCountryCode2(countryCode).stream()
						.map(languageMapper::map).collect(Collectors.toList())
		);
	}

	public RegionList getRegions() {
		return new RegionList().regions(
				regionRepository.findAllRegions().stream()
						.map(regionMapper::map).collect(Collectors.toList())
		);
	}
}
