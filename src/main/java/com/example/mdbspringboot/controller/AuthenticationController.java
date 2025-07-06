package com.example.mdbspringboot.controller;

import com.example.mdbspringboot.config.Logging;
import com.example.mdbspringboot.model.entity.LoginRequest;
import com.example.mdbspringboot.service.MongoStudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/v1/authentication/api")
public class AuthenticationController {


    private MongoStudentService mongoStudentService;

    private final Logging log = Logging.getLog();
    public AuthenticationController(MongoStudentService mongoStudentService){
        this.mongoStudentService = mongoStudentService;
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        log.info("Validating Login");
        boolean isAuthenticated = mongoStudentService.login(request.getUsername(), request.getPassword());
        if (isAuthenticated) {
            log.info("Successful Login");
            return ResponseEntity.ok("Login Successful");
        }
        else {
            log.error("Login Unsuccessful");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

    }
}
