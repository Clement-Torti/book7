package sample.gui.view.ContenuView;

import javafx.scene.Node;
import javafx.scene.control.Label;
import sample.model.Contenu.Contenu;
import sample.model.Observateur.IObservateur;

// rôle: ContenuView indiquant à la vue comment afficher une image
// Dernière modification: Clément Torti
//
public class ImageContenuView extends ContenuView {

    public ImageContenuView(Contenu contenu) {
        super(contenu);
    }

    @Override
    public Node afficher() {
        Label label = new Label();
        label.setText("je suis une image");

        return label;
    }
}
