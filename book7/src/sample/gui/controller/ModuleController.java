package sample.gui.controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import sample.gui.view.CahierView;

// ------------------------
// Rôle: Classe controlant un module enseeiht, pour l'afficher et l'éditer
// Création: Clément Torti
// Dernière Modification: Clément Torti
//
public class ModuleController extends BaseController {
    // Constantes

    // FXML Outlets
    @FXML
    private CahierView currentCahier;

    // Attributs

    // Constructeurs
    public ModuleController(Stage stage) {
        super(stage);
        System.out.println("Creation de cahier");
    }

    // FXML Actions
    @FXML
    private void initialize() {
        System.out.println("Init ModuleController");
    }


    // Methodes
}
