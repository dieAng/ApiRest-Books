package com.example.apirestbooks.request.auth;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class AuthRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;
    private String usuario;
    private String contrasenia;
}
