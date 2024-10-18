package com.clicked.app.services.auth;

import com.clicked.app.dto.authdto.AuthResponseDto;
import com.clicked.app.dto.authdto.LoginRequestDto;
import com.clicked.app.dto.authdto.UserResponseDto;
import com.clicked.app.models.User;

import jakarta.servlet.http.HttpServletRequest;

public interface IAuthService {
  AuthResponseDto createAccount(User request);
  String login(LoginRequestDto request);
  UserResponseDto getUserData(HttpServletRequest request);
}
