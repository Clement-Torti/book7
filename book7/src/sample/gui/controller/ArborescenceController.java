package sample.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.model.Module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

// ------------------------
// Rôle: Classe Controllant la vue de selection de module
// Dernière Modification: Clément Torti
//
public class ArborescenceController extends BaseController {
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
        // Lire les modules
        modules = lireModules();

        System.out.println(modules);

        // Affichage dynamique
        // Acceder a ses enfants
        for (String key: modules.keySet()) {
            // Creation de la TitledPane
            TitledPane semestreTitledPane = new TitledPane();
            semestreTitledPane.setText(key);
            semestreTitledPane.setExpanded(false);

            // Ajout d'une Vbox contenant les modules
            VBox modulesVBox = new VBox();
            modulesVBox.setSpacing(10);

            for(Module m: modules.get(key)) {
                Button moduleButton = new Button();
                moduleButton.setText(m.getNom());

                // Au clique, ouvrir la fenetre du cahier
                moduleButton.setOnAction((event) -> {
                    CahierController cahierController = new CahierController(getStage());

                    //openStage("",  cahierController, 600, 400);
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
