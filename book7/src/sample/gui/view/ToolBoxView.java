package sample.gui.view;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sample.model.Toolbox;
import sample.model.Enums.FontStyle;

public class ToolBoxView extends VBox {
    // Attributs
    private Toolbox toolbox;
    // Outlets


    // Constructeurs
    public ToolBoxView() {
        super();
        toolbox = new Toolbox();
        System.out.println("ToolboxView" + toolbox);
        updateView();
    }

    // Methodes
    private void updateView() {
        // Palette de couleur
        Text couleurTexte = new Text();
        couleurTexte.setFont(new Font(16));
        couleurTexte.setText("Couleurs");
        getChildren().add(couleurTexte);

        FlowPane paletteCouleur = new FlowPane();
        paletteCouleur.setId("palette_couleur");
        // Couleurs
        Color[] listeCouleurs = {
                Color.RED, Color.BLUE, Color.GREEN,
                Color.BLACK, Color.CADETBLUE, Color.TOMATO,
                Color.GHOSTWHITE, Color.VIOLET, Color.TURQUOISE,
                Color.PINK, Color.ORANGE, Color.LEMONCHIFFON
        };
        for (Color couleur : listeCouleurs){
            Button couleurBouton = new Button();
//            couleurBouton.setText();
            couleurBouton.getStyleClass().add("bouton_couleur");
            couleurBouton.setStyle("-fx-background-color: #" + couleur.toString().substring(2, 8) + ";");
            couleurBouton.setOnAction((event) -> {
                toolbox.setColor(couleur);
                System.out.println(toolbox.getColor());
            });
            paletteCouleur.getChildren().add(couleurBouton);
        }
        getChildren().add(paletteCouleur);

        // policeTexte (Liste déroulante)
        ComboBox<FontStyle> policeButton = new ComboBox<>();
        for (int i = 0; i < FontStyle.values().length; i++) {
            policeButton.getItems().add(FontStyle.values()[i]);;
        }
        policeButton.getSelectionModel().select(0);
        policeButton.getStyleClass().add("liste_deroulante");
        getChildren().add(policeButton);

        // taillePolice (Liste déroulante)
        ComboBox<Integer> taillePolice = new ComboBox<>();
        for (int i = 4; i <= 25; i++) {
            taillePolice.getItems().add(i);
        }
        taillePolice.getSelectionModel().select(8);
        taillePolice.getStyleClass().add("liste_deroulante");
        getChildren().add(taillePolice);

        // BOUTONS de style
        FlowPane boutonPanel = new FlowPane();
        boutonPanel.getStyleClass().add("liste_boutons_style");

        // gras (bouton)
        Button grasButton = new Button();
        ImageView iconGras = new ImageView("/icon-bold.png");
        iconGras.setPreserveRatio(true);
        iconGras.setFitHeight(20);
        grasButton.setGraphic(iconGras);
        grasButton.getStyleClass().add("bouton_style");
        grasButton.setOnAction((event) ->{
            toolbox.setGras(Boolean.TRUE);
        });
        boutonPanel.getChildren().add(grasButton);

        // italique (bouton)
        Button italiqueButton = new Button();
        ImageView iconItalique = new ImageView("/icon-talic.png");
        iconItalique.setPreserveRatio(true);
        iconItalique.setFitHeight(20);
        italiqueButton.setGraphic(iconItalique);
        italiqueButton.getStyleClass().add("bouton_style");
        italiqueButton.setOnAction((event) ->{
            ////XXX////
        });
        boutonPanel.getChildren().add(italiqueButton);

        // soulignement (bouton)
        Button soulignementButton = new Button();
        ImageView iconSoulignement = new ImageView("/icon-underline.png");
        iconSoulignement.setPreserveRatio(true);
        iconSoulignement.setFitHeight(20);
        soulignementButton.setGraphic(iconSoulignement);
        soulignementButton.getStyleClass().add("bouton_style");
        soulignementButton.setOnAction((event) ->{

        });
        boutonPanel.getChildren().add(soulignementButton);

        // surlignement (bouton)
        Button surlignementButton = new Button();
        ImageView iconSurlignement = new ImageView("/icon-highlighter.png");
        iconSurlignement.setPreserveRatio(true);
        iconSurlignement.setFitHeight(20);
        surlignementButton.setGraphic(iconSurlignement);
        surlignementButton.getStyleClass().add("bouton_style");
        surlignementButton.setOnAction((event) ->{
            ////XXX////
        });
        boutonPanel.getChildren().add(surlignementButton);

        // Ajout de la liste à la ToolBoxView
        getChildren().add(boutonPanel);
    }

    public Toolbox getToolbox() {
        return toolbox;
    }
}
