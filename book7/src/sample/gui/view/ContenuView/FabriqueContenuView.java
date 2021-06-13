package sample.gui.view.ContenuView;

import sample.model.Contenu.*;


/**
 * Fourni un contenuView adapté à un contenu
 */
public class FabriqueContenuView {
    /**
     * Fourni un ContenuView adapté à un contenu
     * @param contenu contenu
     * @return la ContenuView adaptée
     */
    public static ContenuView fabriquerContenuView(Contenu contenu) {
        if(contenu instanceof CodeZone) {
            return new CodeAreaView(contenu);
        } else if (contenu instanceof TextZone) {
            return new TextAreaView(contenu);
        } else if(contenu instanceof Forme) {
            return new FormeView(contenu);
        } else if(contenu instanceof Formule) {
            return new FormuleView(contenu);
        } else if(contenu instanceof ImageBook7) {
            return new ImageContenuView(contenu);
        } else {
            throw new RuntimeException("Contenu inconnu");
        }
    }
}
