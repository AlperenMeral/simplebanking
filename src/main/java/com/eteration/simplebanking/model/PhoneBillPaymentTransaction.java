package com.eteration.simplebanking.model;

public class PhoneBillPaymentTransaction extends WithdrawalTransaction {

    private String payee;
    private String phoneNumber;


    public PhoneBillPaymentTransaction(String payee, String phoneNumber, double amount) {
        //super(amount);
        this.phoneNumber = phoneNumber;
        this.payee = payee;
    }

}
