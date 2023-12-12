package tn.esprit.twin1.EducationSpringApp.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.twin1.EducationSpringApp.dto.UniversiteDto;
import tn.esprit.twin1.EducationSpringApp.entities.AddUniversiteRequest;
import tn.esprit.twin1.EducationSpringApp.entities.Foyer;
import tn.esprit.twin1.EducationSpringApp.entities.Universite;

import java.util.List;

public interface UniversiteService {
    ResponseEntity<String> addUniversite(AddUniversiteRequest request);
    List<UniversiteDto> getAllUniversites();

    Universite findUniversiteById(Long id);

    void deleteUniversite(long idUniversite);
    Universite updateUniversite(long id, Universite upuniversite);

    Universite affecterFoyerAUniversite(String nomFoyer, long idUniversite) ;
    UniversiteDto mapUniversiteToDTO(Universite universite);
    Universite desaffecterFoyerAUniversite (long idUniversite) ;
    boolean isNomUniversiteUnique(String nomUniversite);
}
