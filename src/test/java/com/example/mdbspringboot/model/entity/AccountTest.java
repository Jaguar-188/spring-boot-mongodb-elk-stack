package com.example.mdbspringboot.model.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;

public class AccountTest {

    @Test
    public void testGettersAndSetters(){

        Account account = new Account();
        String password = "123";
        String actualPassword = Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8));
        Random random = new Random();
        account.setBalance(1000);
        account.setAccountNumber(String.valueOf(random.nextInt(100)));
        account.setPassword(actualPassword);
        account.setFirstName("Ruturaj");
        account.setLastName("Lokhande");

        byte[] byteStream = Base64.getDecoder().decode(actualPassword);
        String expectedPassword = new String(byteStream);
        assertEquals("Ruturaj",account.getFirstName());
        assertEquals("Lokhande",account.getLastName());
        assertTrue(Integer.parseInt(account.getAccountNumber()) <= 100);
        assertEquals(expectedPassword,password);
    }
}
