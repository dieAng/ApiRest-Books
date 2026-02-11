package com.example.apirestbooks.responses.token;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TokenResponse {
    private String jwtToken;
}
