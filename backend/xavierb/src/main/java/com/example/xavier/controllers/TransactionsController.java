package com.example.xavier.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.xavier.Dto.TransactionDto;
import com.example.xavier.Repository.service.TransactionsService;

@RestController
public class TransactionsController {
    private TransactionsService transactionService;

    public TransactionsController(TransactionsService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(path="/user/add/log")
    public void addNewLog(@RequestBody TransactionDto log){
        // double amount,String currency,String type,String split,List<String> members,LocalDate on
        transactionService.addTransaction(log.getAmount(),log.getCurrency(), log.getExpenseType(),log.getSplit(), log.getMembers(), log.getOnDate(),log.getPaidBy(),log.isFlag(), log.getExchangeRate());
    }
}
