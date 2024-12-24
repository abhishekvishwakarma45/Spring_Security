package com.abhishek.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.abhishek.security.JWT.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
          @Bean
          public UserDAO userDAO() {
                    return new UserDAO();
          }

          @Bean
          public BCryptPasswordEncoder passwordEncoder() {
                    return new BCryptPasswordEncoder();
          }

          @Autowired
          private UserService userService;

          @Autowired
          JwtFilter jwtFilter;

          @Bean
          AuthenticationProvider authProvider() {
                    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
                    provider.setUserDetailsService(userService);
                    provider.setPasswordEncoder(passwordEncoder());
                    return provider;
          }

          @Bean
          SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                    http.csrf(csrf -> csrf.disable()) // Ensure CSRF is disabled
                                        .authorizeHttpRequests(auth -> auth
                                                            .requestMatchers("/login", "/register").permitAll()
                                                            .anyRequest().authenticated())
                                        .httpBasic(Customizer.withDefaults())
                                        .sessionManagement(session -> session
                                                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
                    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
                    return http.build();
          }

          @Bean
          AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
                              throws Exception {
                    return authenticationConfiguration.getAuthenticationManager();
          }
}
