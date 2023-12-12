package tn.esprit.twin1.EducationSpringApp.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Bloc")
public class Bloc {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long idBloc;
    @Column(name = "nomBloc",nullable = false)
    private String nomBloc;
    @Column(name = "capaciteBloc",nullable = false)
    private long capaciteBloc;

    @ManyToOne
    @JsonIgnore
    Foyer foyer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="bloc")
    @JsonIgnore
    private Set<Chambre> chambreSet  = new HashSet<>();

}
