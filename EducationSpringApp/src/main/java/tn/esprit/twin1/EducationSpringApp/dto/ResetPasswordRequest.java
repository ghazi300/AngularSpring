package tn.esprit.twin1.EducationSpringApp.dto;

import lombok.Data;

@Data

public class ResetPasswordRequest {

    private String email;
    private String otp;
    private String newPassword;

}
