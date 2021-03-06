package sample.gui.view;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.gui.Utils.FileOpener;
import sample.gui.controller.BaseController;
import sample.gui.controller.ModuleController;
import sample.gui.controller.PDFSelectionController;
import sample.gui.view.ContenuView.*;
import sample.model.Constantes;
import sample.model.Contenu.*;

import sample.model.Enums.Section;
import sample.model.Observateur.IObservateur;
import sample.model.Observateur.Observable;
import sample.model.Page;
import sample.model.Toolbox;
import sample.model.Utils;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Locale;

// ------------------------
// Rôle: Classe controllant une page du cahier
// Création: Clément Torti
// Dernière Modification: Clément Torti
//
public class PageView extends BorderPane implements IObservateur {
    // Outlets
    private VBox scrollPaneVBox;
    private ScrollPane scrollPane;
    private BorderPane headerPane = new BorderPane();
    private VBox contenuBox = new VBox();
    private HBox footerBox = new HBox();
    private FileOpener fileOpener;

    // Attributs
    private Page page;
    private Integer index;
    private String nomModule;
    private Section section;
    private Toolbox toolbox;
    private BaseController baseController;

    // Constructeur
    public PageView(FileOpener fileOpener, BaseController controller) {
        this(fileOpener, controller, 0, 0);
    }

    public PageView(FileOpener fileOpener, BaseController controller, int width, int height) {
        super();
        if(width != 0) {
            this.setWidth(width);
            this.setHeight(height);
        }
        this.fileOpener = fileOpener;
        this.baseController = controller;

        // CSS
        getStylesheets().add("vuePage.css");
        setId("mainVue");

        // Elements
        setTop(headerPane);
        setMargin(headerPane, new Insets(5, 20, 10, 20));



        // Architecture du centre de la page
        // Vbox (motif de fond)
        //  > ScrollPane (contenu scrollable)
        //      > VBox (contenus)
        //          > HBox
        //              > ContenuView | delete Btn
        scrollPaneVBox = new VBox();
        scrollPaneVBox.getStyleClass().setAll("fondGrandCarreaux");

        scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);

        scrollPane.setId("scrollPane");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        setCenter(scrollPaneVBox);
        scrollPaneVBox.getChildren().add(scrollPane);

        scrollPane.setContent(contenuBox);
        setMargin(scrollPaneVBox, new Insets(0, 15, 0, 15));

