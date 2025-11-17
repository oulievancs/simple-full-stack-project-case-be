package com.my.simplefullstackprojectcase.entity;

import lombok.Data;

/**
 * Created by Oulis Evangelos on 11/16/25.
 */
@Data
public class CountryLanguageEntity {

	private Long countryId;
	private Long languageId;
	private Short official;
}
