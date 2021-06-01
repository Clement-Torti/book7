package sample.model.Contenu;

import sample.model.Enums.Alignement;
import sample.model.Enums.Disposition;

import java.io.Serializable;
import java.util.Dictionary;

// ------------------------
// Rôle: Classe abstraite représentant tout élément contenu dans un cahier
// Création: Clément Torti
// Dernière Modification: Clément Torti
//
public abstract class Contenu implements Serializable {
    private static final long serialVersionUID = 6149141948119544784L;
    
    // Attributs
    private String id;
    private Dictionary<String, String> metadonnees;
    private Alignement alignement;
    private Disposition disposition;

    // Constructeurs

    // Methodes

    // -----
    // rôle: Prevenir un changement d'etat de contenu
    // param:
    // - contenu: le contenu lui même
    protected void maj(Contenu contenu) {
        System.out.println("Non implementee");
    }

}
