package com.abhishek.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

          @Autowired
          UserRepository userRepository;

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
}
