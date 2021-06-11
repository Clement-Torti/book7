package sample.model.Contenu;

import java.io.Serializable;

// ------------------------
// Rôle: Encapsule un StyleSpan pour le rendre serializable
// Création: Clément Torti
// Dernière Modification: Clément Torti
//
public class Balise implements Serializable {
    private static final long serialVersionUID = 2598804699603249099L;

    // Attributs
    private int length;
    private String style;

    // Constructeurs
    public Balise(int length, String style) {
        this.length = length;
        this.style = style;
    }

    //
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
