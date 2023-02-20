package com.eteration.simplebanking.model;

public class CheckTransaction extends Transaction {
    public CheckTransaction(double amount) {
        super(amount);
    }

    public String getType() {
        return "Check";
    }
}