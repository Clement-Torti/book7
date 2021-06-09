package sample.gui.view.ContenuView;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import sample.model.Contenu.Contenu;
import sample.model.Contenu.ImageBook7;
import sample.model.Observateur.Observable;
import sample.model.Utils;

// rôle: ContenuView indiquant à la vue comment afficher une image
// Dernière modification: Clément Torti
//
public class ImageContenuView extends ContenuView {

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
