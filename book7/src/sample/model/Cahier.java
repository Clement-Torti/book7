package sample.model;

import sample.model.Enums.Motif;
import sample.model.Enums.Section;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

// ------------------------
// Rôle: Classe représentant un cahier enseeiht
// Création: Clément Torti
// Dernière Modification: Clément Torti
//
public class Cahier {
    // Attributs
    private Section section;
    private Motif motif;
    private List<Page> pages;
    private List<Operation> operations;

    // Getters
    public List<Page> getPages() {
        return pages;
    }

    // Constructeurs

    // Méthodes

    // -----
    // rôle: Rechercher dans le cahier du texte (Ctrl F)
    // param:
    // - texte: texte à rechercher
    // retour: Dictionnaire des id des élements contenant le texte associés à leur page
    public HashMap<Page, List<String>> rechercher(String texte) {
        return new HashMap<Page, List<String>>();
    }

    // -----
    // rôle: Annule la dernière opération effectuée (Ctrl Z)
    public void annulerOperation() {
        System.out.println("Non implemente");
    }
}
