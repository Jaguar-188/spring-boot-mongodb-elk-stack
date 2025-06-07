package com.example.mdbspringboot.controller;

import com.example.mdbspringboot.model.entity.LoginRequest;
import com.example.mdbspringboot.service.MongoStudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class MongoStudentControllerTest {

    @InjectMocks
    private MongoStudentController mongoStudentController;

    @Mock
    private MongoStudentService mongoStudentService;

    LoginRequest loginRequest = new LoginRequest();

    @Test
    public void testLoginSuccess() {

        loginRequest.setUsername("Ruturaj");
        loginRequest.setPassword("abcdef");

        Mockito
                .when(mongoStudentService.login(loginRequest.getUsername(),loginRequest.getPassword()))
                .thenReturn(true);

        ResponseEntity<String> response = mongoStudentController.login(loginRequest);

        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertEquals("Login Successful",response.getBody());
    }

    @Test
    public void testLoginFailure(){

        loginRequest.setUsername("sdshdh");
        loginRequest.setPassword("dffd");

        Mockito
                .when(mongoStudentService.login(loginRequest.getUsername(),loginRequest.getPassword()))
                .thenReturn(false);

        ResponseEntity<String> response = mongoStudentController.login(loginRequest);

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED,response.getStatusCode());
        Assertions.assertEquals("Invalid credentials",response.getBody());

    }
}
