package sample.model;

import java.io.File;

public class Utils {
    public static void supprimerModule(Module module) {
        //Chemin absolu du dossier où sont les fichiers
        String path=System.getProperty("user.dir")+"/"+Constantes.SAVE_ROOT_FOLDER_NAME+"/";
        //Supprimer fichier
        File file = new File(path + module.getChemin());
        System.out.println(file.toString());
        if (file.delete()){
            System.out.println("Module " + module.getNom() + " supprimé.");
        } else{
            System.out.println("Erreur lors de la suppression du module " + module.getNom());
        };
    }
}
