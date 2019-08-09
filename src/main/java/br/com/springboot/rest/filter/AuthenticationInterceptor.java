package br.com.springboot.rest.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.springboot.rest.configuration.TokenService;
import br.com.springboot.rest.model.User;
import br.com.springboot.rest.repository.UserRepository;

public class AuthenticationInterceptor extends OncePerRequestFilter {
	
	private TokenService tokenService;	
	private UserRepository userRepository;
	
	public AuthenticationInterceptor(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String token = extractToken(request);
		Boolean isToken = tokenService.isValidToken(token);
		
		if (isToken) {
			authenticate(token);
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	private void authenticate(String token) {
		Long uuid = tokenService.getPrincipal(token);
		User principal = userRepository.findById(uuid).get();
		final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
		// Authenticate in Srping Security context
		SecurityContextHolder.getContext().setAuthentication(authentication);		
	}

	private String extractToken(final HttpServletRequest request) {
		
		final String header = request.getHeader("Authorization");
		
		if (header==null || header.equals("") || !header.startsWith("Bearer ")) {
			return null;
		}
		
		return header.substring(7, header.length());
	}

}
