package tn.esprit.twin1.EducationSpringApp.entities;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(includeFieldNames = false)
@Table(name = "Foyer")
public class Foyer {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long idFoyer;
    @Column(name = "nomFoyer",nullable = false)
    private String nomFoyer;
    @Column(name = "capaciteFoyer",nullable = false)
    private long capaciteFoyer;

    @OneToOne(mappedBy = "foyer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Universite universite;


    @OneToMany(cascade = CascadeType.ALL, mappedBy="foyer",fetch = FetchType.EAGER)
    private Set<Bloc> blocSet;
}
