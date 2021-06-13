package sample.model.Contenu;

import java.io.Serializable;


/**
 * Modèle contenant des infos de style dans une TextZone
 * Encapsule un StyleSpan pour le rendre serializable
 */
public class Balise implements Serializable {
    private static final long serialVersionUID = 2598804699603249099L;


    // Attributs
    private int length; // nombre de caractère concernés par ce style
    private String style; // Css du style à appliquer


    // Constructeurs
    public Balise(int length, String style) {
        this.length = length;
        this.style = style;
    }


    // Getters
    public String getStyle() {
        return style;
    }

    public int getLength() {
        return length;
    }


    @Override
    public String toString() {
        return "Balise{" +
                "length=" + length +
                ", style='" + style + '\'' +
                '}';
    }
}
