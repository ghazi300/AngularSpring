package tn.esprit.twin1.EducationSpringApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString(includeFieldNames = false)
@Table(name = "Etudiant")
public class Etudiant {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private long idEtudiant;
    @Column(name = "nomEt",nullable = false)
    private String nomEt;
    @Column(name = "prenomEt",nullable = false)
    private String prenomEt;
    @Column(name = "cin",nullable = false)
    private long cin;
    @Column(name = "ecole",nullable = false)
    private String ecole;
    @Column(name = "dateNaissance",nullable = false)
    private Date dateNaissance;



}
