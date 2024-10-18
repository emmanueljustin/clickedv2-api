package com.clicked.app.dto.geminidto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataDto {
  
  @JsonProperty("candidates")
  private CandidateDto[] candidates;

  @JsonProperty("usageMetadata")
  private UsageMetadataDto usageMetadata;

  public CandidateDto[] getCandidates() {
    return candidates;
  }

  public void setCandidates(CandidateDto[] candidates) {
    this.candidates = candidates;
  }

  public UsageMetadataDto getUsageMetadata() {
    return usageMetadata;
  }

  public void setUsageMetadata(UsageMetadataDto usageMetadata) {
    this.usageMetadata = usageMetadata;
  }
}
