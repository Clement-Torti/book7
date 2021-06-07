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
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);
        fileChooser.setTitle("Open Resource File");
        File f = fileChooser.showOpenDialog(stage);

        return f;
    }

    public File getFilePDF() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter PdfFilter
                = new FileChooser.ExtensionFilter("PDF Files", "*.pdf");
        fileChooser.getExtensionFilters().add(PdfFilter);
        fileChooser.setTitle("Open Resource File");
        File f = fileChooser.showOpenDialog(stage);

        return f;

    }

}