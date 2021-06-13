package sample.gui.Utils;


import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.model.Constantes;
import sample.model.Utils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;


/**
 * Classe chargée de la gestion d'ouverture de fichiers
 */
public class FileOpener {
    // Attributs
    private Stage stage;


    // Constructeur
    public FileOpener(Stage stage) {
        this.stage = stage;
    }


    /**
     * Ouverture d'un module
     * @return Fichier correspondant au module
     * @throws IOException
     */
    public File getBook7() throws IOException {
        FileChooser.ExtensionFilter filter
                = new FileChooser.ExtensionFilter("Image Files", "*." + Constantes.EXTENSION);

        return getFile(filter, "Choix d'un module book7", Constantes.IMPORT_MODULE_ROOT_FOLDER_NAME);
    }


    /**
     * Ouverture d'un pdf
     * @return Fichier correspondant au pdf
     * @throws IOException
     */
    public File getPdf() throws IOException{
        FileChooser.ExtensionFilter filter
                = new FileChooser.ExtensionFilter("Image Files", "*.pdf");

        return getFile(filter, "Choix d'un pdf", Constantes.PDF_ROOT_FOLDER_NAME);
    }


    /**
     * Ouverture d'une image
     * @return Fichier correspondant à l'image
     * @throws IOException
     */
    public File getImage() throws IOException {
        FileChooser.ExtensionFilter filter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg");

        return getFile(filter, "Choix d'un module book7", Constantes.IMAGE_ROOT_FOLDER_NAME);
    }


    /**
     * Ouverture d'un fichier
     * @param filters Extensions du fichier autorisées
     * @param title Titre de la fenêtre de choix
     * @param folder Dossier de copy du fichier importé
     * @return Fichier choisi
     * @throws IOException
     */
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