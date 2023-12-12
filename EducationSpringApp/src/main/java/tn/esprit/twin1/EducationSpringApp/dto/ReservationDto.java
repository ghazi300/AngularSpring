package tn.esprit.twin1.EducationSpringApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReservationDto {
    private Long id;
    private Date anneeUniversitaire;
    private Long chambreId;
    private Integer userId;
    private String numeroChambre;
    private String typeChambre;
    private String userEmail;
}
