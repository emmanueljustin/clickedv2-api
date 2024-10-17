package com.clicked.app.filter;

import java.util.Collections;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clicked.app.models.User;
import com.clicked.app.repositories.IAuthRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
  private IAuthRepository authRepository;

  public CustomUserDetailService(IAuthRepository authRepository) {
    this.authRepository = authRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    Optional<User> userOptional = authRepository.findByUsername(username);

    User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

    return org.springframework.security.core.userdetails.User
      .withUsername(user.getUsername())
      .password(user.getPassword())
      .authorities(Collections.emptyList())
      .build();
  }
}
