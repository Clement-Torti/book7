package sample.model.Contenu;

import java.io.Serializable;
import java.net.URI;
import java.net.URL;

// ------------------------
// Rôle: Un contenu représentant un media
// Création: Clément Torti
// Dernière Modification: Clément Torti
//
public abstract class Media extends Contenu implements Serializable {
    private static final long serialVersionUID = -6573313072129458148L;

    // Attributs
    private URI uri;

    // Getter
    public URI getUri() {
        return uri;
    }

    // Constructeurs
    public Media(URI uri) {
        this.uri = uri;
    }

    // Methodes
}
