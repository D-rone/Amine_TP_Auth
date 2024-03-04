package com.esisba.tp_auth.API;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String password;
    private String address;
}
