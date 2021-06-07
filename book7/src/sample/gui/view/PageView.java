package sample.gui.view;

import com.itextpdf.text.pdf.PdfReader;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.gui.Utils.FileOpener;
import sample.gui.view.ContenuView.ContenuView;
import sample.gui.view.ContenuView.FabriqueContenuView;
import sample.gui.view.ContenuView.ImageContenuView;
import sample.model.Contenu.Contenu;

import sample.model.Contenu.ImageBook7;
import sample.model.Contenu.PDF;
import sample.model.Contenu.TextZone;
import sample.model.Enums.Section;
import sample.model.Observateur.IObservateur;
import sample.model.Observateur.Observable;
import sample.model.Page;
import sample.model.Toolbox;
import sample.model.Utils;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// ------------------------
// Rôle: Classe controllant une page du cahier
// Création: Clément Torti
// Dernière Modification: Clément Torti
//
public class PageView extends BorderPane {
    // Outlets
    private ScrollPane scrollPane;
    private HBox headerBox = new HBox();
    private VBox contenuBox = new VBox();
    private HBox footerBox = new HBox();
    private FileOpener fileOpener;

    // Attributs
    private Page page;
    private Integer index;
    private String nomModule;
    private Section section;
    private Toolbox toolbox;

    // Constructeur
    public PageView(FileOpener fileOpener) {
        super();
        this.fileOpener = fileOpener;
        // CSS
        getStylesheets().add("vuePage.css");
        setId("mainVue");

        // Elements
        setTop(headerBox);
        setMargin(headerBox, new Insets(10));

        scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setId("scrollPane");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        setCenter(scrollPane);
        scrollPane.setContent(contenuBox);
        contenuBox.setId("contenuBox");
        contenuBox.setAlignment(Pos.CENTER);
        setMargin(scrollPane, new Insets(0, 20, 0, 20));

        setBottom(footerBox);
        setMargin(footerBox, new Insets(5, 20, 10, 20));

    }

    // Methodes
    public void setPage(Page _page, Integer _index, String _nomModule, Section _section, Toolbox _toolbox) {
        page = _page;
        index = _index;
        nomModule = _nomModule;
        section = _section;
        toolbox = _toolbox;
        updateView();
    }

    private void updateView() {
        // Vider les anciens elements de vue
        headerBox.getChildren().clear();
        contenuBox.getChildren().clear();
        footerBox.getChildren().clear();

        // Header
        headerBox.setAlignment(Pos.CENTER);

        Label nomCahierLabel = new Label();
        nomCahierLabel.setText(nomModule.toUpperCase(Locale.ROOT) + "-" + section);
        headerBox.getChildren().add(nomCahierLabel);

        // Contenu
        for(Contenu c: page.getContenus()) {
            ContenuView cv = FabriqueContenuView.fabriquerContenuView(c);
            contenuBox.getChildren().add(cv.afficher());
        }

        try {
            int contentSize = page.getContenus().size();
            if(contentSize == 0 || !(page.getContenus().get( contentSize - 1) instanceof TextZone)) {
                // Le dernier element est un contenu dynamique
                TextZone tz = new TextZone();
                ContenuView cv = FabriqueContenuView.fabriquerContenuView(tz);
                contenuBox.getChildren().add(cv.afficher());
                page.appendContenu(tz);
            }
        } catch (Exception e) {
            System.out.println(e);
        }


        // Footer
        footerBox.setAlignment(Pos.CENTER_RIGHT);
        footerBox.setSpacing(10);

        // Ajout d'une image
        Button addImage = new Button();
        addImage.setText("Ajout image");
        addImage.setOnAction((event) -> {
            File f = fileOpener.getFile();

            if(f != null) {
                ImageBook7 imageBook7 = new ImageBook7(f.getAbsoluteFile().toURI());
                page.appendContenu(imageBook7);
                updateView();
            }


        });

        Button addPDf = new Button("ajout PDF");
        addPDf.setOnAction((event) -> {
        File file = fileOpener.getFilePDF();


        List<String> Metadonne = new ArrayList<>();
        if(file != null){

            PdfReader reader = null;
            try {
                reader = new PdfReader(file.toString());

                int n = reader.getNumberOfPages();
                for (int i = 1; i < n; i++) {

                    Metadonne.add(Utils.pdfExtraction(reader, i));
                }
                reader.close();

            }
         catch (IOException e) {
                e.printStackTrace();
            }

            PDF pdf = new PDF(file.getAbsoluteFile().toURI(), Metadonne);

            System.out.println(pdf.getMetadonne().toString());
            page.appendContenu(pdf);
          //updateView();

        }
        });

        footerBox.getChildren().add(addImage);
        footerBox.getChildren().add(addPDf);

        Label pageLabel = new Label();
        pageLabel.setText("" + index);
        footerBox.getChildren().add(pageLabel);
    }
}
