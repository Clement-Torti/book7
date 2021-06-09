package sample.model.Persistence;

import sample.model.Constantes;
import sample.model.Module;
import sample.model.Utils;

import java.io.File;
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
            String root= Utils.getRacineProjet() +"/"+Constantes.SAVE_ROOT_FOLDER_NAME+"/";
            String chemin_semestre = root + Constantes.SEMESTRE_NAME + Integer.toString(module.getSemestre());
            String chemin = root+module.getChemin();

            File dossier_semestre = new File(chemin_semestre);

            if(!dossier_semestre.exists())
                dossier_semestre.mkdirs();

            File test_fichier = new File(chemin);
            if(test_fichier.isFile())
                test_fichier.delete();
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