package com.example.xavier.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.xavier.model.TransactionEntity;

public interface TransactionsRepository extends JpaRepository<TransactionEntity,Integer>{
    
}
