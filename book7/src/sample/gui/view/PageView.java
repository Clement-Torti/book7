package sample.gui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import sample.gui.view.ContenuView.ContenuView;
import sample.gui.view.ContenuView.FabriqueContenuView;
import sample.gui.view.ContenuView.ImageContenuView;
import sample.model.Contenu.Contenu;

import sample.model.Contenu.Image;
import sample.model.Contenu.TextZone;
import sample.model.Enums.Section;
import sample.model.Observateur.IObservateur;
import sample.model.Observateur.Observable;
import sample.model.Page;

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

    // Attributs
    private Page page;
    private Integer index;
    private String nomModule;
    private Section section;

    // Constructeur
    public PageView() {
        super();
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
        setMargin(scrollPane, new Insets(0, 20, 0, 20));

        setBottom(footerBox);
        setMargin(footerBox, new Insets(5, 20, 10, 20));

    }

    // Methodes
    public void setPage(Page _page, Integer _index, String _nomModule, Section _section) {
        page = _page;
        index = _index;
        nomModule = _nomModule;
        section = _section;
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
            Integer contentSize = page.getContenus().size();

            if(contentSize == 0 || !(page.getContenus().get( contentSize - 1) instanceof TextZone)) {
                // Le dernier element est un contenu dynamique
                TextZone tz = new TextZone();
                ContenuView cv = FabriqueContenuView.fabriquerContenuView(tz);
                contenuBox.getChildren().add(cv.afficher());

                page.appendContenu(tz);
            }

        } catch (Exception e) {

        }

        Button addImage = new Button();
        addImage.setText("Ajout image");
        addImage.setOnAction((event) -> {
            Contenu c = new Image();
            page.appendContenu(c);
            updateView();
        });

       headerBox.getChildren().add(addImage);
        // Footer
        footerBox.setAlignment(Pos.CENTER_RIGHT);

        Label pageLabel = new Label();
        pageLabel.setText("" + index);
        footerBox.getChildren().add(pageLabel);
    }
}




    // PageView doit savoir quelle TextAreaView a ete modifié

    // Mettre à jour le modele