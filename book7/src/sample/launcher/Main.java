package sample.launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        /*
        URL url = Main.class.getResource(CAHIER_FXML);

        FXMLLoader loader = new FXMLLoader(url);
        loader.setController(new CahierController());
        Parent root = loader.load();

        primaryStage.setTitle("Book7");
        primaryStage.setScene(new Scene(root, WIN_WIDTH, WIN_HEIGHT));
        primaryStage.setMinWidth(WIN_WIDTH);
        primaryStage.setMinHeight(WIN_HEIGHT);

        primaryStage.show();

         */
    }


    public static void main(String[] args) {
        launch(args);
    }
}
