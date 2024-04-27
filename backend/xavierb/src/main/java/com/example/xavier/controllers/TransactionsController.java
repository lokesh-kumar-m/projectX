package com.example.xavier.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.xavier.Dto.TransactionDto;
import com.example.xavier.Repository.service.TransactionsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class TransactionsController {
    private TransactionsService transactionService;
    private Logger logger=LoggerFactory.getLogger(getClass());

    public TransactionsController(TransactionsService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(path="/user/add/log")
    public void addNewLog(@RequestBody TransactionDto log) throws JsonMappingException, JsonProcessingException{
        //logger.info("out map");
        //logger.info("size"+log.getMapString());
        // double amount,String currency,String type,String split,List<String> members,LocalDate on
        transactionService.addTransaction(
            log.getAmount(),
            log.getCurrency(), 
            log.getExpenseType(),
            log.getSplit(), 
            log.getMapString(), 
            log.getOnDate(),
            log.getPaidBy(),
            log.isFlag(), 
            log.getExchangeRate(),
            log.getAdmin()
            );
    }
}
