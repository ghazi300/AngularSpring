package tn.esprit.twin1.EducationSpringApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.twin1.EducationSpringApp.entities.Foyer;
import tn.esprit.twin1.EducationSpringApp.entities.Universite;
import tn.esprit.twin1.EducationSpringApp.repositories.FoyerRepositorie;
import tn.esprit.twin1.EducationSpringApp.repositories.UniversiteRepositorie;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoyerServiceImpl implements FoyerService {

    private final FoyerRepositorie foyerRepositorie;
    private final UniversiteRepositorie universiteRepositorie;

    @Override
    public Foyer addFoyer(Foyer foyer) {
        return foyerRepositorie.save(foyer);
    }

    @Override
    public List<Foyer> findAllFoyer() {
        return foyerRepositorie.findAll();
    }

    @Override
    public Foyer findFoyerById(Long id) {
        return foyerRepositorie.findById(id).isPresent() ? foyerRepositorie.findById(id).get() : null;
    }
    @Override
    public List<String> getFoyersNonAffectes() {
        List<String> nomFoyer = foyerRepositorie.getAllNomsFoyer();
        List<String> nomFoyersAffectes = universiteRepositorie.findFoyersAffectesPourUniversite();

        // Créer une copie de la liste des foyers non affectés
        List<String> foyersNonAffectes = new ArrayList<>(nomFoyer);

        // Supprimer les foyers affectés de la liste des foyers non affectés
        foyersNonAffectes.removeAll(nomFoyersAffectes);

        // Retourner la liste des foyers non affectés
        return foyersNonAffectes;
    }
    @Override
    public void deleteFoyerById(Long id) {
            foyerRepositorie.deleteById(id);
    }

    @Override
    public Foyer updateFoyer(long id, Foyer upfoyer) {
        Foyer foyer = foyerRepositorie.findById(id).orElse(null);

        foyer.setNomFoyer(upfoyer.getNomFoyer());
        foyer.setCapaciteFoyer(upfoyer.getCapaciteFoyer());


        return  foyerRepositorie.save(foyer);
    }

    @Override
    public Foyer addFoyerAndAsigneToUni(long idUni, Foyer foyer) {

        Universite universite = universiteRepositorie.findById(idUni).orElse(null);
        foyer.setUniversite(universite);
        return foyerRepositorie.save(foyer);
    }
    @Override
    public boolean isNomFoyerUnique(String nomFoyer) {
        // Votre logique pour vérifier l'unicité du nom d'université
        // Retournez true si le nom est unique, false sinon
        return foyerRepositorie.findByNomFoyer(nomFoyer).isEmpty();
    }

    @Override
    public Foyer getFoyerByNom(String nomFoyer) {
        return foyerRepositorie.findByNomFoyer(nomFoyer).get();
    }
}
