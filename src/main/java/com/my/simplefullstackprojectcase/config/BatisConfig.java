package com.my.simplefullstackprojectcase.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Oulis Evangelos on 11/16/25.
 */
@Configuration
@MapperScan(basePackages = "com.my.simplefullstackprojectcase.repository")
public class BatisConfig {
}
