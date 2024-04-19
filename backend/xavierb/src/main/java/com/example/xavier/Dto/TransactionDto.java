package com.example.xavier.Dto;

import java.time.LocalDate;


public class TransactionDto {
    private String expenseType;
    private double amount;
    private String currency;
    private String mapString;
    public String getMapString() {
        return mapString;
    }
    public void setMapString(String mapString) {
        this.mapString = mapString;
    }
    private LocalDate onDate;
    private String split;
    private String paidBy;
    private double exchangeRate;
    private boolean flag;
    public TransactionDto(String expenseType, double amount, String currency, String mapString, LocalDate onDate,
            String split, String paidBy, double exchangeRate, boolean flag) {
        this.expenseType = expenseType;
        this.amount = amount;
        this.currency = currency;
        this.mapString = mapString;
        this.onDate = onDate;
        this.split = split;
        this.paidBy = paidBy;
        this.exchangeRate = exchangeRate;
        this.flag = flag;
    }
    public double getExchangeRate() {
        return exchangeRate;
    }
    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
    public String getPaidBy() {
        return paidBy;
    }
    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
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
    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
