package tn.esprit.twin1.EducationSpringApp.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddUniversiteRequest {
    private String nomUniversite;
   private  String adresse;
    @Nullable
    private String nomFoyer;


}
