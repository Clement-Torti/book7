package sample.model;

import sample.model.Contenu.Contenu;

import java.io.Serializable;
import java.util.Date;

// ------------------------
// Rôle: Classe représentant une operation/modification d'un utilisateur dans un cahier
// Création: Clément Torti
// Dernière Modification: Clément Torti
//
public class Operation implements Serializable {
    private static final long serialVersionUID = -5511058818152724582L;

    // Attributs
    private Date date;
    private Contenu contenu; // sauvegarde du contenu avant modification

    // Constructeurs
    public Operation(Contenu contenu, Date date) {
        this.contenu = contenu;
        this.date = date;
    }

    // Methodes
}
