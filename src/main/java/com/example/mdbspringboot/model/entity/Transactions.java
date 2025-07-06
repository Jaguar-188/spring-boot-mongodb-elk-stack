package com.example.mdbspringboot.model.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value = "transactions")
public class Transactions {

    private String id;
    private String transactionId;
    private String action;
    private String status;
    private String sender;
    private String receiver;
    private String transactionType;
}
