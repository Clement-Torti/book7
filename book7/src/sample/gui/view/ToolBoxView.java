package sample.gui.view;

import javafx.geometry.Pos;
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

import java.util.ArrayList;
import java.util.List;

public class ToolBoxView extends VBox {
    // Attributs
    private Toolbox toolbox;
    private Integer buttonSize = 15;
    // Outlets


    // Constructeurs
    public ToolBoxView() {
        super();
        toolbox = new Toolbox();
        updateView();
    }

    // Methodes
    private void updateView() {
        /*
            Motifs
         */
        // Hbox des motifs
        HBox hboxMotif = new HBox();
        hboxMotif.setSpacing(5);
        hboxMotif.setId("hbox_motif");

        Text motifTexte = new Text();
        motifTexte.setFont(new Font(16));
        motifTexte.setText("Fonds");
        getChildren().add(motifTexte);

        //Bouton motif grands carreaux
        Button motifGCButton = new Button();
        ImageView iconGC = new ImageView("/icon-big-grid.png");
        iconGC.setPreserveRatio(true);
        iconGC.setFitHeight(buttonSize);
        motifGCButton.setGraphic(iconGC);
        motifGCButton.getStyleClass().add("bouton_style_motif");
        motifGCButton.getStyleClass().add("motif");
        motifGCButton.setOnAction((event) ->{
            toolbox.setMotif("GrandCarreaux");
        });
        hboxMotif.getChildren().add(motifGCButton);

        //Bouton motif petits carreaux
        Button motifPCButton = new Button();
        ImageView iconPC = new ImageView("/icon-small-grid.png");
        iconPC.setPreserveRatio(true);
        iconPC.setFitHeight(buttonSize);
        motifPCButton.setGraphic(iconPC);
        motifPCButton.getStyleClass().add("bouton_style_motif");
        motifPCButton.getStyleClass().add("motif");
        motifPCButton.setOnAction((event) ->{
            toolbox.setMotif("PetitCarreaux");
        });
        hboxMotif.getChildren().add(motifPCButton);

        //Bouton motif Points
        Button motifPointsButton = new Button();
        ImageView iconPoints = new ImageView("/icon-points-grid.png");
        iconPoints.setPreserveRatio(true);
        iconPoints.setFitHeight(buttonSize);
        motifPointsButton.setGraphic(iconPoints);
        motifPointsButton.getStyleClass().add("bouton_style_motif");
        motifPointsButton.getStyleClass().add("motif");
        motifPointsButton.setOnAction((event) ->{
            toolbox.setMotif("Points");
        });
        hboxMotif.getChildren().add(motifPointsButton);

        //Bouton motif page blanche
        Button motifRienButton = new Button();
        motifRienButton.getStyleClass().add("bouton_style_motif");
        motifRienButton.getStyleClass().add("motif");
        motifRienButton.setOnAction((event) ->{
            toolbox.setMotif("Vide");
        });
        hboxMotif.getChildren().add(motifRienButton);

        getChildren().add(hboxMotif);

        /*
            Palette de couleur
        */
        Text couleurTexte = new Text();
        couleurTexte.setFont(new Font(16));
        couleurTexte.setText("Couleurs");
        getChildren().add(couleurTexte);

        FlowPane paletteCouleur = new FlowPane();
        paletteCouleur.setAlignment(Pos.CENTER);
        paletteCouleur.setId("palette_couleur");
        // Couleurs
        Color[] listeCouleurs = {
                Color.RED, Color.BLUE, Color.GREEN,
                Color.BLACK, Color.CADETBLUE, Color.TOMATO,
                Color.GHOSTWHITE, Color.VIOLET, Color.TURQUOISE};

        List<Button> couleurButtons = new ArrayList<>();
        for (Color couleur : listeCouleurs) {
            couleurButtons.add(new Button());
        }

        for (int i=0; i<listeCouleurs.length; i++){
            Color couleur = listeCouleurs[i];
            Button couleurBouton = couleurButtons.get(i);

            couleurBouton.getStyleClass().add("bouton_couleur");
            couleurBouton.setStyle("-fx-background-color: #" + couleur.toString().substring(2, 8) + ";");
            couleurBouton.setOnAction((event) -> {
                for(Button button: couleurButtons) {
                    button.getStyleClass().remove("selected_button");
                }

                couleurBouton.getStyleClass().add("selected_button");
                toolbox.setColor(couleur);
            });
            paletteCouleur.getChildren().add(couleurBouton);
        }
        getChildren().add(paletteCouleur);

        /*
            Police et taille de text
         */
        Text texteTexte = new Text();
        texteTexte.setFont(new Font(16));
        texteTexte.setText("Texte");
        getChildren().add(texteTexte);

        // policeTexte (Liste déroulante)
        List<String> fontFamilies = Font.getFamilies();

        ComboBox<String> policeButton = new ComboBox<>();
        for(String font : fontFamilies) {
            policeButton.getItems().add(font);
        }
        policeButton.setOnAction((event) -> {
            String selectedFont = policeButton.getSelectionModel().getSelectedItem();
            toolbox.setPoliceTexte(selectedFont);
        });

        policeButton.getSelectionModel().select(0);
        policeButton.getStyleClass().add("liste_deroulante");
        getChildren().add(policeButton);

        // taillePolice (Liste déroulante)
        ComboBox<Integer> taillePolice = new ComboBox<>();
        for (int i = 8; i <= 25; i++) {
            taillePolice.getItems().add(i);
        }
        taillePolice.setOnAction((event) -> {
            Integer selectedSize = taillePolice.getSelectionModel().getSelectedItem();
            toolbox.setTaillePolice(selectedSize);
        });
        taillePolice.getSelectionModel().select(8);
        taillePolice.getStyleClass().add("liste_deroulante");
        getChildren().add(taillePolice);

        /*
            Boutons de style
         */
        FlowPane boutonPanel = new FlowPane();
        boutonPanel.getStyleClass().add("liste_boutons_style");


        /*
            Style de texte
         */
        // gras (bouton)
        Button grasButton = new Button();
        ImageView iconGras = new ImageView("/icon-bold.png");
        iconGras.setPreserveRatio(true);
        iconGras.setFitHeight(buttonSize);
        grasButton.setGraphic(iconGras);
        grasButton.getStyleClass().add("bouton_style");
        grasButton.setOnAction((event) ->{
            toolbox.flipGras();
            if(toolbox.getGras()) {
                grasButton.getStyleClass().add("selected_button");
            } else {
                grasButton.getStyleClass().remove("selected_button");
            }
        });
        boutonPanel.getChildren().add(grasButton);

        // italique (bouton)
        Button italiqueButton = new Button();
        ImageView iconItalique = new ImageView("/icon-talic.png");
        iconItalique.setPreserveRatio(true);
        iconItalique.setFitHeight(buttonSize);
        italiqueButton.setGraphic(iconItalique);
        italiqueButton.getStyleClass().add("bouton_style");
        italiqueButton.setOnAction((event) ->{
            toolbox.flipItalique();
            if(toolbox.getItalique()) {
                italiqueButton.getStyleClass().add("selected_button");
            } else {
                italiqueButton.getStyleClass().remove("selected_button");
            }
        });
        boutonPanel.getChildren().add(italiqueButton);

        // soulignement (bouton)
        Button soulignementButton = new Button();
        ImageView iconSoulignement = new ImageView("/icon-underline.png");
        iconSoulignement.setPreserveRatio(true);
        iconSoulignement.setFitHeight(buttonSize);
        soulignementButton.setGraphic(iconSoulignement);
        soulignementButton.getStyleClass().add("bouton_style");
        soulignementButton.setOnAction((event) ->{
            toolbox.flipSoulignement();
            if(toolbox.getSoulignement()) {
                soulignementButton.getStyleClass().add("selected_button");
            } else {
                soulignementButton.getStyleClass().remove("selected_button");
            }
        });
        boutonPanel.getChildren().add(soulignementButton);



        // Ajout de la liste à la ToolBoxView
        getChildren().add(boutonPanel);
    }

    public Toolbox getToolbox() {
        return toolbox;
    }
}