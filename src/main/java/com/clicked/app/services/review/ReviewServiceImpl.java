package com.clicked.app.services.review;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.clicked.app.dto.reviewdto.AddReviewDto;
import com.clicked.app.dto.reviewdto.DeleteReviewDto;
import com.clicked.app.dto.reviewdto.PageRequestDto;
import com.clicked.app.dto.reviewdto.PaginatedReviewDto;
import com.clicked.app.dto.reviewdto.ReviewDto;
import com.clicked.app.models.Review;
import com.clicked.app.repositories.IReviewRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReviewServiceImpl implements IReviewService {

  private IReviewRepository reviewRepository;

  public ReviewServiceImpl(IReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }

  @Override
  public PaginatedReviewDto getAllReviews(PageRequestDto request) {
    List<Review> reviews = reviewRepository.findAll();

    int pageIndex = request.getPage() - 1; // Used to access the first page into 1 and not 0

    int start = pageIndex * request.getSize();
    int end = Math.min(start + request.getSize(), reviews.size());

    List<Review> paginatedReview = reviews.subList(start, end);
    
    List<ReviewDto> pagedReview = paginatedReview.stream().map((review) -> mapToReviewDto(review)).collect(Collectors.toList());

    int totalCount = reviews.size();

    int totalPages = (int) Math.ceil((double) totalCount / request.getSize());

    PaginatedReviewDto paginatedResponse = PaginatedReviewDto.builder()
      .reviews(pagedReview)
      .currPage(request.getPage())
      .totalItems(totalCount)
      .totalPages(totalPages)
      .build();

    return paginatedResponse;
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
