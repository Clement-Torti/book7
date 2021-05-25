package sample.gui.view.ContenuView;

import javafx.scene.Node;
import javafx.scene.control.Label;
import sample.model.Contenu.Contenu;
import sample.model.Contenu.TextArea;

// rôle: ContenuView indiquant à la vue comment afficher un TextArea
// Dernière modification: Clément Torti
//
public class TextAreaView extends ContenuView {

    public TextAreaView(Contenu contenu) {
        super(contenu);
    }

    @Override
    public Node afficher() {
        Label label = new Label();
        label.setText(((TextArea) contenu).getTexte());

        return label;
    }
}
