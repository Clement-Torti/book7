package sample.model.Contenu;

import java.io.Serializable;


/**
 * Un contenu représentant une forme
 */
public class Forme extends Contenu implements Serializable {
    private static final long serialVersionUID = -1859992219930295378L;


    // Attributs
    private Integer deltaX;
    private Integer deltaY;
    private Integer hauteur;
    private Integer largeur;

    private Contenu ancrage;

}
