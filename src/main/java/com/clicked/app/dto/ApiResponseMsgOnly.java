package com.clicked.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponseMsgOnly {
  private String status;
  private String message;

  public ApiResponseMsgOnly(String status, String message) {
    this.status = status;
    this.message = message;
  }
}
