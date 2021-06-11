module book7 {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.fxmisc.richtext;
    requires javafx.web;
    requires org.apache.pdfbox;
    requires org.apache.fontbox;
    requires java.desktop;
    requires commons.io;
    requires javafx.swing;

    opens sample;
    opens sample.gui.controller;
    opens sample.gui.view;

}