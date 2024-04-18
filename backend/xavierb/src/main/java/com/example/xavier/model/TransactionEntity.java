package com.example.xavier.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class TransactionEntity {
    public TransactionEntity(int id, String expenseType, double amount, String currency, List<String> members,
            LocalDate onDate, String split, String paidBy) {
        this.id = id;
        this.expenseType = expenseType;
        this.amount = amount;
        this.currency = currency;
        this.members = members;
        this.onDate = onDate;
        this.split = split;
        this.paidBy = paidBy;
    }

    @Id
    @GeneratedValue
    private int id;
    private String expenseType;
    private double amount;
    private String currency;
    private List<String> members;
    private LocalDate onDate;
    private String split;
    private String paidBy;
    public String getPaidBy() {
        return paidBy;
    }
    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public TransactionEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public LocalDate getOnDate() {
        return onDate;
    }

    public void setOnDate(LocalDate onDate) {
        this.onDate = onDate;
    }

    public String getSplit() {
        return split;
    }

    public void setSplit(String split) {
        this.split = split;
    }
}
