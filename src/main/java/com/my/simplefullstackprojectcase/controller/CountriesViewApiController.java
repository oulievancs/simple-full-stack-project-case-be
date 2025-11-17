package com.my.simplefullstackprojectcase.controller;

import com.my.simplefullstackprojectcase.apiclient.api.CountriesViewApi;
import com.my.simplefullstackprojectcase.apiclient.model.CountryInfoAdvancedList;
import com.my.simplefullstackprojectcase.apiclient.model.CountryInfoList;
import com.my.simplefullstackprojectcase.apiclient.model.CountryList;
import com.my.simplefullstackprojectcase.apiclient.model.LanguageList;
import com.my.simplefullstackprojectcase.apiclient.model.RegionList;
import com.my.simplefullstackprojectcase.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Created by Oulis Evangelos on 11/10/25.
 */
@RestController
@RequiredArgsConstructor
public class CountriesViewApiController implements CountriesViewApi {

	private final CountryService service;

	@Override
	public ResponseEntity<CountryList> viewCountries(final String orderBy) {
		return ResponseEntity.of(Optional.of(service.getAllCountries(orderBy)));
	}

	@Override
	public ResponseEntity<CountryInfoList> viewCountriesStats(final String orderBy) {
		return ResponseEntity.of(Optional.of(service.getCountriesStats(orderBy)));
	}

	@Override
	public ResponseEntity<CountryInfoAdvancedList> viewCountriesStatsAdvanced(final String orderBy, final Integer yearFrom, final Integer yearTo,
	                                                                          final String region, final Integer page, final Integer size) {
		return ResponseEntity.of(Optional.of(service.getCountriesStatsAdvanced(orderBy, yearFrom, yearTo, region, page, size)));
	}

	@Override
	public ResponseEntity<LanguageList> viewCountryLanguages(final String countryCode) {
		return ResponseEntity.of(Optional.of(service.getCountryLanguages(countryCode)));
	}

	@Override
	public ResponseEntity<RegionList> viewRegions() {
		return ResponseEntity.of(Optional.of(service.getRegions()));
	}
}
