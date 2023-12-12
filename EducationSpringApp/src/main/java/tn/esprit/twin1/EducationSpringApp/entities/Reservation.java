package tn.esprit.twin1.EducationSpringApp.entities;


import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString(includeFieldNames = false)
@Table(name = "Reservation")
public class Reservation {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Long idReservation;

    @Temporal(TemporalType.DATE)
    @Column(name = "anneUniversitaire",nullable = false)
    private Date anneeUniversaire;

    @ManyToOne
    Chambre chambre ;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}
