package com.example.xavier.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.xavier.model.FriendsEntity;

import jakarta.transaction.Transactional;

public interface FriendsRepository extends JpaRepository<FriendsEntity, Integer> {
    @Modifying
    @Transactional
    @Query("update FriendsEntity e set e.amount=:amount where e.id=:id")
    void updateAmount(@Param(value="id")int id, @Param(value="amount")double amount);
}
