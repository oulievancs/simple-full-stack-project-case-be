package com.my.simplefullstackprojectcase.repository;

import com.my.simplefullstackprojectcase.entity.LanguageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Oulis Evangelos on 11/16/25.
 */
@Mapper
public interface LanguageRepository {

	@Select("SELECT * FROM languages L " +
			"LEFT JOIN country_languages CL ON L.language_id = CL.language_id " +
			"LEFT JOIN countries C ON CL.country_id = C.country_id WHERE C.country_code2 = #{countryCode2}")
	List<LanguageEntity> findAllByCountryCode2(@Param("countryCode2") String countryCode2);
}
