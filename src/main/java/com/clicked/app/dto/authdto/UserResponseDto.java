package com.clicked.app.dto.authdto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
  private long id;
  private String username;
  private String email;
  private String firstName;
  private String lastName;
  private int age;
  private String gender;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
