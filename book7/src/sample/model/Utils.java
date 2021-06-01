package sample.model;

import java.io.File;

public class Utils {
    public static void supprimerModule(Module module) {
        // Chemin absolu du dossier où se trouve le module
        String path=System.getProperty("user.dir")+"/"+Constantes.SAVE_ROOT_FOLDER_NAME+"/";
        // Sélectionner le fichier
        File file = new File(path + module.getChemin());
        // Supprimer le fichier
        if (file.delete()){
            System.out.println("Module " + module.getNom() + " supprimé.");
        } else{
            System.out.println("Erreur lors de la suppression du module " + module.getNom());
        };
    }
}