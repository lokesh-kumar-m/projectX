package com.example.xavier.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.xavier.model.FriendsEntity;

public interface FriendsRepository extends JpaRepository<FriendsEntity, Integer> {
    
}
