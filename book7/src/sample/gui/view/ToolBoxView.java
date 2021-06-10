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
        hboxMotif.setId("hbox_motif");

        Text motifTexte = new Text();
        motifTexte.setFont(new Font(16));
        motifTexte.setText("Motifs");
        getChildren().add(motifTexte);

        //Bouton motif grands carreaux
        Button motifGCButton = new Button();
        ImageView iconGC = new ImageView("/icon-big-grid.png");
        iconGC.setPreserveRatio(true);
        iconGC.setFitHeight(buttonSize);
        motifGCButton.setGraphic(iconGC);
        motifGCButton.getStyleClass().add("bouton_style");
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
        motifPCButton.getStyleClass().add("bouton_style");
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
        motifPointsButton.getStyleClass().add("bouton_style");
        motifPointsButton.getStyleClass().add("motif");
        motifPointsButton.setOnAction((event) ->{
            toolbox.setMotif("Points");
        });
        hboxMotif.getChildren().add(motifPointsButton);

        //Bouton motif page blanche
        Button motifRienButton = new Button();
        motifRienButton.getStyleClass().add("bouton_style");
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
            });
            paletteCouleur.getChildren().add(couleurBouton);
        }
        getChildren().add(paletteCouleur);

        /*
            Police et taille de text
         */
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
            Alignement
         */
        //Bouton alignement Gauche
        Button gaucheButton = new Button();
        ImageView iconGauche = new ImageView("/icon-align-left.png");
        iconGauche.setPreserveRatio(true);
        iconGauche.setFitHeight(buttonSize);
        gaucheButton.setGraphic(iconGauche);
        gaucheButton.getStyleClass().add("bouton_style");
        gaucheButton.setOnAction((event) ->{
        });
        boutonPanel.getChildren().add(gaucheButton);

        //Bouton alignement Centre
        Button centreButton = new Button();
        ImageView iconCentre = new ImageView("/icon-align-center.png");
        iconCentre.setPreserveRatio(true);
        iconCentre.setFitHeight(buttonSize);
        centreButton.setGraphic(iconCentre);
        centreButton.getStyleClass().add("bouton_style");
        centreButton.setOnAction((event) ->{
        });
        boutonPanel.getChildren().add(centreButton);

        //Bouton alignement Droit
        Button droitButton = new Button();
        ImageView iconDroit = new ImageView("/icon-align-right.png");
        iconDroit.setPreserveRatio(true);
        iconDroit.setFitHeight(buttonSize);
        droitButton.setGraphic(iconDroit);
        droitButton.getStyleClass().add("bouton_style");
        droitButton.setOnAction((event) ->{
        });
        boutonPanel.getChildren().add(droitButton);

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
        });
        boutonPanel.getChildren().add(soulignementButton);



        // Ajout de la liste à la ToolBoxView
        getChildren().add(boutonPanel);
    }

    public Toolbox getToolbox() {
        return toolbox;
    }
}