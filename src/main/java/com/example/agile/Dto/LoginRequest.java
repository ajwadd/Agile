package com.example.agile.Dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String mobileNumber;
    private String password;
}
