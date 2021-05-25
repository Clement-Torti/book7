package sample.model.Persistence;

import sample.model.Constantes;
import sample.model.Module;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

// ------------------------
// Rôle: Classe permettant d'enregistrer un module sur le disque
// Création: Clément Torti
// Dernière Modification: Clément Torti
//
public class ModuleWriter {
    // Attributs

    // Constructeurs

    // Méthodes

    // -----
    // rôle: Ecrire le module sur le disque
    // param:
    // - module: Module à enregistrer
    // retour: Succés de l'écriture du module

    public boolean ecrire(Module module) {
        try {
            String chemin = System.getProperty("user.dir")+"/"+Constantes.SAVE_ROOT_FOLDER_NAME+"/"+module.getChemin();
            System.out.println(chemin);
            FileOutputStream fos = new FileOutputStream(chemin);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(module);
            oos.close();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}