package sample.model.Contenu;

import java.io.Serializable;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

// ------------------------
// Rôle: Un media représentant une PDF
// Création: Clément Torti
// Dernière Modification: Clément Torti
//
public class PDF extends Media implements Serializable {
    private static final long serialVersionUID = -2049344260833714945L;

    // Attributs
    List<String> Metadonne = new ArrayList<String>();

    // Constructeurs
    public PDF(URI uri,List<String> metadonne) {
        super(uri);
        this.Metadonne = metadonne;
    }

    // Methodes

    public List<String> getMetadonne(){

        return this.Metadonne;
    }

}
