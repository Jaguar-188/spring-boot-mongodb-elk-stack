package com.example.mdbspringboot.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;

public class BankingUtility {

    public static String getRandomAccountNumber(){

        Random random = new Random();
        return String.valueOf(random.nextInt(10000));
    }

    public static String getUserId(String firstName, String lastName){

        String format = "@mybank.com";
        return firstName.toLowerCase() + "." + lastName.toLowerCase() + format;
    }

    public static String encryptPassword(String password) {

        return Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8));
    }
}
