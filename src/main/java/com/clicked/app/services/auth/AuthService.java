package com.clicked.app.services.auth;

import java.util.Optional;

// import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.clicked.app.dto.authdto.AuthResponseDto;
import com.clicked.app.dto.authdto.LoginRequestDto;
import com.clicked.app.models.User;
import com.clicked.app.repositories.IAuthRepository;
import com.clicked.app.util.JwtUtil;

@Service
public class AuthService implements IAuthService {

  private IAuthRepository authRepository;

  // private AuthenticationManager authenticationManager;

  private JwtUtil jwtUtil;

  public AuthService(IAuthRepository authRepository, JwtUtil jwtUtil) {
    this.authRepository = authRepository;
    // this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;
  }

  @Override
  public AuthResponseDto createAccount(User request) {

    if (authRepository.findByUsername(request.getUsername()) != null) {
      throw new RuntimeException("Username already exists");
    }
    
    if (authRepository.findByEmail(request.getEmail()) != null) {
        throw new RuntimeException("Email already exists");
    }

    request.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
    authRepository.save(request);

    AuthResponseDto response = AuthResponseDto.builder()
      .username(request.getUsername())
      .email(request.getEmail())
      .firstName(request.getFirstName())
      .lastName(request.getLastName())
      .jwt(jwtUtil.generateToken(request.getUsername()))
      .build();

    return response;
  }

  @Override
  public String login(LoginRequestDto request) {
    Optional<User> optionalUser = authRepository.findByUsername(request.getUsername());

    User user = optionalUser.orElseThrow(() -> new RuntimeException("Invalid username or password"));

    if (user == null || !new BCryptPasswordEncoder().matches(request.getPassword(), user.getPassword())) {
      throw new RuntimeException("Invalid username or password");
    }

    return jwtUtil.generateToken(user.getUsername());
  }
}
