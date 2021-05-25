package sample.gui.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import sample.model.Page;

// ------------------------
// Rôle: Classe controllant une page du cahier
// Création: Clément Torti
// Dernière Modification: Clément Torti
//
public class PageView extends VBox {
    // Outlets

    // Attributs
    private Page page;

    // Constructeur
    public PageView() {
        super();
        setId("page");
        Label test = new Label();
        test.setText("Test ici");

        getChildren().add(test);
    }

    // Methodes
    public void setPage(Page _page) {
        page = _page;
    }
}
