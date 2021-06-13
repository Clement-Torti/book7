package sample.gui.view;

import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import sample.gui.controller.ModuleController;
import sample.model.Enums.Section;


/**
 * Controller chargé de la navigation dans le module
 */
public class NavigationView extends HBox {
    // Attributs
    private ModuleController moduleController;
    private Integer buttonSize = 15;


    // Constructeur
    public NavigationView() {
        super();
        this.setSpacing(30);


        HBox hboxGauche = new HBox();
        hboxGauche.setId("navigation_gauche");
        HBox hboxMilieu = new HBox();
        hboxMilieu.setId("navigation_milieu");
        HBox hboxDroite = new HBox();
        hboxDroite.setId("navigation_droite");

        // Filtre blanc pour ImageView
        ColorAdjust couleurBlanche = new ColorAdjust();
        couleurBlanche.setBrightness(1);

        /*
            Bouton HBox Gauche
         */
        // Bouton "Précedent"
        Button boutonGauche = new Button();
        boutonGauche.getStyleClass().add("bouton_navigation");
        ImageView iconBoutonGauche = new ImageView("/icon-arrow-left.png");
        iconBoutonGauche.setPreserveRatio(true);
        iconBoutonGauche.setFitWidth(buttonSize);
        iconBoutonGauche.setEffect(couleurBlanche);
        boutonGauche.setGraphic(iconBoutonGauche);
        boutonGauche.setOnAction((event) -> {
            moduleController.previousPage();
        });
        // Bouton "Suivant"
        Button boutonDroite = new Button();
        boutonDroite.getStyleClass().add("bouton_navigation");
        ImageView iconBoutonDroite = new ImageView("/icon-arrow-right.png");
        iconBoutonDroite.setPreserveRatio(true);
        iconBoutonDroite.setFitWidth(buttonSize);
        iconBoutonDroite.setEffect(couleurBlanche);
        boutonDroite.setGraphic(iconBoutonDroite);
        boutonDroite.setOnAction((event) -> {
            moduleController.nextPage();
        });

        /*
            Boutons HBox milieu
         */
        // Bouton cahier "Cours"
        Button boutonCours = new Button();
        boutonCours.setText("Cours");
        boutonCours.getStyleClass().add("bouton_cahier");
        boutonCours.setOnAction((event) -> {
            moduleController.changerSection(Section.COURS);
        });
        // Bouton cahier "TD"
        Button boutonTD = new Button();
        boutonTD.setText("TD");
        boutonTD.getStyleClass().add("bouton_cahier");
        boutonTD.setOnAction((event) -> {
            moduleController.changerSection(Section.TD);
        });
        // Bouton cahier "TP"
        Button boutonTP = new Button();
        boutonTP.setText("TP");
        boutonTP.getStyleClass().add("bouton_cahier");
        boutonTP.setOnAction((event) -> {
            moduleController.changerSection(Section.TP);
        });

        /*
            Boutons HBox droite
         */
        // Bouton Fermer fenêtre
        Button boutonFermer = new Button();
        boutonFermer.getStyleClass().add("bouton_fermer");
        ImageView iconBoutonFermer = new ImageView("/icon-close.png");
        iconBoutonFermer.setPreserveRatio(true);
        iconBoutonFermer.setFitWidth(buttonSize);
        iconBoutonFermer.setEffect(couleurBlanche);
        boutonFermer.setGraphic(iconBoutonFermer);
        boutonFermer.setOnAction((event) -> {
            moduleController.fermerFenetre();
        });

        /*
            Ajout des boutons dans les Hbox
         */
        hboxGauche.getChildren().add(boutonGauche);
        hboxGauche.getChildren().add(boutonDroite);
        hboxMilieu.getChildren().add(boutonCours);
        hboxMilieu.getChildren().add(boutonTD);
        hboxMilieu.getChildren().add(boutonTP);
        hboxDroite.getChildren().add(boutonFermer);

        /*
            Ajout des HBox dans la NavigationView
         */
        getChildren().add(hboxGauche);
        getChildren().add(hboxMilieu);
        getChildren().add(hboxDroite);

    }


    /**
     * Définie le module controller intéressé par des actions de navigation
     * (Devrait être remplacé par le patron observateur)
     * @param module module à prévenir en cas d'action utilisateur
     */
    public void setModuleController(ModuleController module) {
        moduleController = module;
    }
}