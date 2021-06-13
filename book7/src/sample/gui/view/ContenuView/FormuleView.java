package sample.gui.view.ContenuView;

import javafx.scene.Node;
import javafx.scene.control.Label;
import sample.model.Contenu.Contenu;
import sample.model.Observateur.Observable;


/**
 * ContenuView indiquant à la vue comment afficher une formule
 */
public class FormuleView extends ContenuView {

    // Constructeur
    public FormuleView(Contenu contenu) {
        super(contenu);
    }


    @Override
    public Node afficher() {
        Label label = new Label();
        label.setText("je suis une formule");

        return label;
    }

    @Override
    public void update(Observable obs, Object o) {
    }
}
