package com.clicked.app.controllers;

import java.util.List;

import com.clicked.app.dto.ApiResponse;
import com.clicked.app.dto.reviewdto.AddReviewDto;
import com.clicked.app.dto.reviewdto.ReviewDto;
import com.clicked.app.services.review.IReviewService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping(value = BASE_PATH + "/review/get", produces = "application/json")
  public ResponseEntity<ApiResponse<List<ReviewDto>>> getAllReviews() {
    List<ReviewDto> reviews =  reviewService.getAllReviews();

    return ResponseEntity.status(200).body(
      new ApiResponse<List<ReviewDto>>(
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
}
