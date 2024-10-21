package com.clicked.app.controllers;

import java.util.List;

import com.clicked.app.dto.ApiResponse;
import com.clicked.app.dto.ApiResponseMsgOnly;
import com.clicked.app.dto.reviewdto.AddReviewDto;
import com.clicked.app.dto.reviewdto.DeleteReviewDto;
import com.clicked.app.dto.reviewdto.PageRequestDto;
import com.clicked.app.dto.reviewdto.PaginatedReviewDto;
import com.clicked.app.dto.reviewdto.ReviewDto;
import com.clicked.app.services.review.IReviewService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class ReviewController {

  public static final String BASE_PATH = "api/v1";

  public IReviewService reviewService;

  public ReviewController(IReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @PostMapping(value = BASE_PATH + "/review/get", produces = "application/json")
  public ResponseEntity<ApiResponse<PaginatedReviewDto>> getAllReviews(@RequestBody PageRequestDto request) {
    PaginatedReviewDto reviews = reviewService.getAllReviews(request);

    return ResponseEntity.status(200).body(
      new ApiResponse<PaginatedReviewDto>(
        "ok",
        "Here is your list of reviews",
        reviews
      )
    );
  }

  @PostMapping(value = BASE_PATH + "/review/add", produces = "application/json")
  public ResponseEntity<ApiResponse<ReviewDto>> postMethodName(@RequestBody AddReviewDto request) {
    ReviewDto addedReview = reviewService.addReviews(request);

    return ResponseEntity.status(200).body(
      new ApiResponse<ReviewDto>(
        "ok",
        "You have succesfully added a review",
        addedReview
      )
    );
  }

  @PostMapping(value = BASE_PATH + "/review/update", produces = "application/json")
  public ResponseEntity<ApiResponse<ReviewDto>> updateReview(@RequestBody ReviewDto request) {
    ReviewDto updatedReview = reviewService.updateReview(request);
    
    return ResponseEntity.status(200).body(
      new ApiResponse<ReviewDto>(
        "ok",
        "Successfully updated",
        updatedReview
      )
    );
  }
  
  @PostMapping(value = BASE_PATH + "/review/delete", produces = "application/json")
  public ResponseEntity<ApiResponseMsgOnly> deleteReview(@RequestBody DeleteReviewDto request) {
    boolean result = reviewService.deleteReview(request);

    if (result) {
      return ResponseEntity.status(200).body(
        new ApiResponseMsgOnly(
          "ok",
          "Successfully deleted"
        )
      );
    } else {
      return ResponseEntity.status(200).body(
        new ApiResponseMsgOnly(
          "err",
          "Oops! We cannot delete the item"
        )
      );
    }
  }
}
