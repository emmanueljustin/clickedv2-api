package com.clicked.app.dto.reviewdto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginatedReviewDto {
  private List<ReviewDto> reviews;
  private int currPage;
  private int totalItems;
  private int totalPages;
}
