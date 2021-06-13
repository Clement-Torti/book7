package sample.model;

import java.io.*;

/**
 * Ensemble de fonctions utiles dans le programme
 */
public class Utils {
    /**
     * Suppression d'un module
     * @param module module à supprimer
     */
    public static void supprimerModule(Module module) {
        // Chemin absolu du dossier où se trouve le module
        String path= Utils.getRacineProjet() + "/" + Constantes.SAVE_ROOT_FOLDER_NAME + "/";
        // Sélectionner le fichier
        File file = new File(path + module.getChemin());
        // Supprimer le fichier
        if (file.delete()){
            System.out.println("Module " + module.getNom() + " supprimé.");
        } else{
            System.out.println("Erreur lors de la suppression du module " + module.getNom());
        };
    }


    /**
     * Connaitre le chemin vers la racine du projet
     * @return Le chemin vers la racine du projet
     */
    public static String getRacineProjet() {
        return System.getProperty("user.dir");
    }
}