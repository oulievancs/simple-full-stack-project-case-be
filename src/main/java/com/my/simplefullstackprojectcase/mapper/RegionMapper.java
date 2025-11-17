package com.my.simplefullstackprojectcase.mapper;

import com.my.simplefullstackprojectcase.apiclient.model.Region;
import com.my.simplefullstackprojectcase.entity.RegionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by Oulis Evangelos on 11/17/25.
 */
@Mapper(componentModel = "spring")
public interface RegionMapper {

	@Mapping(target = "regionCode", source = "regionId")
	@Mapping(target = "regionName", source = "name")
	Region map(RegionEntity entity);
}
