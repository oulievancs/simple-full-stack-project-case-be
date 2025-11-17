package com.my.simplefullstackprojectcase.controller;

import com.my.simplefullstackprojectcase.apiclient.api.AuthApi;
import com.my.simplefullstackprojectcase.apiclient.model.LoginRequest;
import com.my.simplefullstackprojectcase.apiclient.model.SecurityToken;
import com.my.simplefullstackprojectcase.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Created by Oulis Evangelos on 11/16/25.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthApiController implements AuthApi {

	private final JwtUtil jwtUtil;

	@Override
	public ResponseEntity<SecurityToken> login(final LoginRequest loginRequest) {
		log.info("Access requested by username: {}", loginRequest.getUsername());

		final SecurityToken securityToken = new SecurityToken()
				.accessToken(jwtUtil.generateToken(loginRequest.getUsername()));

		return ResponseEntity.of(Optional.of(securityToken));
	}
}
