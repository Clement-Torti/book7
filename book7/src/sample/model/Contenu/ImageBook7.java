package sample.model.Contenu;


import java.io.Serializable;
import java.net.URI;
import java.net.URL;

// ------------------------
// Rôle: Un media représentant une image
// Création: Clément Torti
// Dernière Modification: Clément Torti
//
public class ImageBook7 extends Media implements Serializable {
    private static final long serialVersionUID = 6525207693293343048L;

    // Attributs

    // Constructeurs
    public ImageBook7(String relativePath, String alt) {
        super(relativePath, alt);
    }

    public ImageBook7(String relativePath) {
        super(relativePath);
    }



    // Methodes
}
