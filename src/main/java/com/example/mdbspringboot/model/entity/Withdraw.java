package com.example.mdbspringboot.model.entity;

import lombok.Data;

@Data
public class Withdraw {

    private String accountNumber;
    private double amountToBeWithdraw;
}
