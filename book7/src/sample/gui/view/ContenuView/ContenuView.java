package sample.gui.view.ContenuView;

import javafx.scene.Node;
import javafx.scene.control.Label;
import sample.gui.controller.ModuleController;
import sample.model.Contenu.*;
import sample.model.Observateur.IObservateur;
import sample.model.Observateur.Observable;


/**
 * Composant abstrait permettant l'affichage d'un contenu
 */
public abstract class ContenuView implements IObservateur {
    // Attribut
    protected Contenu contenu;

    // Constructeur
    public ContenuView(Contenu contenu) {
        this.contenu = contenu;
    }

    /**
     * Construction d'un Node à partir du contenu fourni
     * @return Node à afficher
     */
    public abstract Node afficher();


    /**
     * Sauvegarde du module lors d'un changement du contenu à sauvegarder
     * @return si la sauvegarde à bien eu lieu
     */
    public boolean sauvegarder() {
        return ModuleController.sauvegarderModule();
    }
}
