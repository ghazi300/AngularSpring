package tn.esprit.twin1.EducationSpringApp.services;

import tn.esprit.twin1.EducationSpringApp.entities.Etudiant;

import java.util.List;

public interface EtudiantService {
    Etudiant addEtudiant(Etudiant etudiant);

    List<Etudiant> findAllEtudiant();

    Etudiant findById(Long id);

    String deleteEtudiant(Etudiant etudiant);

    String deleteEtudiantById(Long id);

    Etudiant updateEtudiant(long idEtudiant, Etudiant updatedEt);
}
