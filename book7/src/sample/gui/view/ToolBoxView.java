package sample.gui.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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
        getChildren().add(policeButton);

        // taillePolice (Liste déroulante)
        ComboBox tailleButton = new ComboBox();
        for (int i = 4; i <= 25; i++) {
            tailleButton.getItems().add(i);
        }
        tailleButton.getSelectionModel().select(8);
        getChildren().add(tailleButton);

        // gras (bouton)
        Button grasButton = new Button();
        grasButton.setText("Set gras");
        grasButton.setOnAction((event) ->{
            toolbox.setGras(Boolean.TRUE);
            //text1.setStyle("-fx-font-weight: bold");
        });
        getChildren().add(grasButton);

        // italique (bouton)
        Button italiqueButton = new Button();
        italiqueButton.setText("Set italique");
        italiqueButton.setOnAction((event) ->{

        });
        getChildren().add(italiqueButton);

        // soulignement (bouton)
        Button soulignementButton = new Button();
        soulignementButton.setText("Set soulignement");
        soulignementButton.setOnAction((event) ->{

        });
        getChildren().add(soulignementButton);

        // surlignement (bouton)
        Button surlignementButton = new Button();
        surlignementButton.setText("Set surlignement");
        surlignementButton.setOnAction((event) ->{

        });
        getChildren().add(surlignementButton);

        // formes
        Button shapeButton = new Button();
        shapeButton.setText("Shape");

        getChildren().add(shapeButton);
    }

    public Toolbox getToolbox() {
        return toolbox;
    }
}
