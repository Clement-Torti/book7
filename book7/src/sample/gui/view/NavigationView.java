package sample.gui.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import sample.gui.controller.ModuleController;
import sample.model.Enums.Section;

import java.io.IOException;

public class NavigationView extends HBox {
    // Attributs
    private ModuleController moduleController;

    public NavigationView() {
        super();
        this.setSpacing(100);
        HBox hboxGauche = new HBox();
        hboxGauche.setId("navigation_gauche");
        HBox hboxDroite = new HBox();
        hboxDroite.setId("navigation_droite");

        Button boutonGauche = new Button();
        boutonGauche.getStyleClass().add("bouton_navigation");
        Button boutonDroite = new Button();
        boutonDroite.getStyleClass().add("bouton_navigation");
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

        boutonCours.setText("Cours");
        boutonCours.getStyleClass().add("bouton_cahier");
        boutonTD.setText("TD");
        boutonTD.getStyleClass().add("bouton_cahier");
        boutonTP.setText("TP");
        boutonTP.getStyleClass().add("bouton_cahier");


        hboxDroite.setHgrow(boutonCours, Priority.ALWAYS);
        hboxDroite.setHgrow(boutonTD, Priority.ALWAYS);
        hboxDroite.setHgrow(boutonTP, Priority.ALWAYS);
        hboxGauche.setHgrow(boutonDroite, Priority.ALWAYS);
        hboxGauche.setHgrow(boutonGauche, Priority.ALWAYS);


        hboxGauche.getChildren().add(boutonGauche);
        hboxGauche.getChildren().add(boutonDroite);
        hboxDroite.getChildren().add(boutonCours);
        hboxDroite.getChildren().add(boutonTD);
        hboxDroite.getChildren().add(boutonTP);

        getChildren().add(hboxGauche);
        getChildren().add(hboxDroite);

    }

    public void setModuleController(ModuleController module) {
        moduleController = module;
    }
}