package com.my.simplefullstackprojectcase.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by Oulis Evangelos on 11/10/25.
 */
@Data
public class CountryEntity {

	private Long countryId;
	private String name;
	private BigDecimal area;
	private LocalDate nationalDay;
	private String countryCode2;
	private String countryCode3;
	private Long regionId;
}
