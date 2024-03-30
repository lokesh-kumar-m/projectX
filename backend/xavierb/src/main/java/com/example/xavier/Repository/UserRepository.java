package com.example.xavier.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.xavier.model.UsersEntity;

public interface UserRepository extends JpaRepository<UsersEntity,Integer> {
    Optional<UsersEntity> findByUsername(String username);
    boolean existsByUsername(String username);
}
