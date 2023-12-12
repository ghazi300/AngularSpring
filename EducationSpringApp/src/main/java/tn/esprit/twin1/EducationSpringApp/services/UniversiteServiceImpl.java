package tn.esprit.twin1.EducationSpringApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.twin1.EducationSpringApp.dto.UniversiteDto;
import tn.esprit.twin1.EducationSpringApp.entities.*;
import tn.esprit.twin1.EducationSpringApp.repositories.FoyerRepositorie;
import tn.esprit.twin1.EducationSpringApp.repositories.UniversiteRepositorie;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UniversiteServiceImpl implements UniversiteService {

    private final UniversiteRepositorie universiteRepositorie;
    private final FoyerRepositorie foyerRepositorie;


    @Override
    public Universite findUniversiteById(Long id) {
        return universiteRepositorie.findById(id).orElse(null);
    }

    @Override
    public void deleteUniversite(long idUniversite) {
        Universite universite = universiteRepositorie.findById(idUniversite)
                .orElseThrow(() -> new EntityNotFoundException("Universite not found"));

        universiteRepositorie.delete(universite);
    }
    @Override
    public ResponseEntity<String> addUniversite(AddUniversiteRequest request) {
        Universite univ = new Universite();
        univ.setNomUniversite(request.getNomUniversite());
        univ.setAdresse(request.getAdresse());

        if (request.getNomFoyer() != null) {
            Foyer foyer = foyerRepositorie.findByNomFoyer(request.getNomFoyer())
                    .orElse(null);
            univ.setFoyer(foyer);
        }

        universiteRepositorie.save(univ);

        return ResponseEntity.ok("Universite added successfully");
    }
    @Override
    public Universite updateUniversite(long id, Universite upuniversite) {
        Universite universite = universiteRepositorie.findById(id).orElse(null);

        universite.setNomUniversite(upuniversite.getNomUniversite());
        universite.setAdresse(upuniversite.getAdresse());

        return universiteRepositorie.save(universite);
    }


    @Override

    public Universite affecterFoyerAUniversite(String nomFoyer, long idUniversite) {
        Foyer foyer = foyerRepositorie.findByNomFoyer(nomFoyer)
                .orElseThrow(() -> new RuntimeException("Foyer non trouvé avec le nom: " + nomFoyer));
        Universite universite = universiteRepositorie.findById(idUniversite).get();
        if (universite == null) {
            throw new RuntimeException("Université non trouvée avec le ID : " + idUniversite);
        }
        universite.setFoyer(foyer);
        universiteRepositorie.save(universite);

        return universite;
    }

    @Override
    public List<UniversiteDto> getAllUniversites() {
        List<Universite> universites = universiteRepositorie.findAll();
        return universites.stream()
                .map(universite -> mapUniversiteToDTO(universite))
                .collect(Collectors.toList());
    }

    @Override
    public UniversiteDto mapUniversiteToDTO(Universite universite) {
        UniversiteDto universiteDTO = new UniversiteDto();
        universiteDTO.setIdUniversite(universite.getIdUniversite());
        universiteDTO.setNomUniversite(universite.getNomUniversite());
        universiteDTO.setAdresse(universite.getAdresse());
        universiteDTO.setNomFoyer(universite.getFoyer() != null ? universite.getFoyer().getNomFoyer() : null);
        return universiteDTO;
    }

    @Override
    public Universite desaffecterFoyerAUniversite(long idUniversite) {
        Universite universite = universiteRepositorie.findById(idUniversite).orElseThrow(() -> new RuntimeException("Universite non trouvé avec l'ID : " + idUniversite));
        if (universite != null) {
            universite.setFoyer(null);
            universiteRepositorie.save(universite);
            return universite;
        }
        return null;
    }


   @Override
public boolean isNomUniversiteUnique(String nomUniversite) {
    // Votre logique pour vérifier l'unicité du nom d'université
    // Retournez true si le nom est unique, false sinon
    return universiteRepositorie.findByNomUniversite(nomUniversite).isEmpty();
}
}
