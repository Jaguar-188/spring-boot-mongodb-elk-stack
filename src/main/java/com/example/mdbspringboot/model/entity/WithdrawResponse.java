package com.example.mdbspringboot.model.entity;

import lombok.Data;

@Data
public class WithdrawResponse {

    private String withdrawMessage;
    private double balance;
}
