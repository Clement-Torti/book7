package sample.gui.controller;

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


/**
 * Controller mettant en relation les cahiers, la navigation et la toolbox
 */
public class ModuleController extends BaseController {
    // Constantes
    private static int NB_OPERATION_BEFORE_SAVING = 20;
    private static int current_nb_operation = 0;


    // Attributs
    private Section currentSection = Section.COURS;
    private static Module module;
    private FileOpener fileOpener;
    private Toolbox toolbox;


    // FXML Outlets
    @FXML
    private BorderPane root;
    private CahierView currentCahier;
    @FXML
    private NavigationView navigationView;
    @FXML
    private ToolBoxView toolBoxView;


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


    /**
     * Changement de cahier à afficher
     * @param nouvelleSection type de cahier à afficher
     */
    public void changerSection(Section nouvelleSection) {
        if (nouvelleSection == currentSection) {
            return;
        }
        currentSection = nouvelleSection;
        currentCahier.setPage(0);
        updateView();
    }


    /**
     * Passer à la page suivante
     */
    public void nextPage() {
        currentCahier.nextPage();
        updateView();
    }


    /**
     * Retourner à la page précédence
     */
    public void previousPage() {
        currentCahier.previousPage();
        updateView();
    }


    /**
     * Mise à jour de la vue quand le contenu à afficher change
     */
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


    /**
     * Sauvegarde du module sur le disque au bout de NB_OPERATION_BEFORE_SAVING appels
     * @return la sauvegarde à eu lieu
     */
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


    /**
     * Sauvegarde du module sur le disque
     */
    public static void forcerSauvegarde() {
            ModuleWriter mw = new ModuleWriter();
            mw.ecrire(module);
    }

    /**
     * Fermeture de la fenêtre
     */
    public void fermerFenetre() {
        forcerSauvegarde();
        getStage().close();
    }
}
