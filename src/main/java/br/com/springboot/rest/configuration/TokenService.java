package br.com.springboot.rest.configuration;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.springboot.rest.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${jwt.expiration}")
	private String expiration;
	
	@Value("${jwt.secret}")
	private String secret;

	public String generate(Authentication principal) {
		
		User user = (User) principal.getPrincipal();
		
		return Jwts.builder()
				.setIssuer("web-client")
				.setSubject(user.getId().toString())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + Long.valueOf(expiration)))
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact(); 
	}
	
}