        setBottom(footerBox);
        setMargin(footerBox, new Insets(5, 20, 10, 20));

    }

    // Methodes
    public VBox setPage(Page _page, Integer _index, String _nomModule, Section _section, Toolbox _toolbox) {
        // Se desabonner de l'ancienne page
        if(page != null) {
            page.dettach(this);
        }

        // S'abonner a la nouvelle page
        page = _page;
        page.attach(this);

        index = _index;
        nomModule = _nomModule;
        section = _section;
        toolbox = _toolbox;

        toolbox.attach(this);
        return updateView();
    }

    private VBox updateView() {
        // Vider les anciens elements de vue
        headerPane.getChildren().clear();
        contenuBox.getChildren().clear();
        footerBox.getChildren().clear();

        // Header
        Label nomCahierLabel = new Label();
        nomCahierLabel.setId("nomCahierLabel");
        nomCahierLabel.setText(nomModule.toUpperCase(Locale.ROOT) + "-" + section);
        headerPane.setCenter(nomCahierLabel);

        Label inpLabel = new Label();
        inpLabel.setText(Constantes.INP_HEADER);
        inpLabel.setId("inpLabel");
        headerPane.setRight(inpLabel);

        Label ghostLabel = new Label();
        ghostLabel.setText(Constantes.GOHST_INP_HEADER);
        headerPane.setLeft(ghostLabel);



        // Contenu
        contenuBox.setId("contenuBox");
        contenuBox.setAlignment(Pos.CENTER);

        for(Contenu c: page.getContenus()) {
            contenuBox.getChildren().add(getDefaultContenuView(c, true));
        }


        try {
            int contentSize = page.getContenus().size();

            if(contentSize == 0 || !(page.getContenus().get( contentSize - 1) instanceof TextZone)) {
                // Le dernier element est un contenu dynamique
                TextZone tz = new TextZone();
                page.appendContenu(tz);
                contenuBox.getChildren().add(getDefaultContenuView(tz, true));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        // Footer
        footerBox.setAlignment(Pos.CENTER_RIGHT);
        footerBox.setSpacing(10);

        // Ajout d'une image
        Button addImage = new Button();
        addImage.setPrefWidth(50);
        addImage.setPrefHeight(20);
        ImageView iconImage = new ImageView("/icon-image.png");
        iconImage.setPreserveRatio(true);
        iconImage.setFitHeight(15);
        addImage.setGraphic(iconImage);
        addImage.getStyleClass().add("footer_bouton_style");
        addImage.setOnAction((event) -> {
            try {
                File f = fileOpener.getImage();

                if(f != null) {
                    ImageBook7 imageBook7 = new ImageBook7(Constantes.IMAGE_ROOT_FOLDER_NAME + "/" + f.getName());
                    page.appendContenu(imageBook7);
                    updateView();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        footerBox.getChildren().add(addImage);

        // PDF Button
        Button addPDF = new Button();
        addPDF.setPrefWidth(50);
        addPDF.setPrefHeight(20);
        ImageView iconPdf = new ImageView("/icon-pdf.png");
        iconPdf.setPreserveRatio(true);
        iconPdf.setFitHeight(13);
        addPDF.setGraphic(iconPdf);
        addPDF.getStyleClass().add("footer_bouton_style");
        addPDF.setOnAction((event) -> {
                try {
                    PDFSelectionController contr = new PDFSelectionController(this.baseController.getStage(), fileOpener);
                    this.baseController.openStage("gui/view/vuePDFSelection.fxml", contr, 500.0, 500.0, "Selection PDF", false);

                    if(contr.getImageChosen()) {
                        System.out.println(contr.getCurrentImage().getRelativePath());

                        Contenu contenu = contr.getCurrentImage();
                        page.appendContenu(contenu);
                        contenuBox.getChildren().add(getDefaultContenuView(contenu, false));

                        TextZone tz = new TextZone();
                        contenuBox.getChildren().add(getDefaultContenuView(tz, false));

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        });

        footerBox.getChildren().add(addPDF);

        // Code
        Button codeButton = new Button();
        codeButton.setPrefWidth(50);
        codeButton.setPrefHeight(20);
        ImageView iconCode = new ImageView("/icon-code.png");
        iconCode.setPreserveRatio(true);
        iconCode.setFitHeight(13);
        codeButton.setGraphic(iconCode);
        codeButton.getStyleClass().add("footer_bouton_style");
        codeButton.setOnAction((event) ->{
            CodeZone cz = new CodeZone();
            page.getContenus().add(cz);
            contenuBox.getChildren().add(getDefaultContenuView(cz, false));

            TextZone tz = new TextZone();
            page.getContenus().add(tz);
            contenuBox.getChildren().add(getDefaultContenuView(tz, true));
        });
        footerBox.getChildren().add(codeButton);

        Label pageLabel = new Label();
        pageLabel.setText("" + index);
        footerBox.getChildren().add(pageLabel);

        return contenuBox;
    }

    @Override
    public void update(Observable obs, Object o) {
        if(obs instanceof Toolbox) {
            Toolbox toolBox = (Toolbox) obs;
            Toolbox.ToolboxUpdate update = (Toolbox.ToolboxUpdate) o;

            if (update == Toolbox.ToolboxUpdate.MOTIF) {
                String value = toolBox.getMotif();
                scrollPaneVBox.getStyleClass().setAll("fond" + value);
            } else {
                System.out.println();
            }
        }

        // A l'ajout d'un contenu, sauvegarder
        ModuleController.forcerSauvegarde();
    }

    private Node getDefaultContenuView(Contenu c, boolean attachToToolBox) {
        HBox contenuHBox = new HBox();

        ContenuView cv = FabriqueContenuView.fabriquerContenuView(c);
        Node node = cv.afficher();

        HBox.setHgrow(node, Priority.ALWAYS);

        contenuHBox.getChildren().add(node);

        if(attachToToolBox) {
            toolbox.attach(cv);
        }

        ColorAdjust couleurBlanche = new ColorAdjust();
        couleurBlanche.setBrightness(0);
        Button boutonFermer = new Button();
        boutonFermer.getStyleClass().add("bouton_supprimer");
        ImageView iconBoutonFermer = new ImageView("/icon-close.png");
        iconBoutonFermer.setPreserveRatio(true);
        iconBoutonFermer.setFitWidth(10);
        iconBoutonFermer.setEffect(couleurBlanche);
        boutonFermer.setGraphic(iconBoutonFermer);

        boutonFermer.setOnAction((event) -> {
            page.removeContenu(c);
            updateView();
        });
        contenuHBox.getChildren().add(boutonFermer);

        HBox.setHgrow(boutonFermer, Priority.NEVER);

        return contenuHBox;
    }
}
