package sample.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.model.Constantes;
import sample.model.Module;
import sample.model.Persistence.ModuleWriter;

import java.io.File;
import java.io.IOException;

public class CreationModuleController extends BaseController {
    public static Double MODULE_WIDTH = 300.0;
    public static Double MODULE_HEIGHT = 100.0;
    public static final String MODULE_FXML = "gui/view/vueModule.fxml";

    @FXML private VBox mainVBox;
    @FXML private HBox nomHBox;
    @FXML private HBox semestreHBox;
    @FXML private HBox creerHBox;
    @FXML private Label nom_label;
    @FXML private TextField nom_textfield;
    @FXML private Label semestre_label;
    @FXML private ChoiceBox semestre_choicebox;
    @FXML private Button creer_button;
    @FXML private Text message_erreur;

    public CreationModuleController(Stage stage){
        super(stage);
    }

    @FXML
    void initialize() {
        for (int i=1; i< Constantes.SEMESTRE_MAX; i++){
            semestre_choicebox.getItems().add("Semestre "+Integer.toString(i));
        }

        creer_button.setOnAction((event) -> creerModule());
        semestre_choicebox.setOnAction((event) -> {semestre_choicebox.getStyleClass().remove("error"); message_erreur.setText("");});
    }

    void creerModule(){
        String nom_module = nom_textfield.getText();
        int semestre = semestre_choicebox.getSelectionModel().getSelectedIndex()+1;
        if(nom_module.length()==0)
            nom_textfield.getStyleClass().add("error");

        if(semestre==0)
            semestre_choicebox.getStyleClass().add("error");


        if(nom_module.length()>0 && semestre > 0){
            if(moduleExiste(nom_module, semestre))
                message_erreur.setText("Un module du même nom et semestre existe, veuillez en choisir un autre");
        }

        ModuleWriter mw = new ModuleWriter();
        mw.ecrire(new Module(nom_module, semestre));
    }

    boolean moduleExiste(String nom, int semestre){
        String chemin=System.getProperty("user.dir")+"/"+Constantes.SAVE_ROOT_FOLDER_NAME+"/"+Module.calculerChemin(nom, semestre);
        File fichier=new File(chemin);
        if(fichier.exists() && fichier.isFile())
            return true;
        return false;
    }
}
