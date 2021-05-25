package sample.gui.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.model.Constantes;
import sample.model.Module;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// ------------------------
// Rôle: Classe controllant la vue de selection du module à éditer
// Dernière Modification: Clément Torti
//
public class ArborescenceController extends BaseController {
    // Constantes
    public static final Integer MODULE_WIDTH = 900;
    public static final Integer MODULE_HEIGHT = 600;
    public static final String MODULE_FXML = "gui/view/vueModule.fxml";

    // FXML Outlets
    @FXML private VBox mainVbox;
    @FXML private VBox contentVbox;
    @FXML private MenuItem menuFichierQuitter;

    // Attributs
    private HashMap<Integer, List<Module>> modules;

    // Constructeurs
    public ArborescenceController(Stage stage) {
        super(stage);
    }

    // Methodes
    @FXML
    void initialize() {
        // Barre de menu
        menuFichierQuitter.setAccelerator(KeyCombination.keyCombination("Ctrl+W"));
        menuFichierQuitter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

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
                    ModuleController moduleController = new ModuleController(getStage(), m);
                    try {
                        openStage(MODULE_FXML,  moduleController, MODULE_WIDTH, MODULE_HEIGHT, m.getNom());
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                });

                modulesVBox.getChildren().add(moduleButton);
            }

            semestreTitledPane.setContent(modulesVBox);

            // Ajouter les elements aux enfants
            contentVbox.getChildren().add(semestreTitledPane);
        }

    }

    public static HashMap<Integer, List<Module>> lireModules() {
        HashMap<Integer, List<Module>> out = new HashMap<Integer, List<Module>>();
        try {
            //Initialisation des variables qui seront réutilisées
            File root, repertoire;

            //Expression régulières

            Pattern p_semestre = Pattern.compile(Constantes.SEMESTRE_NAME+"([0-9]+)$");
            Pattern p_fichier = Pattern.compile(".+\\."+ Constantes.EXTENSION);

            Matcher m_semestre, m_fichier;

            //Chemin absolu du dossier où sont les fichiers
            String path=System.getProperty("user.dir")+"/"+Constantes.SAVE_ROOT_FOLDER_NAME;

            root = new File(path);
            //On vérifie si le dossier existe, sinon on le crée
            if(! root.isDirectory())
                root.mkdirs();

            //On récupère la racine du dossier de sauvegarde pour récupérer tous les dossiers qu'il contient
            String liste_r[] = root.list();

            if (liste_r.length==0){
                for (int i = Constantes.SEMESTRE_PAR_DEFAUT_MIN ; i <= Constantes.SEMESTRE_PAR_DEFAUT_MAX ; i++)
                    new File(path+"/"+Constantes.SEMESTRE_NAME+i).mkdirs();
            }

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
