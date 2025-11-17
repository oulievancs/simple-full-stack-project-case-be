package com.my.simplefullstackprojectcase.security.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Oulis Evangelos on 11/16/25.
 */
@Component
@Slf4j
public class AuthEntrypointJwt implements AuthenticationEntryPoint {

	@Override
	public void commence(
			final HttpServletRequest request,
			final HttpServletResponse response,
			final AuthenticationException authException
	) throws IOException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
	}
}
