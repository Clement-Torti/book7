package sample.gui.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;

import java.io.IOException;
import java.net.URL;

import static sample.Main.WIN_HEIGHT;
import static sample.Main.WIN_WIDTH;

/**
 * Classe mère de tout controller
 */
public abstract class BaseController {
    // Attributs
    private Stage stage;


    // Constructeur
    public BaseController(Stage stage) {
        this.stage = stage;
    }


    // Getter
    public Stage getStage() {
        return stage;
    }


    /**
     * Changer la scene du stage
     * @param source FXML de la nouvelle vue
     * @param destController Controlleur de la nouvelle vue
     * @param width Largeur de la vue
     * @param height Hauteur de la vue
     * @param titre Titre de la scene
     * @throws IOException
     */
    public void changeScene(String source, BaseController destController, Double width, Double height, String titre) throws IOException {
        URL url = Main.class.getResource(source);
        FXMLLoader loader = new FXMLLoader(url);

        loader.setController(destController);

        Parent root = loader.load();

        getStage().setScene(new Scene(root, width, height));

        getStage().setTitle(titre);
    }


    /**
     * Ouvrir une nouvelle fenêtre
     * @param source FXML de la nouvelle vue
     * @param destController Controlleur de la nouvelle vue
     * @param width Largeur de la vue
     * @param height Hauteur de la vue
     * @param titre Titre de la fenêtre
     * @param transparent Transparence de la fenetre
     * @throws IOException
     */
    public void openStage(String source, BaseController destController, Double width, Double height, String titre, boolean transparent) throws IOException {
        URL url = Main.class.getResource(source);

        FXMLLoader loader = new FXMLLoader(url);
        loader.setController(destController);
        Parent root = loader.load();

        Stage newStage = new Stage();
        Scene scene = new Scene(root, width, height);

        if (transparent) {
            newStage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
        }

        destController.stage = newStage;

        newStage.setResizable(false);
        newStage.setTitle(titre);
        newStage.setScene(scene);
        newStage.showAndWait();
    }
}
