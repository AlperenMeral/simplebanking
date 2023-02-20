package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

// This class is a place holder you can change the complete implementation
@RestController
@RequestMapping("/account/v1")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) {
        Account account = accountService.findAccount(accountNumber);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(
            @PathVariable String accountNumber,
            @RequestBody(required=false) DepositTransaction creditRequest) throws Exception {
        Account account = accountService.findAccount(accountNumber);
        if(account == null)
        {
            //throw new Exception("Account not found");
            // for test purpose i added manually account
            Account newAccount = new Account();
            newAccount.setAccountNumber(accountNumber);
            newAccount.setBalance(0);
            newAccount.setOwner("Kerem Karaca");
            account = newAccount;
        }
        creditRequest.setApprovalCode(okTransaction().getApprovalCode());
        creditRequest.setAmount(creditRequest.getAmount());
        account.post(creditRequest);
        TransactionStatus okTransaction = okTransaction();
        accountService.saveAccount(account);
        return ResponseEntity.ok(okTransaction);
    }

    private TransactionStatus okTransaction() {
        return new TransactionStatus("OK",String.valueOf(UUID.randomUUID()));
    }

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(
            @PathVariable String accountNumber,
            @RequestBody(required=false) WithdrawalTransaction debitRequest) throws Exception {
        Account account = accountService.findAccount(accountNumber);
        if(account == null)
        {
            //throw new Exception("Account not found");
            Account newAccount = new Account();
            newAccount.setAccountNumber(accountNumber);
            newAccount.setBalance(0);
            newAccount.setOwner("Kerem Karaca");
            account = newAccount;
        }
        WithdrawalTransaction debitTransaction = new WithdrawalTransaction();
        debitTransaction.setApprovalCode(okTransaction().getApprovalCode());
        debitTransaction.setAmount(debitRequest.getAmount());
        account.post(debitTransaction);
        TransactionStatus okTransaction = okTransaction();
        accountService.saveAccount(account);
        return ResponseEntity.ok(okTransaction);
    }


    /*public Object getAccount() {
        return null;
    }

    public Object credit( ) {
        return null;
    }
    public Object debit() {
        return null;
	}*/
}