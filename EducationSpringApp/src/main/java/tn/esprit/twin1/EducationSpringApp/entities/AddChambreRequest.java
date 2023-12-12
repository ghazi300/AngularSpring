package tn.esprit.twin1.EducationSpringApp.entities;


public class AddChambreRequest {
    private String nomBloc;
    String numChambre;
    private TypeChambre typeChambre;
    public AddChambreRequest() {
    }

    public AddChambreRequest(String nomBloc, String numChambre, TypeChambre typeChambre) {
        this.nomBloc = nomBloc;
        this.numChambre = numChambre;
        this.typeChambre = typeChambre;
    }

    public String getNomBloc() {
        return nomBloc;
    }

    public void setNomBloc(String nomFoyer) {
        this.nomBloc = nomFoyer;
    }

    public String getNumChambre() {
        return numChambre;
    }

    public void setNumChambre(String numChambre) {
        this.numChambre = numChambre;
    }

    public TypeChambre getTypeChambre() {
        return typeChambre;
    }

    public void setTypeChambre(TypeChambre typeChambre) {
        this.typeChambre = typeChambre;
    }
}