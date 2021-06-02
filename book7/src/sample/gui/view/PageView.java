package sample.gui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import sample.Main;
import sample.gui.view.ContenuView.ContenuView;
import sample.gui.view.ContenuView.FabriqueContenuView;
import sample.model.Contenu.Contenu;
import sample.model.Enums.Section;
import sample.model.Page;
import sample.model.Toolbox;

import java.util.Locale;

// ------------------------
// Rôle: Classe controllant une page du cahier
// Création: Clément Torti
// Dernière Modification: Clément Torti
//
public class PageView extends BorderPane {
    // Outlets
    private HBox headerBox = new HBox();
    private VBox contenuBox = new VBox();
    private HBox footerBox = new HBox();

    // Attributs
    private Page page;
    private Integer index;
    private String nomModule;
    private Section section;
    private Toolbox toolbox;

    // Constructeur
    public PageView() {
        super();
        // CSS
        getStylesheets().add("vuePage.css");
        setId("mainVue");

        // Elements
        setTop(headerBox);
        setMargin(headerBox, new Insets(10));
        setCenter(contenuBox);
        setMargin(contenuBox, new Insets(0, 20, 0, 20));
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

        // Le dernier element est un contenu dynamique
        TextField textField = new TextField();
        contenuBox.getChildren().add(textField);

        // Footer
        footerBox.setAlignment(Pos.CENTER_RIGHT);

        Label pageLabel = new Label();
        pageLabel.setText("" + index);
        footerBox.getChildren().add(pageLabel);
    }
}
