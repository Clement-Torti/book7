package sample.gui.view.ContenuView;

import sample.gui.view.PageView;
import sample.model.Contenu.*;
import sample.model.Observateur.IObservateur;

// rôle: Fabrique fournissant la bonne sous classe de contenuView en fonction du contenu fourni
// Dernière modification: Clément Torti
//
public class FabriqueContenuView {
    public static ContenuView fabriquerContenuView(Contenu contenu) {
        if (contenu instanceof TextZone) {
            return new TextAreaView(contenu);
        } else if(contenu instanceof Forme) {
            return new FormeView(contenu);
        } else if(contenu instanceof Formule) {
            return new FormuleView(contenu);
        } else if(contenu instanceof Image) {
            return new ImageContenuView(contenu);
        } else if(contenu instanceof  PDF) {
            return new PDFView(contenu);
        } else {
            throw new RuntimeException("Contenu inconnu");
        }
    }
}
