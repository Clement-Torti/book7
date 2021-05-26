package sample.model.Contenu;

import java.io.Serializable;
import java.util.Dictionary;
import java.util.List;

// ------------------------
// Rôle: Un contenu représentant un paragraphe de texte
// Création: Clément Torti
// Dernière Modification: Clément Torti
//
public class TextArea extends Contenu implements Serializable {
    private static final long serialVersionUID = 1043738449910295226L;

    // Attributs
    private String texte;
    private List<Balise> balises;


    // Getters
    public String getTexte() {
        return texte;
    }

    // Setters
    public void setTexte(String texte) {
        this.texte = texte;
    }

    // -----
    // rôle: Ajout d'une balise de style dans le texte
    // param:
    // - balise: balise html a rajouter
    public void ajoutStyle(Balise balise) {
        maj(this);
    }

    // -----
    // rôle: Suppression d'une balise de style dans le texte
    // param:
    // - balise: balise html a supprimer
    public void supprimerStyle(Balise balise) {
        maj(this);
    }
}
