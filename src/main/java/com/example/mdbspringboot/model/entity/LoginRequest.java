package com.example.mdbspringboot.model.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class LoginRequest {

    private String username;
    private String password;
}
