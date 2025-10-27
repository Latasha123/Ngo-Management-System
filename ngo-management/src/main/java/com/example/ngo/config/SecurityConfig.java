package com.example.ngo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtFilter;

	public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
		this.jwtFilter = jwtFilter;
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new com.example.ngo.service.CustomUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider p = new DaoAuthenticationProvider();
		p.setUserDetailsService(userDetailsService());
		p.setPasswordEncoder(passwordEncoder());
		return p;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth
				.requestMatchers("/swagger-ui.html", "/api-docs/**", "/api/auth/**", "/api/public/**", "/api/donor/**",
						"/swagger-ui/**", "/v3/api-docs/**")
				.permitAll().requestMatchers("/api/**").hasRole("ADMIN").requestMatchers("/api/volunteer/**")
				.hasRole("VOLUNTEER").requestMatchers("/api/donor/**").hasRole("DONOR").anyRequest().authenticated())
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		http.headers(headers -> headers.frameOptions(frame -> frame.disable()));
		return http.build();
	}
}
