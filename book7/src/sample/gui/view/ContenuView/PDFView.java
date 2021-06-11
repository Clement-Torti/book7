package sample.gui.view.ContenuView;

import javafx.scene.Node;
import javafx.scene.control.Label;
import sample.model.Contenu.Contenu;
import sample.model.Observateur.IObservateur;
import sample.model.Observateur.Observable;

// rôle: ContenuView indiquant à la vue comment afficher un PDF
// Dernière modification: Clément Torti
//
public class PDFView extends ContenuView {

    public PDFView(Contenu contenu) {
        super(contenu);
    }

    @Override
    public Node afficher() {
        Label label = new Label();
        label.setText("je suis un pdf");

        return label;
    }

    @Override
    public void update(Observable obs, Object o) {
    }
}
