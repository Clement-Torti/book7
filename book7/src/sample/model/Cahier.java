package sample.model;

import sample.model.Enums.Motif;
import sample.model.Enums.Section;

import java.io.Serializable;
import java.util.*;


/**
 * Classe représentant un cahier enseeiht
 */
public class Cahier implements Serializable {
    private static final long serialVersionUID = 3445207238004973501L;


    // Attributs
    private Section section;
    private Motif motif = Motif.GRANDS_CARREAUX;
    private List<Page> pages = new ArrayList<>();
    private List<Operation> operations = new ArrayList<>();


    // Getters
    public List<Page> getPages() {
        return pages;
    }
    public Section getSection() { return section; }


    // Constructeurs
    public Cahier(Section section) {
        this.section = section;

        // Un cahier a 2 page à sa création
        pages.add(new Page());
        pages.add(new Page());
    }

    // Méthodes

    /**
     * Ajout d'une page en fin de cahier
     * @param page page à ajouter
     */
    public void addPage(Page page) {
        pages.add(page);
    }


    /**
     * Rechercher dans le cahier du texte (Ctrl F)
     * @param texte texte à rechercher
     * @return Dictionnaire des id des élements contenant le texte associés à leur page
     */
    public HashMap<Page, List<String>> rechercher(String texte) {
        return new HashMap<Page, List<String>>();
    }


    /**
     * Annule la dernière opération effectuée (Ctrl Z)
     */
    public void annulerOperation() {
        System.out.println("Non implemente");
    }
}
