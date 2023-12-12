package tn.esprit.twin1.EducationSpringApp.services;

import org.springframework.http.ResponseEntity;
import tn.esprit.twin1.EducationSpringApp.entities.AddBlocRequest;
import tn.esprit.twin1.EducationSpringApp.entities.AddChambreRequest;
import tn.esprit.twin1.EducationSpringApp.entities.Chambre;

import java.util.List;

public interface ChambreService {
    Chambre addChambre(Chambre  chambre);

    List<Chambre> findAllChambre();

    Chambre findChambreById(Long id);

    void deleteChambreById(Long id);
    Chambre updateChambre(long id, AddChambreRequest upchambre);
    ResponseEntity<String> addChambreToBloc(AddChambreRequest request);

}
