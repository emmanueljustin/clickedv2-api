package com.clicked.app.services.review;

import com.clicked.app.dto.reviewdto.AddReviewDto;
import com.clicked.app.dto.reviewdto.DeleteReviewDto;
import com.clicked.app.dto.reviewdto.PageRequestDto;
import com.clicked.app.dto.reviewdto.PaginatedReviewDto;
import com.clicked.app.dto.reviewdto.ReviewDto;

public interface IReviewService {
  PaginatedReviewDto getAllReviews(PageRequestDto request);
  ReviewDto addReviews(AddReviewDto request);
  ReviewDto updateReview(ReviewDto request);
  boolean deleteReview(DeleteReviewDto request);
}
