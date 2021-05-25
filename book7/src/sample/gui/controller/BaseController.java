package sample.gui.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;
import java.net.URL;

import static sample.Main.WIN_HEIGHT;
import static sample.Main.WIN_WIDTH;

// ------------------------
// Rôle: Classe mère comprenant les actions communes aux controlleurs
// Dernière Modification: Clément Torti
//
public abstract class BaseController {
    private Stage stage;

    public BaseController(Stage stage) {
        this.stage = stage;
    }

    protected Stage getStage() {
        return stage;
    }

    // -----
    // rôle: Changer la scene du stage
    // param:
    // - source: FXML de la nouvelle vue
    // - destController: Controlleur de la nouvelle vue
    // - width: Largeur de la vue
    // - height: Hauteur de la vue
    // - titre: Titre de la scene
    public void changeScene(String source, BaseController destController, int width, int height, String titre) throws IOException {
        URL url = Main.class.getResource(source);
        FXMLLoader loader = new FXMLLoader(url);

        loader.setController(destController);

        Parent root = loader.load();

        getStage().setScene(new Scene(root, width, height));

        getStage().setTitle(titre);
    }

    // -----
    // rôle: Ouvrir une nouvelle fenêtre
    // param:
    // - source: FXML de la nouvelle vue
    // - destController: Controlleur de la nouvelle vue
    // - width: Largeur de la vue
    // - height: Hauteur de la vue
    // - titre: Titre de la fenêtre
    public void openStage(String source, BaseController destController, int width, int height, String titre) throws IOException {
        URL url = Main.class.getResource(source);

        FXMLLoader loader = new FXMLLoader(url);
        loader.setController(destController);
        Parent root = loader.load();

        Stage newStage = new Stage();

        newStage.setTitle(titre);
        newStage.setScene(new Scene(root, width, height));
        newStage.setMinWidth(width+16);
        newStage.setMinHeight(height+39);
        newStage.initOwner(getStage());
        newStage.showAndWait();
    }
}