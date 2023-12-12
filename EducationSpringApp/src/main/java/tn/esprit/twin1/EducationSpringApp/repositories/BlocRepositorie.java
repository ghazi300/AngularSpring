package tn.esprit.twin1.EducationSpringApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.twin1.EducationSpringApp.entities.Bloc;
import tn.esprit.twin1.EducationSpringApp.entities.Chambre;

import java.util.Set;

public interface BlocRepositorie  extends JpaRepository<Bloc,Long> {
    Bloc findByNomBloc(String nomBloc);
    @Query("SELECT c from Chambre c WHERE c.bloc.nomBloc=:nomBloc ")
    Set<Chambre> getByNomBloc(@Param("nomBloc") String nomBloc);

}
