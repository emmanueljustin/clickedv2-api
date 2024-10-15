package com.clicked.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clicked.app.models.Review;

public interface IReviewRepository extends JpaRepository<Review, Long> {}
