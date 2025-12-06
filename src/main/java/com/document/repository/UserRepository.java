package com.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.document.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);
}
