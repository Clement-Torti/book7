package sample.gui.Utils;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.gui.controller.BaseController;

import java.io.File;

// Role: Laisse l'utilisateur selectionner une image
public class FileOpener {
    // Attributs
    private Stage stage;

    // Constructeur
    public FileOpener(Stage stage) {
        this.stage = stage;
    }

    // Methodes
    public File getFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File f = fileChooser.showOpenDialog(stage);

        return f;
    }

}