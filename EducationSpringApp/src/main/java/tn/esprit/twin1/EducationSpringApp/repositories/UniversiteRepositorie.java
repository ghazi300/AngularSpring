package tn.esprit.twin1.EducationSpringApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.twin1.EducationSpringApp.entities.Universite;

import java.util.List;

public interface UniversiteRepositorie  extends JpaRepository<Universite,Long> {
    List<Universite> findByNomUniversite(String nomUniversite);
    @Query("SELECT u.foyer.nomFoyer FROM Universite u WHERE u.foyer IS NOT NULL   ")
    List<String> findFoyersAffectesPourUniversite();
}
