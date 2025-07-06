package com.example.mdbspringboot.controller;

import com.example.mdbspringboot.model.entity.*;
import com.example.mdbspringboot.service.BankingTransactionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rest/v1/banking-transaction/api")
public class BankingTransactionController {

    private BankingTransactionService bankingTransactionService;
    public BankingTransactionController(BankingTransactionService bankingTransactionService){
        this.bankingTransactionService = bankingTransactionService;
    }
    @PostMapping("/createAccount")
    public String createAccount(@RequestBody Account accountDetaiils){

        return bankingTransactionService.createAccount(accountDetaiils);
    }

    @PostMapping("/depositAmount")
    public DepositResponse depositAmount(@RequestBody Deposit depositDetails){

        return bankingTransactionService.depositAmount(depositDetails);
    }

    @PostMapping("/withdrawAmount")
    public WithdrawResponse withdrawAmount(@RequestBody Withdraw withdrawDetails){

        return bankingTransactionService.withdrawAmount(withdrawDetails);
    }

    @GetMapping("/getBalance")
    public String getBalance(@RequestParam String accountNumber){

        return bankingTransactionService.getBalance(accountNumber);
    }

    @PostMapping("/sendMoney")
    public String sendMoney(@RequestBody MoneyTransfer moneyTransfer){

        return bankingTransactionService.sendMoney(moneyTransfer);
    }
}
