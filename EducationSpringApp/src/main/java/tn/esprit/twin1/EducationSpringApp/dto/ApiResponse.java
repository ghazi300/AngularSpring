package tn.esprit.twin1.EducationSpringApp.dto;

import lombok.Data;

@Data

public class ApiResponse {
    private String message;
    public ApiResponse(String message) {
        this.message = message;
    }
}
