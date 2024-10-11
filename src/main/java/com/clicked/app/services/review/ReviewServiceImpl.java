package com.clicked.app.services.review;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.clicked.app.dto.reviewdto.AddReviewDto;
import com.clicked.app.dto.reviewdto.DeleteReviewDto;
import com.clicked.app.dto.reviewdto.ReviewDto;
import com.clicked.app.models.Review;
import com.clicked.app.repositories.review.IReviewRepository;

import jakarta.persistence.EntityNotFoundException;

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

    return mapToReviewDto(newReview);
  }

  @Override
  public ReviewDto updateReview(ReviewDto request) {
    Optional<Review> optionalReview = reviewRepository.findById(request.getId());

    if (optionalReview.isPresent()) {
      Review existingReview = optionalReview.get();
        existingReview.setImgPath(request.getImgPath());
        existingReview.setName(request.getName());
        existingReview.setDescription(request.getDescription());
        existingReview.setRating(request.getRating());
        existingReview.setComment(request.getComment());

      Review updatedReview = reviewRepository.save(existingReview);
      return mapToReviewDto(updatedReview);
    } else {
      throw new EntityNotFoundException("Review not found with id: " + request.getId());
    }
  }

  @Override
  public boolean deleteReview(DeleteReviewDto request) {
    List<Long> ids = request.getIds();

    if (ids != null  && !ids.isEmpty()) {
      reviewRepository.deleteAllById(ids);

      return true;
    }

    return false;
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
