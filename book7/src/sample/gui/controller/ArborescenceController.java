package sample.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

// ------------------------
// Rôle: Classe Controllant la vue de selection de module
// Dernière Modification: Clément Torti
//
public class ArborescenceController extends BaseController {

    // Attributs

    // Constructeurs
    public ArborescenceController(Stage stage) {
        super(stage);
    }

    // Methodes
    @FXML
    void initialize() {
        System.out.println("Test");
        //getStage().getScene().getStylesheets().add(getClass().getResource("/css/vueArborescence.css").toExternalForm());

    }
}
