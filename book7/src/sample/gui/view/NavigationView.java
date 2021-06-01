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
        HBox Gauche = new HBox();
        Gauche.setId("navigation_gauche");
        HBox Droite = new HBox();
        Droite.setId("navigation_droite");

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
        //        boutonGauche.setText("bouton gauche");

        // Bouton "Suivant"
        ImageView iconBoutonDroite = new ImageView("/icon-arrow-right.png");
        iconBoutonDroite.setPreserveRatio(true);
        iconBoutonDroite.setFitWidth(20);
        iconBoutonDroite.setEffect(couleurBlanche);
        boutonDroite.setGraphic(iconBoutonDroite);
//        boutonDroite.setText("bouton droit");

        boutonCours.setText("Cours");
        boutonCours.getStyleClass().add("bouton_cahier");
        boutonTD.setText("TD");
        boutonTD.getStyleClass().add("bouton_cahier");
        boutonTP.setText("TP");
        boutonTP.getStyleClass().add("bouton_cahier");


        Droite.setHgrow(boutonCours, Priority.ALWAYS);
        Droite.setHgrow(boutonTD, Priority.ALWAYS);
        Droite.setHgrow(boutonTP, Priority.ALWAYS);
        Gauche.setHgrow(boutonDroite, Priority.ALWAYS);
        Gauche.setHgrow(boutonGauche, Priority.ALWAYS);


        Gauche.getChildren().add(boutonGauche);
        Gauche.getChildren().add(boutonDroite);
        Droite.getChildren().add(boutonCours);
        Droite.getChildren().add(boutonTD);
        Droite.getChildren().add(boutonTP);

        getChildren().add(Gauche);
        getChildren().add(Droite);

    }

    public void setModuleController(ModuleController module) {
        moduleController = module;
    }
}