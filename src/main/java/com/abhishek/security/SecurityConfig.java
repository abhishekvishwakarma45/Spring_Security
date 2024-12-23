package com.abhishek.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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

          @Bean
          DaoAuthenticationProvider authProvider() {
                    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
                    provider.setUserDetailsService(userService);
                    provider.setPasswordEncoder(passwordEncoder());
                    return provider;
          }

          @Bean
          SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                    http.csrf(customizer -> customizer.disable());
                    http.authorizeHttpRequests(customizer -> customizer
                                        .requestMatchers("/login", "/register").permitAll()
                                        .anyRequest().authenticated());
                    http.formLogin(customizer -> customizer.loginPage("/login.html").permitAll());

                    return http.build();
          }

}
