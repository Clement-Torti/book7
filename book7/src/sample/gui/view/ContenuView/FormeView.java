package sample.gui.view.ContenuView;

import javafx.scene.Node;
import javafx.scene.control.Label;
import sample.model.Contenu.Contenu;
import sample.model.Observateur.Observable;


/**
 * ContenuView indiquant à la vue comment afficher une forme
 */
public class FormeView extends ContenuView {
    // Constructeur
    public FormeView(Contenu contenu) {
        super(contenu);
    }


    @Override
    public Node afficher() {
        Label label = new Label();
        label.setText("je suis une forme");

        return label;
    }


    @Override
    public void update(Observable obs, Object o) {
    }
}
