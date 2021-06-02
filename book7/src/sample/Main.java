package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.gui.controller.ArborescenceController;
import sample.model.Contenu.TextZone;
import sample.model.Module;
import sample.model.Persistence.ModuleWriter;

import java.net.URL;

public class Main extends Application {
    public static final int WIN_WIDTH = 550;
    public static final int WIN_HEIGHT = 400;
    private static final String ARBORESCENCE_FXML = "gui/view/vueArborescence.fxml";

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
//        Module m = new Module("gestion", 1);
//
//        TextArea tz = new TextArea();
//        tz.setTexte("ceci est la textezone");
//
//        m.getCours().getPages().get(0).appendContenu(tz);
//
//        ModuleWriter mw = new ModuleWriter();
//        mw.ecrire(m);
        launch(args);
    }
}
