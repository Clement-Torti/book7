package sample.gui.view;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import sample.model.Cahier;

// ------------------------
// Rôle: Classe controllant un cahier
// Création: Clément Torti
// Dernière Modification: Clément Torti
//
public class CahierView extends HBox {
    // Attributs
    private Cahier cahier;

    // Outlets
    private PageView leftPage;
    private PageView rightPage;


    // Constructeur
    public CahierView() {
        setSpacing(20);

        // Ajout des 2 pages
        leftPage = new PageView();
        leftPage.setId("page1");
        rightPage = new PageView();
        rightPage.setId("page2");

        HBox.setHgrow(leftPage, Priority.ALWAYS);
        HBox.setHgrow(rightPage, Priority.ALWAYS);

        getChildren().add(leftPage);
        getChildren().add(rightPage);
    }

    // Methodes
    public void setCahier(Cahier _cahier) {
        cahier = _cahier;
        setPage(0); // Afficher la 1ere page par défaut
    }

    public void setPage(Integer pageIndex) {
        if(pageIndex < 0 || pageIndex >= cahier.getPages().size()) {
            System.out.println("Erreur: Page index invalide");
        }

        // La page a afficher est paire
        if (pageIndex % 2 == 0) {
            leftPage.setPage(cahier.getPages().get(pageIndex));

            if(cahier.getPages().size() > pageIndex) {
                rightPage.setPage(cahier.getPages().get(pageIndex + 1));
            }
        } else {
            // La page à afficher est impaire
            leftPage.setPage(cahier.getPages().get(pageIndex-1));
            rightPage.setPage(cahier.getPages().get(pageIndex));
        }
    }
}
