package sample.gui.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
        HBox Droite = new HBox();
        Gauche.setSpacing(10);
        Droite.setSpacing(10);

        Button boutonGauche = new Button();
        Button boutonDroite = new Button();
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

        boutonGauche.setText("bouton gauche");
        boutonDroite.setText("bouton droit");

        boutonCours.setText("cours");
        boutonTD.setText("TD");
        boutonTP.setText("TP");


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