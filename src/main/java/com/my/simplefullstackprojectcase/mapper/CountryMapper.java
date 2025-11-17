package com.my.simplefullstackprojectcase.mapper;

import com.my.simplefullstackprojectcase.apiclient.model.Country;
import com.my.simplefullstackprojectcase.entity.CountryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by Oulis Evangelos on 11/16/25.
 */
@Mapper(componentModel = "spring")
public interface CountryMapper {

	@Mapping(target = "countryCode", source = "countryCode2")
	Country map(CountryEntity entity);
}
