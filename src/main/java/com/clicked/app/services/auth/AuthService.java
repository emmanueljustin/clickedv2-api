package com.clicked.app.services.auth;

import java.util.Optional;

// import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.clicked.app.dto.authdto.AuthResponseDto;
import com.clicked.app.dto.authdto.LoginRequestDto;
import com.clicked.app.dto.authdto.UserResponseDto;
import com.clicked.app.models.User;
import com.clicked.app.repositories.IAuthRepository;
import com.clicked.app.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

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

  @Override
  public UserResponseDto getUserData(HttpServletRequest request) {
    String token = request.getHeader("Authorization").substring(7);

    String username = jwtUtil.extractUsername(token);

    User user = authRepository.findByUsername(username)
      .orElseThrow(() -> new RuntimeException("User cannot be found"));

    UserResponseDto userResponse = UserResponseDto.builder()
      .id(user.getId())
      .username(user.getUsername())
      .email(user.getEmail())
      .firstName(user.getFirstName())
      .lastName(user.getLastName())
      .age(user.getAge())
      .gender(user.getGender())
      .createdAt(user.getCreatedAt())
      .updatedAt(user.getUpdatedAt())
      .build();

    return userResponse;
  }
}
