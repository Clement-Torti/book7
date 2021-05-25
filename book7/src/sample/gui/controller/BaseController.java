package sample.gui.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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
    public void changeScene(String source, BaseController destController) throws IOException {
        URL url = getClass().getResource(source);
        FXMLLoader loader = new FXMLLoader(url);

        loader.setController(destController);

        Parent root = loader.load();

        getStage().setScene(new Scene(root, WIN_WIDTH, WIN_HEIGHT));
    }

    // -----
    // rôle: Ouvrir une nouvelle fenêtre
    // param:
    // - source: FXML de la nouvelle vue
    // - destController: Controlleur de la nouvelle vue
    // - width: Largeur de la vue
    // - height: Hauteur de la vue
    public void openStage(String source, BaseController destController, int width, int height) throws IOException {
        URL url = getClass().getResource(source);

        FXMLLoader loader = new FXMLLoader(url);
        loader.setController(destController);
        Parent root = loader.load();

        Stage newStage = new Stage();

        newStage.setTitle("Infos");
        newStage.setScene(new Scene(root, width, height));
        newStage.setMinWidth(width+16);
        newStage.setMinHeight(height+39);
        newStage.initOwner(getStage());
        newStage.showAndWait();
    }
}
