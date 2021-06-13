package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.gui.controller.ArborescenceController;

import java.net.URL;

/**
 * Point du départ du programme
 */
public class Main extends Application {
    // Constantes
    public static final int WIN_WIDTH = 550;
    public static final int WIN_HEIGHT = 400;
    private static final String ARBORESCENCE_FXML = "gui/view/vueArborescence.fxml";


    // Methodes
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = getClass().getResource(ARBORESCENCE_FXML);

        FXMLLoader loader = new FXMLLoader(url);
        loader.setController(new ArborescenceController(primaryStage));
        Parent root = loader.load();

        primaryStage.setTitle("Book 7");
        primaryStage.setScene(new Scene(root, WIN_WIDTH, WIN_HEIGHT));
        primaryStage.setMinWidth(WIN_WIDTH);
        primaryStage.setMinHeight(WIN_HEIGHT);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
