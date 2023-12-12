package tn.esprit.twin1.EducationSpringApp.dto;

import lombok.Data;

@Data

public class JwtAuthenticationResponse {
    private String token;
    private String refreshToken;
}
