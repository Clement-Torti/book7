package sample.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import sample.gui.view.CahierView;
import sample.gui.view.NavigationView;
import sample.model.Enums.Section;

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
    @FXML
    private NavigationView navigationView;

    // Attributs
    private Section currentSection = Section.COURS;

    // Constructeurs
    public ModuleController(Stage stage) {
        super(stage);
        System.out.println("Creation de cahier");

    }

    // FXML Actions
    @FXML
    private void initialize() {
        navigationView.setModuleController(this);
    }

    // Methodes
    // role: changer la section afficher
    // params:
    // - nouvelleSection: nouvelle section à afficher
    public void changerSection(Section nouvelleSection) {
        if (nouvelleSection == currentSection) {
            return;
        }
        currentSection = nouvelleSection;
    }
}
