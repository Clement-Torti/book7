package sample.gui.view.ContenuView;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import sample.model.Contenu.Contenu;
import sample.model.Contenu.ImageBook7;
import sample.model.Observateur.Observable;
import sample.model.Utils;

/**
 * Implémentation de ContenuView chargée d'afficher une ImageBook7
 */
public class ImageContenuView extends ContenuView {
    // Constructeur
    public ImageContenuView(Contenu contenu) {
        super(contenu);
    }


    @Override
    public Node afficher() {
        Pane pane = new Pane();
        HBox.setMargin(pane, new Insets(5));


        ImageView iv = new ImageView("file://"+ Utils.getRacineProjet() + "/" + ((ImageBook7)contenu).getRelativePath());
        iv.getStyleClass().add("imageContenuView");

        iv.setPreserveRatio(true);
        iv.setFitWidth(350);

        pane.getChildren().add(iv);
        return pane;
    }

    @Override
    public void update(Observable obs, Object o) {
    }
}
