package com.skillsoft.junit;

public class AccountDetails {

    private String name;
    private Double accountNumber;
    private Integer customerID;
    private Double balance;
    private String accountType;

    public AccountDetails(String name, double accountNumber, int customerID, double balance, String accountType) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.customerID = customerID;
        this.balance = balance;
        this.accountType = accountType;
    }

    public String getName() {
        return name;
    }

    public Double getAccountNumber() {
        return accountNumber;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public Double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void deposit(double depositAmount) {
        balance = balance + depositAmount;

    }

    public boolean withdraw(double withdrawAmount) {
        
        if (withdrawAmount > balance) {
            System.out.println("Insufficient Funds");
            return false;

        } else {
            balance = balance - withdrawAmount;
            return true;
        }
    }
}