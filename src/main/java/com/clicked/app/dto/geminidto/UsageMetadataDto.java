package com.clicked.app.dto.geminidto;

public class UsageMetadataDto {
  
  private int promptTokenCount;
  private int candidatesTokenCount;
  private int totalTokenCount;

  public int getPromptTokenCount() {
    return promptTokenCount;
  }

  public void setPromptTokenCount(int promptTokenCount) {
    this.promptTokenCount = promptTokenCount;
  }

  public int getCandidatesTokenCount() {
    return candidatesTokenCount;
  }

  public void setCandidatesTokenCount(int candidatesTokenCount) {
    this.candidatesTokenCount = candidatesTokenCount;
  }

  public int getTotalTokenCount() {
    return totalTokenCount;
  }

  public void setTotalTokenCount(int totalTokenCount) {
    this.totalTokenCount = totalTokenCount;
  }
}
