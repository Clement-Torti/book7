package sample.model;

import sample.gui.controller.ModuleController;
import sample.model.Contenu.Contenu;
import sample.model.Observateur.Observable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



/**
 * Classe représentant une page d'un cahier
 */
public class Page extends Observable implements Serializable {
    private static final long serialVersionUID = 9165005631809492024L;

    // Attributs
    private boolean isPortrait;
    private List<Contenu> contenus = new ArrayList<>();


    // Getters
    public List<Contenu> getContenus() {
        return contenus;
    }


    // Methodes

    /**
     * Ajout d'un contenu en fin de page
     * @param contenu contenue à rajouter
     */
    public void appendContenu(Contenu contenu) {
        notifier(null);
        contenus.add(contenu);
        ModuleController.forcerSauvegarde();
    }


    /**
     * Suppression d'un contenu de la page
     * @param c contenu à supprimer
     */
    public void removeContenu(Contenu c) {
        if(contenus.remove(c)) {
            ModuleController.forcerSauvegarde();
        }
    }
}
