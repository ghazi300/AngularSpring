package tn.esprit.twin1.EducationSpringApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserReservationRequest {
    private Integer id;
    private String email;
    private String username;

    public UserReservationRequest(String email, String username) {
        this.email = email;
        this.username = username;
    }
}
