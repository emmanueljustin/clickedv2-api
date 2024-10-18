package com.clicked.app.dto.geminidto;

public class CandidateDto {

  private ContentDto content;
  private String finishReason;
  private int index;
  private SafetyRatingDto[] safetyRatings;

  public ContentDto getContent() {
    return content;
  }

  public void setContent(ContentDto content) {
    this.content = content;
  }

  public String getFinishReason() {
    return finishReason;
  }

  public void setFinishReason(String finishReason) {
    this.finishReason = finishReason;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public SafetyRatingDto[] getSafetyRatings() {
    return safetyRatings;
  }

  public void setSafetyRatings(SafetyRatingDto[] safetyRatings) {
    this.safetyRatings = safetyRatings;
  }
}
