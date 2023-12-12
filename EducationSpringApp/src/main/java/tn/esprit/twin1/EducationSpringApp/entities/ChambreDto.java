package tn.esprit.twin1.EducationSpringApp.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChambreDto {


    private  long idChambre ;
    private String numChambre;
    private String nomBloc;
    private TypeChambre typeChambre;
    public ChambreDto() {}

    public ChambreDto(long idChambre, String numChambre, String nomBloc, TypeChambre typeChambre) {
        this.idChambre = idChambre;
        this.numChambre = numChambre;
        this.nomBloc = nomBloc;
        this.typeChambre = typeChambre;
    }

    public long getIdChambre() {
        return idChambre;
    }

    public String getNumChambre() {
        return numChambre;
    }

    public String getNomBloc() {
        return nomBloc;
    }



    public void setIdChambre(long idChambre) {
        this.idChambre = idChambre;
    }

    public void setNumChambre(String numChambre) {
        this.numChambre = numChambre;
    }

    public void setNomBloc(String nomBloc) {
        this.nomBloc = nomBloc;
    }

    public TypeChambre getTypeChambre() {
        return typeChambre;
    }

    public void setTypeChambre(TypeChambre typeChambre) {
        this.typeChambre = typeChambre;
    }
}
