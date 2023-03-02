package com.eteration.simplebanking.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


// This class is a place holder you can change the complete implementation
@Entity
@Data
public abstract class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private double amount;
    private String approvalCode;


    public Transaction(double amount) {
        this.date = new Date();
        this.amount = amount;
    }

    public Transaction() {

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public abstract String getType();

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }
}
