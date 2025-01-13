package it.epicode.esercizio.W7.D1.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
