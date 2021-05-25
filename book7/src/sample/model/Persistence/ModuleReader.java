package sample.model.Persistence;

import sample.model.Constantes;
import sample.model.Module;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

// ------------------------
// Rôle: Classe permettant de lire un module depuis le disque
// Création: Clément Torti
// Dernière Modification: Clément Torti
//
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
            String chemin_absolu = System.getProperty("user.dir")+"/"
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