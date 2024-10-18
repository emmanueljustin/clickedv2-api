package com.clicked.app.dto.geminidto;

public class SafetyRatingDto {
  
  private String category;
  private String probability;

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getProbability() {
    return probability;
  }

  public void setProbability(String probability) {
    this.probability = probability;
  }
}
