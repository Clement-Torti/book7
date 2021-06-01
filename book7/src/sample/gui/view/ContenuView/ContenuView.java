package sample.gui.view.ContenuView;

import javafx.scene.Node;
import javafx.scene.control.Label;
import sample.model.Contenu.*;
import sample.model.Observateur.IObservateur;
import sample.model.Observateur.Observable;

// rôle: Classe abstraite indiquant à la vue comment afficher un contenu du modèle
// Dernière modification: Clément Torti
//
public abstract class ContenuView {
    // Attribut
    protected Contenu contenu;

    // Constructeur
    public ContenuView(Contenu contenu) {
        this.contenu = contenu;
    }

    public abstract Node afficher();
}
