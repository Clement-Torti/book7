package sample.model;

import sample.model.Contenu.Contenu;
import sample.model.Observateur.Observable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// ------------------------
// Rôle: Classe représentant une page d'un cahier
// Création: Clément Torti
// Dernière Modification: Clément Torti
//
public class Page extends Observable implements Serializable {
    private static final long serialVersionUID = 9165005631809492024L;

    // Attributs
    private boolean isPortrait;
    private List<Contenu> contenus = new ArrayList<>();

    // Getters
    public List<Contenu> getContenus() {
        return contenus;
    }


    // Constructeurs

    // Methodes
    public void appendContenu(Contenu contenu) {
        notifier(null);
        contenus.add(contenu);
    }

}
