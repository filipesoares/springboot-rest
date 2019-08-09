package br.com.springboot.rest.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import br.com.springboot.rest.configuration.TokenService;

public class AuthenticationInterceptor extends OncePerRequestFilter {
	
	private TokenService tokenService;
	
	public AuthenticationInterceptor(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String token = extractToken(request);
		Boolean isToken = tokenService.isValidToken(token);
		System.out.println(isToken);
		filterChain.doFilter(request, response);
		
	}
	
	private String extractToken(final HttpServletRequest request) {
		
		final String header = request.getHeader("Authorization");
		
		if (header==null || header.equals("") || !header.startsWith("Bearer ")) {
			return null;
		}
		
		return header.substring(7, header.length());
	}

}
