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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private HashMap<Integer, List<Module>> modules;

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
        for (Integer key: modules.keySet()) {
            // Creation de la TitledPane
            TitledPane semestreTitledPane = new TitledPane();
            semestreTitledPane.getStyleClass().add("element_semestre");
            semestreTitledPane.setText("semestre " + key);
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

    public static HashMap<Integer, List<Module>> lireModules() {
        HashMap<Integer, List<Module>> out = new HashMap<Integer, List<Module>>();
        try {
            //Initialisation des variables qui seront réutilisées
            File root, repertoire;

            //Expression régulières

            Pattern p_semestre = Pattern.compile("semestre([0-9]+)$");
            Pattern p_fichier = Pattern.compile(".+\\.book7");

            Matcher m_semestre, m_fichier;

            //Chemin absolu du dossier où sont les fichiers
            String path=System.getProperty("user.dir")+"/ressources/sauvegarde_module";

            //On récupère la racine du dossier de sauvegarde pour récupérer tous les dossiers qu'il contient
            root = new File(path);
            String liste_r[] = root.list();
            for (int i = 0; i < liste_r.length; i++) {
                m_semestre = p_semestre.matcher(liste_r[i]);
                //On vérifie que le dossier ai un nom valide pour le traiter
                if(m_semestre.matches()) {
                    //Le cas échéant on récupère le numéro du dossier (semestreN on récupère N)
                    Integer numero=Integer.parseInt(m_semestre.group(1));

                    //On crée la liste et on la rempli de modules
                    List<Module> liste = new ArrayList<Module>();
                    repertoire = new File(path+"/"+liste_r[i]);
                    String liste_fichiers[] = repertoire.list();
                    //System.out.println(liste_fichiers);
                    for (int j = 0; j < liste_fichiers.length; j++) {
                        //On vérifie que le programme ait la bonne extension déjà
                        m_fichier=p_fichier.matcher(liste_fichiers[j]);
                        if (m_fichier.matches())
                            liste.add(extraireModule(path+"/"+liste_r[i]+"/"+liste_fichiers[j]));
                    }
                    //Et bim on incrémente la map
                    out.put(numero, liste);
                }
            }

        }
        catch (Exception e){
            //TODO : traiter les erreurs au lieu de tout afficher
            e.printStackTrace();
        }

        return out;
    }

    public static Module extraireModule(String path){ //On va propager les exceptions vers getModuleMap ou consors
        //C'est comme ça qu'on unserialize VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV
        //File fichier = new File(path);
        //ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier));
        return new Module("arouf ou salam", path);
    }

}
