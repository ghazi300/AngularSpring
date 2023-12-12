package tn.esprit.twin1.EducationSpringApp.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esprit.twin1.EducationSpringApp.entities.AddBlocRequest;
import tn.esprit.twin1.EducationSpringApp.entities.Bloc;
import tn.esprit.twin1.EducationSpringApp.entities.Foyer;
import tn.esprit.twin1.EducationSpringApp.repositories.BlocRepositorie;
import tn.esprit.twin1.EducationSpringApp.repositories.FoyerRepositorie;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlocServiceImpl implements BlocService{

    private final BlocRepositorie blocRepositorie;
    private final FoyerRepositorie foyerRepositorie;

    @Override
    public Bloc addBloc(Bloc bloc  ) {
        Foyer foyer= bloc.getFoyer();
        bloc.setFoyer(foyer);
        return blocRepositorie.save(bloc);
    }

    @Override
    public List<Bloc> findByNomFoyer() {
        return null;
    }


    @Override
    public List<Bloc> findAllBloc() {
        return blocRepositorie.findAll();
    }

    @Override
    public Bloc findBlocById(Long id) {
        return blocRepositorie.findById(id).isPresent() ? blocRepositorie.findById(id).get() : null;
    }



    @Override
    public void deleteBlocById(long id) {
        blocRepositorie.deleteById(id);

    }


    @Override
    public Bloc updateBloc(long id, AddBlocRequest upbloc) {
        Bloc bloc = blocRepositorie.findById(id).orElse(null);

        Foyer foyer = foyerRepositorie.findByNomFoyer(upbloc.getNomFoyer()).orElse(null);

        bloc.setNomBloc(upbloc.getNomBloc());
        bloc.setCapaciteBloc(upbloc.getCapaciteBloc());
        bloc.setFoyer(foyer);

        return  blocRepositorie.save(bloc);
    }

    @Override
    public ResponseEntity<String> addBlocToFoyer(AddBlocRequest request) {

        Foyer foyer = foyerRepositorie.findByNomFoyer(request.getNomFoyer())
                .orElseThrow(() -> new EntityNotFoundException("Foyer not found"));

        Bloc bloc = new Bloc();

        bloc.setNomBloc(request.getNomBloc());
        bloc.setCapaciteBloc(request.getCapaciteBloc());
        bloc.setFoyer(foyer);

        foyer.getBlocSet().add(bloc);

        foyerRepositorie.save(foyer);

        return ResponseEntity.ok("Bloc added to Foyer successfully");

    }


}
