package com.my.simplefullstackprojectcase.security.config;

import com.my.simplefullstackprojectcase.security.component.AuthEntrypointJwt;
import com.my.simplefullstackprojectcase.security.component.AuthTokenFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by Oulis Evangelos on 11/16/25.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final AuthEntrypointJwt unauthorizedHandler;
	private final AuthTokenFilter authTokenFilter;

	@Bean
	public AuthenticationManager authenticationManager(
			final AuthenticationConfiguration authenticationConfiguration
	) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				// Disable CSRF
				.csrf().disable()
				// Enable CORS if needed
				.cors()
				.and()
				// Exception handling
				.exceptionHandling()
				.authenticationEntryPoint(unauthorizedHandler)
				.and()
				// Stateless session management
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				// Request authorization
				.authorizeRequests()
				.antMatchers("/api/auth/**", "/api/test/all").permitAll()
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.anyRequest().authenticated();

		// Add JWT token filter before UsernamePasswordAuthenticationFilter
		http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
