package com.clicked.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clicked.app.models.Service;

public interface IServiceRepository extends JpaRepository<Service, Long> {}
