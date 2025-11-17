package com.my.simplefullstackprojectcase.entity;

import lombok.Data;

/**
 * Created by Oulis Evangelos on 11/17/25.
 */
@Data
public class RegionEntity {

	private Long regionId;
	private String name;
	private Long continentId;
}
