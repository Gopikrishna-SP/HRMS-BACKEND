package com.kryptos.hrms.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.kryptos.hrms.security.jwt.AuthEntryPointJwt;
import com.kryptos.hrms.security.jwt.AuthTokenFilter;
import com.kryptos.hrms.security.jwt.CustomAccessDeniedHandler;
import com.kryptos.hrms.serviceimpl.UserDetailsServiceImpl;



@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unathorizedHandler;

	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;
	
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Your React app origin
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allowed methods
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Headers to allow
        configuration.setAllowCredentials(true); // Allow cookies and authorization headers
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    
	
	@Bean
	AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.csrf(csrf -> csrf.disable())
	        .exceptionHandling(exception -> exception.authenticationEntryPoint(unathorizedHandler).accessDeniedHandler(customAccessDeniedHandler))
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authorizeRequests(auth -> auth.requestMatchers("/api/v1/user/signin").permitAll()
	            .requestMatchers("/swagger-ui/**").permitAll()
	            .requestMatchers("/h2-console/**").permitAll()
	            .requestMatchers("/v3/api-docs/**").permitAll()
	            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Allow OPTIONS requests
	            .anyRequest().authenticated())
	        .headers(headers -> headers.frameOptions().sameOrigin())
	        .cors();  // Apply CORS configuration

	    http.authenticationProvider(authenticationProvider());
	    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}

	


}


