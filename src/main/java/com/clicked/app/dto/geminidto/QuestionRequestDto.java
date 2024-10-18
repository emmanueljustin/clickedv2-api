package com.clicked.app.dto.geminidto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionRequestDto {
  private String question;

  @JsonCreator
  public QuestionRequestDto(@JsonProperty("question") String question) {
    this.question = question;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }
}
