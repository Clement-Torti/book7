package sample.gui.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import sample.gui.controller.ModuleController;
import sample.model.Enums.Section;

import java.io.IOException;

public class NavigationView extends HBox {
    // Attributs
    private Stage moduleStage;
    private ModuleController moduleController;

    public NavigationView() {
        super();
        this.setSpacing(100);
        HBox hboxGauche = new HBox();
        hboxGauche.setId("navigation_gauche");
        HBox hboxMilieu = new HBox();
        hboxMilieu.setId("navigation_milieu");
        HBox hboxDroite = new HBox();
        hboxDroite.setId("navigation_droite");

        // Navigation gauche
        Button boutonGauche = new Button();
        boutonGauche.getStyleClass().add("bouton_navigation");
        Button boutonDroite = new Button();
        boutonDroite.getStyleClass().add("bouton_navigation");

        // Navigation Milieu
        Button boutonCours = new Button();
        boutonCours.setOnAction((event) -> {
            moduleController.changerSection(Section.COURS);
        });
        Button boutonTD = new Button();
        boutonTD.setOnAction((event) -> {
            moduleController.changerSection(Section.TD);
        });
        Button boutonTP = new Button();
        boutonTP.setOnAction((event) -> {
            moduleController.changerSection(Section.TP);
        });

        // Navigation Droite
        Button boutonFermer = new Button();


        ColorAdjust couleurBlanche = new ColorAdjust();
        couleurBlanche.setBrightness(1);

        // Bouton "Précedent"
        ImageView iconBoutonGauche = new ImageView("/icon-arrow-left.png");
        iconBoutonGauche.setPreserveRatio(true);
        iconBoutonGauche.setFitWidth(20);
        iconBoutonGauche.setEffect(couleurBlanche);
        boutonGauche.setGraphic(iconBoutonGauche);
        boutonGauche.setOnAction((event) -> {
            moduleController.previousPage();
        });
        //        boutonGauche.setText("bouton gauche");

        // Bouton "Suivant"
        ImageView iconBoutonDroite = new ImageView("/icon-arrow-right.png");
        iconBoutonDroite.setPreserveRatio(true);
        iconBoutonDroite.setFitWidth(20);
        iconBoutonDroite.setEffect(couleurBlanche);
        boutonDroite.setGraphic(iconBoutonDroite);
        boutonDroite.setOnAction((event) -> {
            moduleController.nextPage();
        });

        // Boutons cahiers
        boutonCours.setText("Cours");
        boutonCours.getStyleClass().add("bouton_cahier");
        boutonTD.setText("TD");
        boutonTD.getStyleClass().add("bouton_cahier");
        boutonTP.setText("TP");
        boutonTP.getStyleClass().add("bouton_cahier");

        // Boutons de fermeture
        ImageView iconFermer = new ImageView("/icon-close.png");
        iconFermer.setPreserveRatio(true);
        iconFermer.setFitWidth(20);
        iconFermer.setEffect(couleurBlanche);
        boutonFermer.getStyleClass().add("bouton_fermer");
        boutonFermer.setGraphic(iconFermer);
        boutonFermer.setOnAction((event) ->{
           moduleStage.close();
        });

        hboxMilieu.setHgrow(boutonCours, Priority.ALWAYS);
        hboxMilieu.setHgrow(boutonTD, Priority.ALWAYS);
        hboxMilieu.setHgrow(boutonTP, Priority.ALWAYS);
        hboxGauche.setHgrow(boutonDroite, Priority.ALWAYS);
        hboxGauche.setHgrow(boutonGauche, Priority.ALWAYS);
        hboxDroite.setHgrow(boutonFermer, Priority.ALWAYS);

        hboxGauche.getChildren().add(boutonGauche);
        hboxGauche.getChildren().add(boutonDroite);
        hboxMilieu.getChildren().add(boutonCours);
        hboxMilieu.getChildren().add(boutonTD);
        hboxMilieu.getChildren().add(boutonTP);
        hboxDroite.getChildren().add(boutonFermer);

        getChildren().add(hboxGauche);
        getChildren().add(hboxMilieu);
        getChildren().add(hboxDroite);

    }

    public void setModuleController(ModuleController module, Stage stage) {
        moduleController = module;
        moduleStage = stage;
    }
}