package com.clicked.app.services.review;

import java.util.List;

import com.clicked.app.dto.reviewdto.AddReviewDto;
import com.clicked.app.dto.reviewdto.ReviewDto;

public interface IReviewService {
  List<ReviewDto> getAllReviews();
  ReviewDto addReviews(AddReviewDto request);
  ReviewDto updateReview(ReviewDto request);
}
