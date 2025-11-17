package com.my.simplefullstackprojectcase.mapper;

import com.my.simplefullstackprojectcase.apiclient.model.Language;
import com.my.simplefullstackprojectcase.entity.LanguageEntity;
import org.mapstruct.Mapper;

/**
 * Created by Oulis Evangelos on 11/16/25.
 */
@Mapper(componentModel = "spring")
public interface LanguageMapper {

	Language map(LanguageEntity entity);
}
