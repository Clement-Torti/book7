package sample.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import sample.gui.Utils.FileOpener;
import sample.gui.view.ContenuView.ContenuView;
import sample.gui.view.ContenuView.FabriqueContenuView;
import sample.gui.view.ContenuView.ImageContenuView;
import sample.model.Constantes;
import sample.model.Contenu.ImageBook7;
import sample.model.Contenu.PDF;
import sample.model.Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PDFSelectionController extends BaseController {
    @FXML private ComboBox<String> pdfChoiceBox;
    @FXML private ComboBox<Integer> pageChoicebox;
    @FXML private HBox conteneurImage;
    @FXML private Button boutonAnnuler;
    @FXML private Button boutonAjouter;

    private FileOpener fileOpener;
    private PDF pdf;
    private ImageBook7 currentImage;
    private boolean imageChosen = false;
    private boolean containImage = false;
    private List<PDF> pdfs = new ArrayList<>();

    public PDFSelectionController(Stage stage, FileOpener fileOpener) {
        super(stage);
        this.fileOpener = fileOpener;
        pdfs = loadPDFs();
    }

    private void setPdf(PDF pdf) {
        this.pdf = pdf;
        updateView();
    }

    // FXML Actions
    @FXML
    private void initialize() {
        // Choix du pdf
        for(PDF pdf: pdfs) {
            pdfChoiceBox.getItems().add(pdf.getNomFichier());
        }

        pdfChoiceBox.setOnAction((event) -> {
            setPdf(pdfs.get(pdfChoiceBox.getSelectionModel().getSelectedIndex()));
        });


        // Affichage de l'aperçu de la page selectionnée
        pageChoicebox.setOnAction((event) -> {
            if (pdf == null || pageChoicebox.getValue() == null) {
                return;
            }

            try {
                // Supprimer l'ancienne image utilisée pour l'aperçu
                if(this.containImage) {
                    FileUtils.deleteQuietly(new File(Utils.getRacineProjet() + "/" + this.currentImage.getRelativePath()));
                }

                this.conteneurImage.getChildren().clear();
                this.currentImage = this.pdf.getImageBook7((Integer) pageChoicebox.getValue());
                Node node = FabriqueContenuView.fabriquerContenuView(currentImage).afficher();
                node.getStyleClass().add("imageView");
                this.conteneurImage.getChildren().add(node);
                this.containImage = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Envoie de la page selectioné au module controller
        boutonAjouter.setOnAction((event) -> {
            this.imageChosen = true;
            getStage().close();
        });

        // Sortir sans renvoyé d'image
        boutonAnnuler.setOnAction((event) -> {
            this.imageChosen = false;
            getStage().close();
        });
    }

    public ImageBook7 getCurrentImage() {
        return this.currentImage;
    }

    public boolean getImageChosen() {
        return imageChosen;
    }

    @FXML private void newPdf() {
        try {
            File file = fileOpener.getPdf();
            if(file == null) {
                return;
            }

            PDF _pdf = new PDF(Constantes.PDF_ROOT_FOLDER_NAME + "/" + file.getName());
            System.out.println(_pdf);

            pdfs.add(_pdf);
            setPdf(_pdf);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<PDF> loadPDFs() {
        List<PDF> pdfs = new ArrayList<>();

        // On verifie que le dossier existe, sinon on le creer
        String path = Utils.getRacineProjet() + "/" + Constantes.PDF_ROOT_FOLDER_NAME;
        File dossierPdf = new File(path);

        if(!dossierPdf.isDirectory()) {
            dossierPdf.mkdirs();
        }

        // On parcours les fichiers avec l'extension pdf
        Pattern patterPdf = Pattern.compile("^.*\\.pdf$");
        String fichiers[] = dossierPdf.list();

        for(int i=0; i<fichiers.length; i++) {
            String fichier = fichiers[i];

            // Pour chaque fichier valide, on creer un pdf
            if(patterPdf.matcher(fichier).matches()){
                try {
                    PDF newPdf = new PDF(Constantes.PDF_ROOT_FOLDER_NAME + "/" + fichier);
                    pdfs.add(newPdf);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return pdfs;
    }

    // MAJ du choix de la page quand on change de pdf
    private void updateView() {
        pdfChoiceBox.getItems().clear();
        pageChoicebox.getItems().clear();

        // Choix du pdf
        for(PDF pdf: pdfs) {
            pdfChoiceBox.getItems().add(pdf.getNomFichier());
        }

        // Choix de page
        if(pdf != null) {
            for (int i = 1; i <= this.pdf.getNbPages(); i++) {
                pageChoicebox.getItems().add(i);
            }
        }

    }

}