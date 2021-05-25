package sample.gui.view;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

// ------------------------
// Rôle: Classe controllant un cahier
// Création: Clément Torti
// Dernière Modification: Clément Torti
//
public class CahierView extends HBox {
    // Constructeur
    public CahierView() {
        setId("cahier");

        // Ajout des 2 pages
        PageView leftPage = new PageView();
        leftPage.setId("leftPage");
        PageView rightPage = new PageView();
        leftPage.setId("rightPage");

        HBox.setHgrow(leftPage, Priority.ALWAYS);
        HBox.setHgrow(rightPage, Priority.ALWAYS);

        getChildren().add(leftPage);
        getChildren().add(rightPage);
    }
}
