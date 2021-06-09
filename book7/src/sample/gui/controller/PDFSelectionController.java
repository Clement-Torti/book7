package sample.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import sample.gui.view.ContenuView.FabriqueContenuView;
import sample.gui.view.ContenuView.ImageContenuView;
import sample.model.Contenu.ImageBook7;
import sample.model.Contenu.PDF;
import sample.model.Utils;

import java.io.File;
import java.io.IOException;

public class PDFSelectionController extends BaseController {
    @FXML
    private VBox rootVbox;
    @FXML
    private HBox selectionPage;
    @FXML
    private VBox apercuPage;
    @FXML
    private Label labelChoicebox;
    @FXML
    private ChoiceBox pageChoicebox;
    @FXML
    private Label labelApercu;
    @FXML
    private HBox conteneurImage;
    @FXML
    private Button boutonAnnuler;
    @FXML
    private Button boutonAjouter;

    private PDF pdf;
    private ImageBook7 currentImage;
    private boolean imageChosen;
    private boolean containImage = false;

    public PDFSelectionController(Stage stage, PDF pdf) {
        super(stage);
        this.pdf = pdf;
        this.imageChosen = false;
    }

    // FXML Actions
    @FXML
    private void initialize() {
        for (int i = 1; i <= this.pdf.getNbPages(); i++)
            pageChoicebox.getItems().add(i);

        pageChoicebox.setOnAction((event) -> {
            try {
                // Supprimer l'ancienne image utilisée pour l'aperçu
                if(this.containImage) {
                    FileUtils.deleteQuietly(new File(Utils.getRacineProjet() + "/" + this.currentImage.getRelativePath()));
                }

                this.conteneurImage.getChildren().clear();
                this.currentImage = this.pdf.getImageBook7((Integer) pageChoicebox.getValue());
                this.conteneurImage.getChildren().add(FabriqueContenuView.fabriquerContenuView(currentImage).afficher());
                this.containImage = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        boutonAjouter.setOnAction((event) -> {
            this.imageChosen = true;
            getStage().close();
        });

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

}