package com.clicked.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clicked.app.models.Pricing;

public interface IPricingRepository extends JpaRepository<Pricing, Long> {}
