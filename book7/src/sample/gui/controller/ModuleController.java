package sample.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import sample.gui.view.CahierView;
import sample.gui.view.NavigationView;
import sample.model.Enums.Section;
import sample.model.Module;

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
    private Module module;

    // Constructeurs
    public ModuleController(Stage stage, Module module) {
        super(stage);
        this.module = module;
    }

    // FXML Actions
    @FXML
    private void initialize() {
        // Permet à la navigation view de communiquer les changements de section ...
        navigationView.setModuleController(this);

        currentCahier.setCahier(module.getCahiers().get(0));
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
