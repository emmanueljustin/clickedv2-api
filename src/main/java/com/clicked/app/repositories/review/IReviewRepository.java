package com.clicked.app.repositories.review;

// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clicked.app.models.Review;

public interface IReviewRepository extends JpaRepository<Review, Long> {
  // Optional<Review> findById(Long id);
}
