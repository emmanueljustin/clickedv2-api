package com.clicked.app.dto.authdto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDto {
  private String username;
  private String email;
  private String firstName;
  private String lastName;
  private String jwt;
}
