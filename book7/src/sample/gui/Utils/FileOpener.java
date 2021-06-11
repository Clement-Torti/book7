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
import sample.model.Constantes;
import sample.model.Utils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

// Role: Laisse l'utilisateur selectionner une image
public class FileOpener {
    // Attributs
    private Stage stage;

    // Constructeur
    public FileOpener(Stage stage) {
        this.stage = stage;
    }

    public File getBook7() throws IOException {
        FileChooser.ExtensionFilter filter
                = new FileChooser.ExtensionFilter("Image Files", "*." + Constantes.EXTENSION);

        return getFile(filter, "Choix d'un module book7", Constantes.IMPORT_MODULE_ROOT_FOLDER_NAME);
    }

    public File getPdf() throws IOException{
        FileChooser.ExtensionFilter filter
                = new FileChooser.ExtensionFilter("Image Files", "*.pdf");

        return getFile(filter, "Choix d'un pdf", Constantes.PDF_ROOT_FOLDER_NAME);
    }

    public File getImage() throws IOException {
        FileChooser.ExtensionFilter filter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg");

        return getFile(filter, "Choix d'un module book7", Constantes.IMAGE_ROOT_FOLDER_NAME);
    }

    // Methodes
    private File getFile(FileChooser.ExtensionFilter filters, String title, String folder) throws IOException {
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(filters);
        fileChooser.setTitle(title);
        File f = fileChooser.showOpenDialog(stage);

        if (f == null) {
            return null;
        }

        File output = new File(Utils.getRacineProjet() + "/" + folder + "/" + f.getName());
        FileUtils.copyFile(f, output);

        return output;
    }

}