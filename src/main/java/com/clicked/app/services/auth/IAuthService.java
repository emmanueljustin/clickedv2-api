package com.clicked.app.services.auth;

import com.clicked.app.dto.authdto.AuthResponseDto;
import com.clicked.app.dto.authdto.LoginRequestDto;
import com.clicked.app.models.User;

public interface IAuthService {
  AuthResponseDto createAccount(User request);
  String login(LoginRequestDto request);
}
