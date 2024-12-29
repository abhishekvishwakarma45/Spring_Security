package com.abhishek.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.abhishek.security.JWT.JWTService;

@Service
public class UserService implements UserDetailsService {

          @Autowired
          UserRepository userRepository;

          @Autowired
          JWTService jwtService;

          public ResponseEntity<?> register(UserDAO user) {
                    user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
                    userRepository.save(user);
                    return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
          }

          @Override
          public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                    UserDAO user = userRepository.findUserByUsername(username);
                    if (user == null) {
                              throw new UsernameNotFoundException("User not found with username: " + username);
                    }
                    return new UserPrincipal(user);
          }

          public String verify(UserDAO userDAO, AuthenticationManager authenticationManager) {
                    try {
                              Authentication authentication = authenticationManager.authenticate(
                                                  new UsernamePasswordAuthenticationToken(userDAO.getUsername(),
                                                                      userDAO.getPassword()));

                              if (authentication.isAuthenticated()) {
                                        return jwtService.generateToken(userDAO.getUsername());
                              }
                    } catch (Exception e) {
                              System.out.println(e.getMessage());
                    }
                    return "fail";
          }

}
