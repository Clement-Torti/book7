package sample.gui.view.ContenuView;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import sample.model.Contenu.Contenu;
import sample.model.Contenu.ImageBook7;
import sample.model.Observateur.IObservateur;

import java.net.URI;

// rôle: ContenuView indiquant à la vue comment afficher une image
// Dernière modification: Clément Torti
//
public class ImageContenuView extends ContenuView {

    public ImageContenuView(Contenu contenu) {
        super(contenu);
    }

    @Override
    public Node afficher() {
        ImageView iv = new ImageView(((ImageBook7)contenu).getUri().toString());

        iv.setPreserveRatio(true);
        iv.setFitWidth(300);

        return iv;
    }
}
