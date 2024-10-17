package com.clicked.app.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.clicked.app.dto.ApiResponse;
import com.clicked.app.dto.authdto.AuthResponseDto;
import com.clicked.app.dto.authdto.LoginRequestDto;
import com.clicked.app.models.User;
import com.clicked.app.services.auth.IAuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AuthController {
  
  public static final String BASE_PATH = "api/v1";

  private IAuthService authService;

  public AuthController(IAuthService authService) {
    this.authService = authService;
  }

  @PostMapping(value = BASE_PATH + "/auth/register")
  public ResponseEntity<ApiResponse<?>> createAccount(@RequestBody User request) {
    try {
      AuthResponseDto response = authService.createAccount(request);

      return ResponseEntity.status(200).body(
        new ApiResponse<AuthResponseDto>(
          "ok",
          "You've successfully created your account",
          response
        )
      );
    } catch (RuntimeException e) {
      return ResponseEntity.status(403).body(
        new ApiResponse<String>(
          "err",
          "There is a problem encountered",
          e.getMessage()
        )
      );
    }
  }

  @PostMapping(value = BASE_PATH + "/auth/login")
  public ResponseEntity<ApiResponse<String>> signIn(@RequestBody LoginRequestDto request) {
    try {
      String token = authService.login(request);
      
      return ResponseEntity.status(200).body(
        new ApiResponse<String>(
          "ok",
          "You've successfully created your account",
          token
        )
      );
    } catch (RuntimeException e) {
      return ResponseEntity.status(403).body(
        new ApiResponse<String>(
          "err",
          "We're unable to log you in",
          e.getMessage()
        )
      );
    }
  }
}
