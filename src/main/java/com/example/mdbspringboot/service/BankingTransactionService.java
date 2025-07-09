package com.example.mdbspringboot.service;

import com.example.mdbspringboot.exceptions.AmountCanNotBeNegativeOrZero;
import com.example.mdbspringboot.exceptions.IncorrectAccountNumberException;
import com.example.mdbspringboot.exceptions.InsufficientAmountException;
import com.example.mdbspringboot.model.entity.*;
import com.example.mdbspringboot.repository.AccountRepository;
import com.example.mdbspringboot.repository.TransactionsRepository;
import com.example.mdbspringboot.utils.BankingUtility;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BankingTransactionService {

    private AccountRepository accountRepository;
    private TransactionsRepository transactionsRepository;

    private static final String bankHeader = "myBank";

    public BankingTransactionService(AccountRepository accountRepository, TransactionsRepository transactionsRepository){
        this.accountRepository = accountRepository;
        this.transactionsRepository = transactionsRepository;
    }

    public String createAccount(Account accountDetails) {

        try {
            accountDetails.setAccountNumber(BankingUtility.getRandomAccountNumber());
            accountDetails.setBalance(0.0);
            accountDetails.setId(UUID.randomUUID().toString());
            accountDetails.setUserId(BankingUtility.getUserId(accountDetails.getFirstName(),accountDetails.getLastName()));
            accountDetails.setPassword(BankingUtility.encryptPassword(accountDetails.getPassword()));
            accountRepository.save(accountDetails);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "Account created Successfully for user " + accountDetails.getFirstName();
    }

    public DepositResponse depositAmount(Deposit depositDetails) {

        if(depositDetails.getAmountToDeposit() <= 0){
            throw new AmountCanNotBeNegativeOrZero("Amount can not be zero or negative");
        }
        DepositResponse depositResponse = new DepositResponse();
        Transactions transaction = new Transactions();
        Account account = findAccountByAccountNumber(depositDetails.getAccountNumber());
        account.setBalance(account.getBalance() + depositDetails.getAmountToDeposit());
        Account account1 = accountRepository.save(account);
        transaction.setId(UUID.randomUUID().toString());
        transaction.setTransactionId(bankHeader + UUID.randomUUID());
        transaction.setTransactionType("Credit");
        transaction.setSender(depositDetails.getAccountNumber());
        transaction.setAction("Deposit");
        transaction.setStatus("Success");
        transactionsRepository.save(transaction);
        depositResponse.setDepositMessage("Successfully Deposited to the account");
        depositResponse.setBalance(account1.getBalance());
        return depositResponse;
    }

    public String getBalance(String accountNumber) {

        return String.valueOf(accountRepository.findByAccountNumber(accountNumber).getBalance());
    }

    public String sendMoney(MoneyTransfer moneyTransfer) {

        if(moneyTransfer.getAmount() < 0.0 || moneyTransfer.getAmount() == 0.0){
            throw new AmountCanNotBeNegativeOrZero("Amount can not be zero or negative");
        }
        Account sender = accountRepository.findByAccountNumber(moneyTransfer.getFromAccount());
        if(sender.getBalance() < moneyTransfer.getAmount())
        {
            throw new InsufficientAmountException("Insufficient amount. Money did not withdrawn");
        }
        Account receiver = accountRepository.findByAccountNumber(moneyTransfer.getToAccount());
        sender.setBalance(sender.getBalance() - moneyTransfer.getAmount());
        receiver.setBalance(receiver.getBalance() + moneyTransfer.getAmount());
        accountRepository.save(sender);
        accountRepository.save(receiver);
        Transactions transaction = new Transactions();
        transaction.setId(UUID.randomUUID().toString());
        transaction.setTransactionId(bankHeader + UUID.randomUUID());
        transaction.setTransactionType("Credit/Debit");
        transaction.setSender(moneyTransfer.getFromAccount());
        transaction.setReceiver(moneyTransfer.getToAccount());
        transaction.setAction("Transfer");
        transaction.setStatus("Success");
        transactionsRepository.save(transaction);
        return "Payment Successful.";
    }

    public WithdrawResponse withdrawAmount(Withdraw withdrawDetails) {

        if(withdrawDetails.getAmountToBeWithdraw() < 0.0 || withdrawDetails.getAmountToBeWithdraw() == 0.0){
            throw new AmountCanNotBeNegativeOrZero("Amount can not be zero or negative");
        }
        Account account = findAccountByAccountNumber(withdrawDetails.getAccountNumber());
        if(account.getBalance() < withdrawDetails.getAmountToBeWithdraw())
        {
            throw new InsufficientAmountException("Insufficient amount. Money did not withdrawn.");
        }
        account.setBalance(account.getBalance() - withdrawDetails.getAmountToBeWithdraw());
        Account account1 = accountRepository.save(account);
        Transactions transaction = new Transactions();
        transaction.setId(UUID.randomUUID().toString());
        transaction.setTransactionId(bankHeader + UUID.randomUUID());
        transaction.setTransactionType("Debit");
        transaction.setSender(withdrawDetails.getAccountNumber());
        transaction.setAction("Withdraw");
        transaction.setStatus("Success");
        transactionsRepository.save(transaction);
        WithdrawResponse withdrawResponse = new WithdrawResponse();
        withdrawResponse.setWithdrawMessage("Successfully Withdrawn from the account");
        withdrawResponse.setBalance(account1.getBalance());
        return withdrawResponse;
    }

    private Account findAccountByAccountNumber(String accountNumber) {

        return Optional.ofNullable(accountRepository.findByAccountNumber(accountNumber))
                .orElseThrow(() -> new IncorrectAccountNumberException("Incorrect account number or is not valid."));
    }
}
