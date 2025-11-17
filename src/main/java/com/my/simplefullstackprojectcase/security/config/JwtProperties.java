package com.my.simplefullstackprojectcase.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Oulis Evangelos on 11/16/25.
 */
@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

	private String secret;
	private int expiration;
}
