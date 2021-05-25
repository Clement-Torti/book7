package sample.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;
import sample.model.Module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

// ------------------------
// Rôle: Classe controllant la vue de selection du module à éditer
// Dernière Modification: Clément Torti
//
public class ArborescenceController extends BaseController {
    // Constantes
    public static final Integer MODULE_WIDHT = 600;
    public static final Integer MODULE_HEIGHT = 400;
    public static final String MODULE_FXML = "gui/view/vueModule.fxml";

    // FXML Outlets
    @FXML
    private VBox mainVbox;

    // Attributs
    private HashMap<String, List<Module>> modules;

    // Constructeurs
    public ArborescenceController(Stage stage) {
        super(stage);
    }

    // Methodes
    @FXML
    void initialize() {
        //

        // Lire les modules
        modules = lireModules();


        // Affichage dynamique des modules
        // Parcourir l'ensemble des dossiers "semestre"
        for (String key: modules.keySet()) {
            // Creation de la TitledPane
            TitledPane semestreTitledPane = new TitledPane();
            semestreTitledPane.getStyleClass().add("element_semestre");
            semestreTitledPane.setText(key);
            semestreTitledPane.setExpanded(false);

            // Les modules sont ajoutés dans une VBox
            VBox modulesVBox = new VBox();
            modulesVBox.setSpacing(10);

            // Parcourir les modules d'un semestre
            for(Module m: modules.get(key)) {
                Button moduleButton = new Button();
                moduleButton.getStyleClass().add("element_module");
                moduleButton.setText(m.getNom());

                // Au clique, ouvrir la fenetre du cahier
                moduleButton.setOnAction((event) -> {
                    ModuleController moduleController = new ModuleController(getStage());
                    try {
                        openStage(MODULE_FXML,  moduleController, MODULE_WIDHT, MODULE_HEIGHT, m.getNom());
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                });

                modulesVBox.getChildren().add(moduleButton);
            }

            semestreTitledPane.setContent(modulesVBox);

            // Ajouter les elements aux enfants
            mainVbox.getChildren().add(semestreTitledPane);
        }

    }


    private HashMap<String, List<Module>> lireModules() {
        HashMap<String, List<Module>> modules = new HashMap<String, List<Module>>();

        Module m1 = new Module("Conception Objet", "/semestre1");
        Module m2 = new Module("Reseaux tel", "/semestre2");
        Module m3 = new Module("Math", "/semestre1");
        Module m4 = new Module("SHS", "/semestre1");

        ArrayList<Module> semestre5 = new ArrayList<>();
        semestre5.add(m1);
        semestre5.add(m2);

        ArrayList<Module> semestre6 = new ArrayList<>();
        semestre6.add(m3);

        ArrayList<Module> semestre7 = new ArrayList<>();
        semestre7.add(m4);

        ArrayList<Module> semestre8 = new ArrayList<>();


        modules.put("Semestre 5", semestre5);
        modules.put("Semestre 6", semestre6);
        modules.put("Semestre 7", semestre7);
        modules.put("Semestre 8", semestre8);


        return modules;
    }


}
