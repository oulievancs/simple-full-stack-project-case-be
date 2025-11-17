package com.my.simplefullstackprojectcase.repository.providers;

import lombok.Data;

/**
 * Created by Oulis Evangelos on 11/17/25.
 */
@Data
public class CountryAdvancedFilter {

	private Integer yearFrom;
	private Integer yearTo;
	private String region;

	private String orderBy;
	private int limit;
	private int offset;
}
