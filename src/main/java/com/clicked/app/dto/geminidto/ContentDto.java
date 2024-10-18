package com.clicked.app.dto.geminidto;

import java.util.List;

public class ContentDto {
  private List<PartDto> parts;
  private String role;

  public List<PartDto> getParts() {
    return parts;
  }

  public void setParts(List<PartDto> parts) {
    this.parts = parts;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}
