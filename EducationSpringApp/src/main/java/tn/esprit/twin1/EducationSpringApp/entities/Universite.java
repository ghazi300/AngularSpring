package tn.esprit.twin1.EducationSpringApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString(includeFieldNames = false)
@Table(name = "Universite")
public class Universite {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private long idUniversite;

    @Column(name = "nomUniversite",nullable = false)
    private String nomUniversite;

    @Column(name = "adresse",nullable = false)
    private String adresse;
    @Column(name = "logo")
    private String logo;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Foyer foyer;



}
