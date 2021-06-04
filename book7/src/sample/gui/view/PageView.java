package sample.gui.view;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import java.nio.file.Files;
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
            contenuBox.getChildren().add(getDefaultContenuView(c));
        }

        try {
            int contentSize = page.getContenus().size();
            if(contentSize == 0 || !(page.getContenus().get( contentSize - 1) instanceof TextZone)) {
                // Le dernier element est un contenu dynamique
                TextZone tz = new TextZone();
                page.appendContenu(tz);

                contenuBox.getChildren().add(getDefaultContenuView(tz));
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
            String pwd = System.getProperty("user.dir");
            File chemindossier = new File(pwd + "/ressources/image_sauvegarde");
            if(! chemindossier.isDirectory()){
                chemindossier.mkdir();
            }

            File f = fileOpener.getFile();

            if(f != null) {

                try {
                    String[] splitChemin = f.toString().split("/");
                    String nomImage = splitChemin[splitChemin.length-1];
                    String dest = chemindossier + "/" + nomImage;
                    System.out.println(f.toString());
                    System.out.println(nomImage);
                    System.out.println(dest);
                    File cheminDest = new File(dest);
                    if(! cheminDest.exists()) {
                        Files.copy(f.toPath(), cheminDest.toPath());
                    }
                    ImageBook7 imageBook7 = new ImageBook7(cheminDest.getAbsoluteFile().toURI());
                    page.appendContenu(imageBook7);
                    updateView();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        footerBox.getChildren().add(addImage);

        Label pageLabel = new Label();
        pageLabel.setText("" + index);
        footerBox.getChildren().add(pageLabel);
    }

    private Node getDefaultContenuView(Contenu c) {
        HBox contenuHBox = new HBox();

        ContenuView cv = FabriqueContenuView.fabriquerContenuView(c);
        contenuHBox.getChildren().add(cv.afficher());

        Button deleteBtn = new Button();
        deleteBtn.setText("delete");
        deleteBtn.setOnAction((event) -> {
            page.getContenus().remove(c);
            updateView();
        });
        contenuHBox.getChildren().add(deleteBtn);


        return contenuHBox;
    }
}
