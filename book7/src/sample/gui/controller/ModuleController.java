package sample.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.gui.Utils.FileOpener;
import sample.gui.view.CahierView;
import sample.gui.view.NavigationView;
import sample.gui.view.ToolBoxView;
import sample.model.Enums.Section;
import sample.model.Module;
import sample.model.Toolbox;
import sample.model.Persistence.ModuleWriter;

// ------------------------
// Rôle: Classe controlant un module enseeiht, pour l'afficher et l'éditer
// Création: Clément Torti
// Dernière Modification: Clément Torti
//
public class ModuleController extends BaseController {
    // Static
    private static int NB_OPERATION_BEFORE_SAVING = 20;
    private static int current_nb_operation = 0;

    // FXML Outlets
    @FXML
    private BorderPane root;
    private CahierView currentCahier;
    @FXML
    private NavigationView navigationView;
    @FXML
    private ToolBoxView toolBoxView;

    // Attributs
    private Section currentSection = Section.COURS;
    private static Module module;
    private FileOpener fileOpener;
    private Toolbox toolbox;

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
        // Toolbox
        toolbox = toolBoxView.getToolbox();
        // Cahier View
        fileOpener = new FileOpener(getStage());
        currentCahier = new CahierView(fileOpener, this);
        root.setCenter(currentCahier);

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
        currentCahier.setPage(0);
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
                currentCahier.setCahier(module.getCours(), module.getNom(), toolbox);
                break;
            case TD:
                currentCahier.setCahier(module.getTD(), module.getNom(), toolbox);
                break;
            case TP:
                currentCahier.setCahier(module.getTP(), module.getNom(), toolbox);
                break;
        }

    }

    public static boolean sauvegarderModule() {
        // Ne sauvegarder qu'au bout de N operation (pour les performance car exriture disque lent)

        if(current_nb_operation > NB_OPERATION_BEFORE_SAVING) {
            ModuleWriter mw = new ModuleWriter();
            mw.ecrire(module);

            current_nb_operation = 0;
            return true;
        }

        current_nb_operation += 1;
        return false;
    }

    public static void forcerSauvegarde() {
            ModuleWriter mw = new ModuleWriter();
            mw.ecrire(module);
    }

    public void fermerFenetre() {
        forcerSauvegarde();
        getStage().close();
    }
}
