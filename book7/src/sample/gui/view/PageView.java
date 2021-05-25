package sample.gui.view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

// ------------------------
// Rôle: Classe controllant une page du cahier
// Création: Clément Torti
// Dernière Modification: Clément Torti
//
public class PageView extends VBox {
    public PageView() {
        super();
        this.setMaxWidth(Double.MAX_VALUE);
        this.setMaxHeight(Double.MAX_VALUE);

        Label test = new Label();
        test.setText("Test ici");

        getChildren().add(test);
    }
}
