package com.clicked.app.services.review;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.clicked.app.dto.reviewdto.AddReviewDto;
import com.clicked.app.dto.reviewdto.ReviewDto;
import com.clicked.app.models.Review;
import com.clicked.app.repositories.review.IReviewRepository;

@Service
public class ReviewServiceImpl implements IReviewService {

  private IReviewRepository reviewRepository;

  public ReviewServiceImpl(IReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }

  @Override
  public List<ReviewDto> getAllReviews() {
    List<Review> reviews = reviewRepository.findAll();

    return reviews.stream().map((review) -> mapToReviewDto(review)).collect(Collectors.toList());
  }

  @Override
  public ReviewDto addReviews(AddReviewDto request) {
    Review review = Review.builder()
      .imgPath(request.getImgPath())
      .name(request.getName())
      .description(request.getDescription())   
      .rating(request.getRating())
      .comment(request.getComment())
      .build();

    Review newReview =  reviewRepository.save(review);

    ReviewDto addedData = mapToReviewDto(newReview);

    return addedData;
  }
  
  private ReviewDto mapToReviewDto(Review review) {
    ReviewDto reviewDto = ReviewDto.builder()
    .id(review.getId())
    .imgPath(review.getImgPath())
    .name(review.getName())
    .description(review.getDescription())
    .rating(review.getRating())
    .comment(review.getComment())
    .build();

    return reviewDto;
  }
}
