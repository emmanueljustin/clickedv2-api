package com.clicked.app.dto.reviewdto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddReviewDto {
  public String imgPath;
  public String name;
  public String description;
  public int rating;
  public String comment;
}
