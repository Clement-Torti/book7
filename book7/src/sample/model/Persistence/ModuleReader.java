package sample.model.Persistence;

import sample.model.Constantes;
import sample.model.Module;
import sample.model.Utils;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Classe permettant de lire un module depuis le disque
 */
public class ModuleReader {
    // Attributs

    // Constructeurs

    // Méthodes

    // -----
    // rôle: Extraire d'un fichier disque un module
    // param:
    // - chemin: Chemin du fichier contenant le module
    // retour: Le module extrait
    public Module lire(String chemin) {
        try {
            String chemin_absolu = Utils.getRacineProjet()  +"/"
                    +Constantes.SAVE_ROOT_FOLDER_NAME+"/"+chemin;
            FileInputStream fis = new FileInputStream(chemin_absolu);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (Module) ois.readObject();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}