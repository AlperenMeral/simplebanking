package com.eteration.simplebanking.model;

// This class is a place holder you can change the complete implementation


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {
    private String owner;
    @Id
    private String accountNumber;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    private double balance;
    private Date date;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    public Account(String owner, String accountNumber, double balance) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }


    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.date = new Date();
    }

    public Account() {
        this.date = new Date();
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount >= balance){
            throw new InsufficientBalanceException();
        }
        balance -= amount;
    }

    public Account post(DepositTransaction transaction){
        this.deposit(transaction.getAmount());
        this.transactions.add(transaction);
        return this;
    }

    public Account post(WithdrawalTransaction transaction) throws InsufficientBalanceException {
        this.withdraw(transaction.getAmount());
        this.transactions.add(transaction);
        return this;
    }

}
