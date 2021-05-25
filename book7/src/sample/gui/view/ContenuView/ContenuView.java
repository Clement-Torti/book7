package sample.gui.view.ContenuView;

import javafx.scene.Node;
import javafx.scene.control.Label;
import sample.model.Contenu.*;

// rôle: Classe indiquant à la vue comment afficher un contenu du modèle
// Dernière modification: Clément Torti
//
public class ContenuView {
    // Attribut
    protected Contenu contenu;

    // Constructeur
    public ContenuView(Contenu contenu) {
        this.contenu = contenu;
    }

    public Node afficher() {
        if (contenu instanceof TextArea) {
            //return TextAreaView.afficher()
        } else if(contenu instanceof Forme) {

        } else if(contenu instanceof Formule) {

        } else if(contenu instanceof  Image) {

        } else if(contenu instanceof  PDF) {

        }

        return new Label();
    }
}
