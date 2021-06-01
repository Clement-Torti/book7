package sample.gui.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
        Button colorButton = new Button();
        colorButton.setText("Get Color");
        colorButton.setOnAction((event) -> {
            System.out.println(toolbox);
            System.out.println(toolbox.getColor());
        });
        getChildren().add(colorButton);

        Button colorRedButton = new Button();
        colorRedButton.setText("Set red Color");
        colorRedButton.setOnAction((event) ->{
            toolbox.setColor(Color.RED);
        });
        getChildren().add(colorRedButton);

        // policeTexte (Liste déroulante)
        ComboBox policeButton = new ComboBox();
        for (int i = 0; i < FontStyle.values().length; i++) {
            policeButton.getItems().add(FontStyle.values()[i]);;
        }
        policeButton.getSelectionModel().select(0);
        policeButton.getStyleClass().add("toolBoxButton");
        getChildren().add(policeButton);

        // taillePolice (Liste déroulante)
        ComboBox tailleButton = new ComboBox();
        for (int i = 4; i <= 25; i++) {
            tailleButton.getItems().add(i);
        }
        tailleButton.getSelectionModel().select(8);
        tailleButton.getStyleClass().add("toolBoxButton");
        getChildren().add(tailleButton);

        // gras (bouton)
        Button grasButton = new Button();
        grasButton.getStyleClass().add("toolBoxButton");
        grasButton.setOnAction((event) ->{
            toolbox.setGras(Boolean.TRUE);
        });
        ImageView iconGras = new ImageView("/icon-bold.png");
        iconGras.setPreserveRatio(true);
        iconGras.setFitWidth(20);
        grasButton.setGraphic(iconGras);

        getChildren().add(grasButton);

        // italique (bouton)
        Button italiqueButton = new Button();
        ImageView iconItalique = new ImageView("/icon-talic.png");
        iconItalique.setPreserveRatio(true);
        iconItalique.setFitWidth(20);
        italiqueButton.setGraphic(iconItalique);
        italiqueButton.getStyleClass().add("toolBoxButton");
        italiqueButton.setOnAction((event) ->{
            ////XXX////
        });
        getChildren().add(italiqueButton);

        HBox hbox1 = new HBox(grasButton, italiqueButton);
        getChildren().add(hbox1);

        // soulignement (bouton)
        Button soulignementButton = new Button();
        ImageView iconSoulignement = new ImageView("/icon-underline.png");
        iconSoulignement.setPreserveRatio(true);
        iconSoulignement.setFitWidth(20);
        soulignementButton.setGraphic(iconSoulignement);
        soulignementButton.getStyleClass().add("toolBoxButton");
        soulignementButton.setOnAction((event) ->{

        });
        getChildren().add(soulignementButton);

        // surlignement (bouton)
        Button surlignementButton = new Button();
        ImageView iconSurlignement = new ImageView("/icon-highlighter.png");
        iconSurlignement.setPreserveRatio(true);
        iconSurlignement.setFitWidth(20);
        surlignementButton.setGraphic(iconSurlignement);
        surlignementButton.getStyleClass().add("toolBoxButton");
        surlignementButton.setOnAction((event) ->{
            ////XXX////
        });
        getChildren().add(surlignementButton);

        HBox hbox2 = new HBox(soulignementButton, surlignementButton);
        getChildren().add(hbox2);

        // formes
        Button shapeButton = new Button();
        shapeButton.setText("Shape");

        getChildren().add(shapeButton);
    }

    public Toolbox getToolbox() {
        return toolbox;
    }
}
