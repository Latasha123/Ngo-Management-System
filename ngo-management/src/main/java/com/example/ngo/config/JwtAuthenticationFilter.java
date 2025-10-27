package com.example.ngo.config;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.ngo.util.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private JwtUtils jwtUtils;

	public JwtAuthenticationFilter(JwtUtils jwtUtils) {
		this.jwtUtils = jwtUtils;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");

		if (header == null || !header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = header.substring(7);
		if (!jwtUtils.isTokenValid(token)) {
			filterChain.doFilter(request, response);
			return;
		}

		String email = jwtUtils.extractEmail(token);
		String role = jwtUtils.extractRole(token);

		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, null,
				List.of(authority));

		SecurityContextHolder.getContext().setAuthentication(auth);
		filterChain.doFilter(request, response);

	}

}
