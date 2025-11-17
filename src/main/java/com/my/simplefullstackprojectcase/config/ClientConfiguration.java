package com.my.simplefullstackprojectcase.config;

import com.my.simplefullstackprojectcase.apiclient.api.CountriesViewApiClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Oulis Evangelos on 11/10/25.
 */
@EnableFeignClients(clients = CountriesViewApiClient.class)
@ComponentScan(basePackageClasses = CountriesViewApiClient.class)
@ConditionalOnProperty("com.my.simplefullstackprojectcace.apiclient.url")
public class ClientConfiguration {
}
