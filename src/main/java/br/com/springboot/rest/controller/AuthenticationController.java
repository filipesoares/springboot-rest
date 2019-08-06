package br.com.springboot.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.springboot.rest.configuration.TokenService;
import br.com.springboot.rest.model.User;

@RestController
@RequestMapping(value = "/token", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AuthenticationController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<String> auth(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		try {
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
			Authentication principal = authenticationManager.authenticate(authentication);
			final String token = tokenService.generate(principal);
			return new ResponseEntity<String>(token, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_GATEWAY);
		}
	}

}
