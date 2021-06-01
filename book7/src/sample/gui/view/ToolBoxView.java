package sample.gui.view;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ToolBoxView extends VBox {
    // Attributs

    // Outlets

    // Constructeurs
    public ToolBoxView() {
        super();
        updateView();
    }

    // Methodes
    private void updateView() {
        Button policeButton = new Button();
        policeButton.setText("Police");

        getChildren().add(policeButton);

        Button colorButton = new Button();
        colorButton.setText("Color");

        getChildren().add(colorButton);

        Button shapeButton = new Button();
        shapeButton.setText("Shape");

        getChildren().add(shapeButton);
    }
}
