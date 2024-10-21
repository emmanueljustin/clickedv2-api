package com.clicked.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.clicked.app.filter.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  
  private JwtRequestFilter jwtRequestFilter;

  public SecurityConfig(JwtRequestFilter jwtRequestFilter ) {
    this.jwtRequestFilter = jwtRequestFilter;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(authorize -> authorize
        .requestMatchers("/api/v1/auth/**").permitAll()
        .requestMatchers("/api/v1/pricing/**").permitAll()
        .requestMatchers("/api/v1/review/**").permitAll()
        .anyRequest().authenticated()
      )
      .sessionManagement(session -> session
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      );

    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public AuthenticationManager authManager(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder = 
      http.getSharedObject(AuthenticationManagerBuilder.class);

    return authenticationManagerBuilder.build();
  }
}
