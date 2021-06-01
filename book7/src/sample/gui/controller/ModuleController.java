package sample.gui.controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import sample.gui.view.CahierView;
import sample.gui.view.NavigationView;
import sample.gui.view.ToolBoxView;
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
    @FXML
    private ToolBoxView toolBoxView;


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

        updateView();
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
        updateView();
    }

    public void nextPage() {
        currentCahier.nextPage();
        updateView();
    }

    public void previousPage() {
        currentCahier.previousPage();
        updateView();
    }

    private void updateView() {
        switch (currentSection) {
            case COURS:
                currentCahier.setCahier(module.getCours(), module.getNom());
                break;
            case TD:
                currentCahier.setCahier(module.getTD(), module.getNom());
                break;
            case TP:
                currentCahier.setCahier(module.getTP(), module.getNom());
                break;
        }

    }
}
