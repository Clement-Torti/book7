package sample.model.Contenu;

import java.io.Serializable;
import java.net.URI;

// ------------------------
// Rôle: Un media représentant une formule mathematique
// Création: Clément Torti
// Dernière Modification: Clément Torti
//
public class Formule extends Media implements Serializable {
    private static final long serialVersionUID = -3823401815970534040L;

    // Attributs
    private String texte;

    // Constructeurs
    public Formule(String relativePath, String alt) {
        super(relativePath, alt);
    }

    public Formule(String relativePath) {
        super(relativePath);
    }


    // Méthodes
}
