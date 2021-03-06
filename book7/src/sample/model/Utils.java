package sample.model;

import java.io.*;

public class Utils {
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

    public static void copyDirectory(File sourceLocation , File targetLocation) throws IOException {
        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }

            String[] children = sourceLocation.list();
            for (int i=0; i<children.length; i++) {
                copyDirectory(new File(sourceLocation, children[i]),
                        new File(targetLocation, children[i]));
            }
        } else {

            InputStream in = new FileInputStream(sourceLocation);
            OutputStream out = new FileOutputStream(targetLocation);

            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }
    }

    public static String getRacineProjet() {
        return System.getProperty("user.dir");
    }
}