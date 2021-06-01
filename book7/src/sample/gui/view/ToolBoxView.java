package sample.gui.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import sample.model.Toolbox;

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
        Button policeButton = new Button();
        policeButton.setText("Police");

        getChildren().add(policeButton);

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

        Button shapeButton = new Button();
        shapeButton.setText("Shape");

        getChildren().add(shapeButton);
    }

    public Toolbox getToolbox() {
        return toolbox;
    }
}
