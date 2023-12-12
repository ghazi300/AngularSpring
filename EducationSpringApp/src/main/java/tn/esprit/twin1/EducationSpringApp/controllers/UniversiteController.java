package tn.esprit.twin1.EducationSpringApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.twin1.EducationSpringApp.dto.UniversiteDto;
import tn.esprit.twin1.EducationSpringApp.entities.*;
import tn.esprit.twin1.EducationSpringApp.repositories.FoyerRepositorie;
import tn.esprit.twin1.EducationSpringApp.repositories.UniversiteRepositorie;
import tn.esprit.twin1.EducationSpringApp.services.FoyerService;
import tn.esprit.twin1.EducationSpringApp.services.UniversiteService;

import javax.persistence.EntityNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestMapping("/universite")
@RequiredArgsConstructor
@RestController
public class UniversiteController {
    private final UniversiteService universiteService;
    private final FoyerService foyerService;
    private final UniversiteRepositorie universiteRepositorie;
    private final FoyerRepositorie foyerRepositorie;


    @PostMapping("/new")
    public ResponseEntity<Object> addUniversite(@RequestBody AddUniversiteRequest request) {
        try {
            ResponseEntity<String> savedUniversite = universiteService.addUniversite(request);
            return ResponseEntity.ok(savedUniversite);
        } catch (Exception e) {

            e.printStackTrace();
            return new ResponseEntity<>("Erreur lors de l'ajout de l'université", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update/{idUniversite}")
    public Universite updateUniversite(@PathVariable long idUniversite,@RequestBody Universite universite) {
        return universiteService.updateUniversite(idUniversite,universite);
    }

    @GetMapping("/getId/{idUniversite}")
    public Universite getId(@PathVariable long idUniversite) {
        Universite universite =universiteService.findUniversiteById(idUniversite);
        System.out.println(universite);

        return universite;
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<UniversiteDto>> getAllUniversites() {
        List<UniversiteDto> universitesDTO = universiteService.getAllUniversites();
        return ResponseEntity.ok(universitesDTO);
    }



    @DeleteMapping("/delete/{idUniversite}")
    public ResponseEntity<String> deleteUniversite(@PathVariable long idUniversite) {
        try {
            universiteService.deleteUniversite(idUniversite);
            return ResponseEntity.ok("Universite deleted successfully");
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Universite not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception for debugging
            return new ResponseEntity<>("Error deleting universite", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/foyers")
    public void affecterFoyerAUniversite(@RequestParam String nomFoyer, @RequestParam long idUniversite) {
        universiteService.affecterFoyerAUniversite(nomFoyer, idUniversite);
    }

    @PatchMapping("/desaffecterFoyer")
    public ResponseEntity<Universite> desaffecterFoyerAUniversite(@RequestParam long idUniv ) {
        try {
            Universite universite = universiteService.desaffecterFoyerAUniversite(idUniv);
            return new ResponseEntity<>(universite, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/checkUniqueName")
    public ResponseEntity<Boolean> checkUniqueName(@RequestParam String nomUniversite) {
        boolean isUnique = universiteService.isNomUniversiteUnique(nomUniversite);
        return ResponseEntity.ok(isUnique);
    }


    @GetMapping("/foyernames")
    public List<String> getFoyerNames() {
        return foyerService.getFoyersNonAffectes();
    }





}
