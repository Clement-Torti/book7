package sample.gui.view;

import javafx.geometry.Pos;
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
        Label test = new Label();
        test.setText("Test ici");

        getChildren().add(test);
    }
}
