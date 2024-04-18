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
        logger.info("size"+log.getMembers().size());
        log.getMembers().forEach((key,val)->logger.info("id:"+key+"val:"+val));

        HashMap<Character, String> charType
            = new HashMap<Character, String>();
 
        // Inserting data in the hash map.
        charType.put('J', "Java");
        charType.put('H', "Hibernate");
        charType.put('P', "Python");
        charType.put('A', "Angular");
 
        // Iterating HashMap through forEach and
        // Printing all. elements in a Map
        charType.forEach(
            (key, value)
                -> System.out.println(key + " = " + value));

        // double amount,String currency,String type,String split,List<String> members,LocalDate on
        transactionService.addTransaction(log.getAmount(),log.getCurrency(), log.getExpenseType(),log.getSplit(), log.getMembers(), log.getOnDate(),log.getPaidBy(),log.isFlag(), log.getExchangeRate());
    }
}
