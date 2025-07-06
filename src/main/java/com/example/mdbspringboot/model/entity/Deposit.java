package com.example.mdbspringboot.model.entity;

import lombok.Data;
import org.apache.kafka.common.protocol.types.Field;

@Data
public class Deposit {

    private String accountNumber;
    private double amountToDeposit;
}
