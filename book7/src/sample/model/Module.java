package sample.model;

import sample.model.Enums.Section;

import java.io.Serializable;
import java.util.List;

// ------------------------
// Rôle: Classe représentant un module de l'enseeiht, composé de plusieurs cahiers
// Création: Clément Torti
// Dernière Modification: Clément Torti
//
public class Module implements Serializable {
    private static final long serialVersionUID = -9154332618374629494L;
    // Attributs
    private String nom;
    private int semestre;
    private String chemin;
    private List<Cahier> cahiers;


    // Constructeurs
    public Module(String nom, String chemin) {
        this.nom = nom;
        this.chemin = chemin;
    }

    public Module(String nom, int semestre){
        this.nom=nom;
        this.semestre=semestre;
        this.calculerChemin();
    }

    private void calculerChemin(){
        String nom_format = nom.replace(' ','_');
        System.out.println(nom_format);
        this.chemin = Constantes.SEMESTRE_NAME
                + this.semestre
                + "/" + nom_format + "." + Constantes.EXTENSION;
    }

    public String getChemin(){
        return this.chemin;
    }

    // Getters
    public String getNom() {
        return nom;
    }

    public List<Cahier> getCahiers() {
        return cahiers;
    }

    // Méthodes
    @Override
    public String toString(){
        return ("Module[nom: " + this.nom
                + ", semestre : " + this.semestre
                + ", chemin : " + this.chemin + "]");
    }
}