package com.my.simplefullstackprojectcase.repository;

import com.my.simplefullstackprojectcase.entity.RegionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Oulis Evangelos on 11/17/25.
 */
@Mapper
public interface RegionRepository {

	@Select("SELECT * FROM regions ORDER BY name")
	List<RegionEntity> findAllRegions();
}
