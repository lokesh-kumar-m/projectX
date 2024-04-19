package com.example.xavier.Repository.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import java.time.LocalDate;

import com.example.xavier.Repository.TransactionsRepository;
import com.example.xavier.model.TransactionEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TransactionsService {
    private TransactionsRepository transactionsServices;
    private FriendRepositoryServices friendsRepo;

    public TransactionsService(TransactionsRepository transactionsServices,FriendRepositoryServices friendsRepo) {
        this.transactionsServices = transactionsServices;
        this.friendsRepo=friendsRepo;
    }
    
    public void addTransaction(double amount,String currency,String type,String split,String membersString,LocalDate on,String paidBy,boolean flag, double exchangeRate,String Username ) throws JsonMappingException, JsonProcessingException{
        ArrayList<String> mem=new ArrayList<>();
        HashMap<Integer,String> members=new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        List<List<String>> pairs = objectMapper.readValue(membersString, new TypeReference<List<List<String>>>() {});
        for(List<String> pair: pairs){
            members.put(Integer.parseInt(pair.get(0)), pair.get(1));
            mem.add(pair.get(1));
        }
        TransactionEntity log=new TransactionEntity();
        log.setAmount(amount);
        log.setCurrency(currency);
        log.setExpenseType(type);
        log.setOnDate(on);
        log.setMembers(mem);
        log.setSplit(split);
        log.setPaidBy(paidBy);
        log.setUsername(Username);
        transactionsServices.save(log);
        double splitAmount=amount/(mem.size()+1);
        if(flag){
            members.forEach((key,val)-> friendsRepo.updateAmount(key, friendsRepo.getOneBalance(key)+splitAmount));
        }
        else{
            members.forEach((key,val)-> {
                if(val.equals(paidBy)){
                    friendsRepo.updateAmount(key, friendsRepo.getOneBalance(key)-splitAmount);
                }
            });
        }
    }
}
