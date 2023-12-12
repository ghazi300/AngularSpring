package tn.esprit.twin1.EducationSpringApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.twin1.EducationSpringApp.entities.Bloc;
import tn.esprit.twin1.EducationSpringApp.entities.Foyer;
import tn.esprit.twin1.EducationSpringApp.repositories.FoyerRepositorie;
import tn.esprit.twin1.EducationSpringApp.services.FoyerService;

import java.util.List;
import java.util.Set;

@RequestMapping("/foyer")
@RequiredArgsConstructor
@RestController
public class FoyerController {
    private final FoyerService foyerService;
    private final FoyerRepositorie foyerRepositorie;



    @GetMapping("/checkUniqueName")
    public ResponseEntity<Boolean> checkUniqueName(@RequestParam String nomFoyer) {
        boolean isUnique = foyerService.isNomFoyerUnique(nomFoyer);
        return ResponseEntity.ok(isUnique);
    }

    @PostMapping("/new")
    public Foyer addFoyer(@RequestBody Foyer foyer) {
        return foyerService.addFoyer(foyer);
    }

    @PutMapping("/update/{idFoyer}")
    public Foyer updateFoyer(@PathVariable long idFoyer,@RequestBody Foyer foyer) {
        return foyerService.updateFoyer(idFoyer,foyer);
    }

    @GetMapping("/getId/{idFoyer}")
    public Foyer getId(@PathVariable long idFoyer) {
        return foyerService.findFoyerById(idFoyer);
    }

    @DeleteMapping("/delete/{idFoyer}")
    public void deleteFoyer(@PathVariable long idFoyer) {
     foyerService.deleteFoyerById(idFoyer);
    }
    @GetMapping("/getAll")
public List<Foyer> getAllFoyers(){
        return foyerService.findAllFoyer();
    }
    @GetMapping("/getFoyer")
    public Foyer getByNom(@RequestParam String nomFoyer) {
        return foyerService.getFoyerByNom(nomFoyer);
    }
    @GetMapping("/getBlocs")
    public Set<Bloc> getByNomFoyer(@RequestParam String nomFoyer) {
        return foyerRepositorie.getByNomFoyer(nomFoyer);
    }

}
