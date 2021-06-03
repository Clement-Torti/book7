package sample.gui.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sample.Main;
import sample.model.Constantes;
import sample.model.Module;
import sample.model.Persistence.ModuleReader;
import sample.model.Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// ------------------------
// Rôle: Classe controllant la vue de selection du module à éditer
// Dernière Modification: Clément Torti
//
public class ArborescenceController extends BaseController {
    // Constantes
    public static Double MODULE_WIDTH = 900.0;
    public static Double MODULE_HEIGHT = 600.0;
    public static final String MODULE_FXML = "gui/view/vueModule.fxml";

    // FXML Outlets
    @FXML private VBox mainVbox;
    @FXML private VBox contentVbox;
    @FXML private Menu menuFichier;
    @FXML private MenuItem menuFichierQuitter;
    @FXML private MenuItem menuFichierNouveau;

    // Attributs
    private HashMap<Integer, List<Module>> modules;

    // Constructeurs
    public ArborescenceController(Stage stage) {
        super(stage);
        // Definir la taille de la fenetre d'un module comme la hauteur maximal de l'ecran
        ObservableList<Screen> screenSizes = Screen.getScreens();
        screenSizes.forEach(screen -> {
            MODULE_HEIGHT = screen.getBounds().getHeight();
            MODULE_WIDTH = MODULE_HEIGHT * 1.4;
        });
    }

    // Methodes
    @FXML
    void initialize() {
        // Barre de menu
        menuFichier.getStyleClass().add("menu");
        // ItemMenu pour quitter le programme -> class CSS + action
        menuFichierQuitter.getStyleClass().add("item-menu");
        menuFichierQuitter.setAccelerator(KeyCombination.keyCombination("Ctrl+W"));
        menuFichierQuitter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        menuFichierNouveau.setOnAction((event) -> {
            CreationModuleController contr = new CreationModuleController(getStage(), this);

            try {
                openStage("gui/view/vueCreationModule.fxml", contr, 400.0, 140.0, "Creation module", false);
            } catch (IOException e) {
                System.out.println(e);
            }
        });

       updateView();

    }

    public static HashMap<Integer, List<Module>> lireModules() {
        HashMap<Integer, List<Module>> out = new HashMap<Integer, List<Module>>();
        ModuleReader mr = new ModuleReader();
        try {
            //Initialisation des variables qui seront réutilisées
            File root, repertoire;

            //Expression régulières
            Pattern p_semestre = Pattern.compile(Constantes.SEMESTRE_NAME+"([0-9]+)$");
            Pattern p_fichier = Pattern.compile(".+\\."+Constantes.EXTENSION);

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
                            liste.add(extraireModule(mr,liste_r[i]+"/"+liste_fichiers[j]));
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

    public static Module extraireModule(ModuleReader mr, String chemin){ //On va propager les exceptions vers getModuleMap ou consors
        return mr.lire(chemin);
    }

    public void updateView() {
        // Vider les anciennes données
        contentVbox.getChildren().clear();

        // Lire les modules
        modules = lireModules();

        // Affichage dynamique des modules
        // Parcourir l'ensemble des dossiers "semestre"
        for (Integer key : modules.keySet()) {
            // Creation de la TitledPane
            TitledPane semestreTitledPane = new TitledPane();
            semestreTitledPane.getStyleClass().add("titled-pane");
            semestreTitledPane.setText("Semestre " + key);
            semestreTitledPane.setExpanded(false);

            // Les modules sont ajoutés dans une VBox
            VBox modulesVBox = new VBox();
            modulesVBox.setSpacing(10);
            modulesVBox.getStyleClass().add("vbox");

            // Parcourir les modules d'un semestre
            for (Module m : modules.get(key)) {
                HBox moduleHBox = new HBox();
                moduleHBox.setSpacing(30);
                moduleHBox.getStyleClass().add("hbox");

                // Bouton pour ouvrir le module
                Button moduleButton = new Button();
                moduleButton.getStyleClass().add("bouton");
                moduleButton.setText(m.getNom());

                // Bouton pour supprimer le module
                Button supprimerButton = new Button();
                supprimerButton.getStyleClass().add("boutonSupprimer");

                // Filtre Blanc pour icône
                ColorAdjust couleurBlanche = new ColorAdjust();
                couleurBlanche.setBrightness(1);

                // Ajout icône pour bouton supprimer
                ImageView iconSupprimer = new ImageView("/icon-trash-2.png");
                iconSupprimer.setPreserveRatio(true);
                iconSupprimer.setFitWidth(20);
                iconSupprimer.setEffect(couleurBlanche);
                supprimerButton.setGraphic(iconSupprimer);

                // Au clique, ouvrir la fenêtre du cahier
                moduleButton.setOnAction((event) -> {
                    ModuleController moduleController = new ModuleController(getStage(), m);
                    try {
                        openStage(MODULE_FXML, moduleController, MODULE_WIDTH, MODULE_HEIGHT, m.getNom(), true);
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                });

                // Au clique, ouvrir une fenêtre de confirmation
                supprimerButton.setOnAction((event) -> {
                    // Creer une pop-up Alert
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Supprimer module");
                    alert.setHeaderText("Voulez-vous supprimer le module : " + m.getNom() + " ?");
                    alert.setContentText("T'es sûr frérot ?");

                    // Bouton pour supprimer le module
                    Optional<ButtonType> result = alert.showAndWait();

                    // Actions des boutons
                    if (result.get() == ButtonType.OK) {
                        Utils.supprimerModule(m);
                        modulesVBox.getChildren().remove(moduleHBox);
                    } else {
                        System.out.println("Suppression annulée.");
                    }
                });

                // Ajout des boutons dans la HBox
                moduleHBox.getChildren().add(moduleButton);
                moduleHBox.getChildren().add(supprimerButton);
                // Ajout de la HBox dans la VBox du semestre
                modulesVBox.getChildren().add(moduleHBox);
            }

            // Ajout de la VBox dans le TitledPane
            semestreTitledPane.setContent(modulesVBox);

            // Ajouter les elements aux enfants
            contentVbox.getChildren().add(semestreTitledPane);
        }
    }

}