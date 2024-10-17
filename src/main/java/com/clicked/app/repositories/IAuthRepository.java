package com.clicked.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clicked.app.models.User;

public interface IAuthRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
  User findByEmail(String email);
}
