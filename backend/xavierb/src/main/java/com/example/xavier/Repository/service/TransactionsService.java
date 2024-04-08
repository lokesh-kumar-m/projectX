package com.example.xavier.Repository.service;

import java.util.List;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

import com.example.xavier.Repository.TransactionsRepository;
import com.example.xavier.model.TransactionEntity;

@Service
public class TransactionsService {
    private TransactionsRepository transactionsServices;

    public TransactionsService(TransactionsRepository transactionsServices) {
        this.transactionsServices = transactionsServices;
    }
    
    public void addTransaction(double amount,String currency,String type,String split,List<String> members,LocalDate on ){
        TransactionEntity log=new TransactionEntity();
        log.setAmount(amount);
        log.setCurrency(currency);
        log.setExpenseType(type);
        log.setOnDate(on);
        log.setMembers(members);
        log.setSplit(split);
        transactionsServices.save(log);
    }
}
