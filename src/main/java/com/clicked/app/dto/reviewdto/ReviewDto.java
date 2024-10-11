package com.clicked.app.dto.reviewdto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDto {
  private Long id;
  private String imgPath;
  private String name;
  private String description;
  private int rating;
  private String comment;
}
