package com.example.mdbspringboot.model.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value = "account")
public class Account {

    private String id;
    private String userId;
    private String firstName;
    private String lastName;
    private String password;
    private double balance;
    private String accountNumber;

}
