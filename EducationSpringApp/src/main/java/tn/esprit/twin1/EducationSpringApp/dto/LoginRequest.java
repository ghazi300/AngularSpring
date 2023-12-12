package tn.esprit.twin1.EducationSpringApp.dto;

import lombok.Data;

@Data

public class LoginRequest {
    private String email;
    private String password;
}
