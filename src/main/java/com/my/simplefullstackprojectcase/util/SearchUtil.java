package com.my.simplefullstackprojectcase.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Created by Oulis Evangelos on 11/16/25.
 */
@Component
@RequiredArgsConstructor
public class SearchUtil {

	private final SearchMappings searchMappings;

	public String getCountyListOrderBy(final String orderBy) {
		return searchMappings.getCountryList().getOrDefault(orderBy, orderBy);
	}
}
