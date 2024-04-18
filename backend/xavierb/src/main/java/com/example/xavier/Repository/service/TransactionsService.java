package com.example.xavier.Repository.service;

import java.util.HashMap;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

import com.example.xavier.Repository.TransactionsRepository;
import com.example.xavier.model.TransactionEntity;

@Service
public class TransactionsService {
    private TransactionsRepository transactionsServices;
    private FriendRepositoryServices friendsRepo;

    public TransactionsService(TransactionsRepository transactionsServices,FriendRepositoryServices friendsRepo) {
        this.transactionsServices = transactionsServices;
        this.friendsRepo=friendsRepo;
    }
    
    public void addTransaction(double amount,String currency,String type,String split,HashMap<Integer,String> members,LocalDate on,String paidBy,boolean flag, double exchangeRate ){
        ArrayList<String> mem=new ArrayList<>();
        members.forEach((key,val)->mem.add(val));
        TransactionEntity log=new TransactionEntity();
        log.setAmount(amount);
        log.setCurrency(currency);
        log.setExpenseType(type);
        log.setOnDate(on);
        log.setMembers(mem);
        log.setSplit(split);
        log.setPaidBy(paidBy);
        transactionsServices.save(log);
        double splitAmount=amount/mem.size();
        if(flag){
            members.forEach((key,val)-> friendsRepo.updateAmount(key, splitAmount));
        }
        else{
            members.forEach((key,val)-> {
                if(val==paidBy){
                    friendsRepo.updateAmount(key, friendsRepo.getOneBalance(key)-splitAmount);
                }
            });
        }
    }
}
