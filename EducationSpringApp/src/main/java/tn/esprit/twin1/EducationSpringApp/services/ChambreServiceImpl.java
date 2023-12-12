package tn.esprit.twin1.EducationSpringApp.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esprit.twin1.EducationSpringApp.entities.AddChambreRequest;
import tn.esprit.twin1.EducationSpringApp.entities.Bloc;
import tn.esprit.twin1.EducationSpringApp.entities.Chambre;
import tn.esprit.twin1.EducationSpringApp.entities.Foyer;
import tn.esprit.twin1.EducationSpringApp.repositories.BlocRepositorie;
import tn.esprit.twin1.EducationSpringApp.repositories.ChambreRepositorie;
import javax.persistence.EntityNotFoundException;

 import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class  ChambreServiceImpl implements ChambreService {

    private final ChambreRepositorie chambreRepositorie;
    private final BlocRepositorie blocRepositorie;

    @Override
    public Chambre addChambre(Chambre chambre) {
        return chambreRepositorie.save(chambre);
    }

    @Override
    public List<Chambre> findAllChambre() {
        return chambreRepositorie.findAll();
    }

    @Override
    public Chambre findChambreById(Long id) {
        return chambreRepositorie.findById(id).isPresent() ? chambreRepositorie.findById(id).get() : null;
    }

    @Override
    public void deleteChambreById(Long id) {
chambreRepositorie.deleteById(id);
    }

    @Override
    public Chambre updateChambre(long id, AddChambreRequest upchambre) {
        Chambre chambre = chambreRepositorie.findById(id).orElse(null);
        Bloc bloc = blocRepositorie.findByNomBloc(upchambre.getNomBloc());

        chambre.setNumeroChambre(upchambre.getNumChambre());
        chambre.setTypeChambre(upchambre.getTypeChambre());
          chambre.setBloc(bloc);

        return  chambreRepositorie.save(chambre);
    }

    @Override
    public ResponseEntity<String> addChambreToBloc(AddChambreRequest request) {

        Bloc bloc = blocRepositorie.findByNomBloc(request.getNomBloc());

        Chambre chambre = new Chambre();

        chambre.setNumeroChambre(request.getNumChambre());
        chambre.setTypeChambre(request.getTypeChambre());
        chambre.setBloc(bloc);

        bloc.getChambreSet().add(chambre);
        blocRepositorie.save(bloc);

        return ResponseEntity.ok("Chambre added to Bloc successfully");
    }
}
