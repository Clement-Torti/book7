package sample.gui.view;

import javafx.scene.layout.HBox;

// ------------------------
// Rôle: Classe controllant un cahier
// Création: Clément Torti
// Dernière Modification: Clément Torti
//
public class CahierView extends HBox {
    // Constructeur
    public CahierView() {
        // Ajout des 2 pages
        PageView leftPage = new PageView();
        leftPage.setId("leftPage");
        PageView rightPage = new PageView();
        leftPage.setId("rightPage");

        getChildren().add(leftPage);
        getChildren().add(rightPage);
    }
}
