package com.abhishek.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserPrincipal implements UserDetails {

          private UserDAO userDAO;

          public UserPrincipal(UserDAO userDAO) {
                    this.userDAO = userDAO;
          }

          @Override
          public Collection<? extends GrantedAuthority> getAuthorities() {
                    // If you have roles, add them here
                    return Collections.emptyList(); // Or return authorities based on your roles
          }

          @Override
          public String getPassword() {
                    return userDAO.getPassword();
          }

          @Override
          public String getUsername() {
                    return userDAO.getUsername();
          }

          @Override
          public boolean isAccountNonExpired() {
                    return true; // Customize as per your needs
          }

          @Override
          public boolean isAccountNonLocked() {
                    return true; // Customize as per your needs
          }

          @Override
          public boolean isCredentialsNonExpired() {
                    return true; // Customize as per your needs
          }

          @Override
          public boolean isEnabled() {
                    return true; // Customize as per your needs
          }
}
