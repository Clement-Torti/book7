package sample.model;

import sample.model.Enums.Section;

import java.util.List;

// ------------------------
// Rôle: Classe représentant un module de l'enseeiht, composé de plusieurs cahiers
// Création: Clément Torti
// Dernière Modification: Clément Torti
//
public class Module {
    // Attributs
    private String nom;
    private String chemin;
    private List<Cahier> cahiers;


    // Constructeurs
    public Module(String nom, String chemin) {
        this.nom = nom;
        this.chemin = chemin;
    }

    // Getters
    public String getNom() {
        return nom;
    }

    // Méthodes
}
