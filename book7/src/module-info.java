module book7 {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires itextpdf;

    opens sample;
    opens sample.gui.controller;
    opens sample.gui.view;

}