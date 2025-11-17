package com.my.simplefullstackprojectcase.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Created by Oulis Evangelos on 11/16/25.
 */
@Configuration
@ConfigurationProperties("ordering")
@Getter
@Setter
public class SearchMappings {

	private Map<String, String> countryList;
}
