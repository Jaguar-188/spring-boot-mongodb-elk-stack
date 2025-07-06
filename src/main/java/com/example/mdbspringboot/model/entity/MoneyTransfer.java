package com.example.mdbspringboot.model.entity;

import lombok.Data;

import javax.validation.constraints.NegativeOrZero;

@Data
public class MoneyTransfer {

    private String fromAccount;
    private String toAccount;
    private double amount;
}
