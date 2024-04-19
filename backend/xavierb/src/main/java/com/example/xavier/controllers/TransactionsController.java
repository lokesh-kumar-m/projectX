package com.example.xavier.controllers;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.xavier.Dto.TransactionDto;
import com.example.xavier.Repository.service.TransactionsService;

@RestController
public class TransactionsController {
    private TransactionsService transactionService;
    private Logger logger=LoggerFactory.getLogger(getClass());

    public TransactionsController(TransactionsService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(path="/user/add/log")
    public void addNewLog(@RequestBody TransactionDto log){
        logger.info("out map");
        logger.info("size"+log.getMapString());
        // double amount,String currency,String type,String split,List<String> members,LocalDate on
        // transactionService.addTransaction(log.getAmount(),log.getCurrency(), log.getExpenseType(),log.getSplit(), log.getMembers(), log.getOnDate(),log.getPaidBy(),log.isFlag(), log.getExchangeRate());
    }
}
