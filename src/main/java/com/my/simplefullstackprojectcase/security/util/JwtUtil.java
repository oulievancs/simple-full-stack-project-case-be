package com.my.simplefullstackprojectcase.security.util;

import com.my.simplefullstackprojectcase.security.config.JwtProperties;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Created by Oulis Evangelos on 11/16/25.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtUtil {

	private final JwtProperties jwtProperties;

	private SecretKey key;
	// Initializes the key after the class is instantiated and the jwtSecret is injected,
	// preventing the repeated creation of the key and enhancing performance

	@PostConstruct
	public void init() {
		this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
	}

	// Generate JWT token
	public String generateToken(final String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtProperties.getExpiration()))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}

	// Get username from JWT token
	public String getUsernameFromToken(final String token) {
		return Jwts.parserBuilder()
				.setSigningKey(key).build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}

	// Validate JWT token
	public boolean validateJwtToken(final String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (SecurityException e) {
			log.error("Invalid JWT signature: " + e.getMessage());
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token: " + e.getMessage());
		} catch (ExpiredJwtException e) {
			log.error("JWT token is expired: " + e.getMessage());
		} catch (UnsupportedJwtException e) {
			log.error("JWT token is unsupported: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty: " + e.getMessage());
		}
		return false;
	}
}
