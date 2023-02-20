package com.eteration.simplebanking.model;


import javax.persistence.Entity;

// This class is a place holder you can change the complete implementation
@Entity
public class DepositTransaction  extends Transaction {
        public DepositTransaction(double amount) {
            super(amount);
        }

    public DepositTransaction() {
    }

    public String getType() {
            return "Deposit";
        }

}
