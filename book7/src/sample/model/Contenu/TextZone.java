package sample.model.Contenu;

import org.fxmisc.richtext.model.StyleSpan;
import org.fxmisc.richtext.model.StyleSpans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Un contenu représentant un paragraphe de texte
 */
public class TextZone extends Contenu implements Serializable {
    private static final long serialVersionUID = 1043738449910295226L;

    // Attributs
    private String texte = "";
    private List<Balise> balises = new ArrayList<>();


    // Getters
    public String getTexte() {
        return texte;
    }

    // Setters
    public void setTexte(String texte) {
        this.texte = texte;
    }

    public List<StyleSpan> getStyleSpans() {
        List<StyleSpan> styleSpans = new ArrayList<>();

        for(Balise b: balises) {
            StyleSpan styleSpan = new StyleSpan(b.getStyle(), b.getLength());
            styleSpans.add(styleSpan);
        }

        return styleSpans;
    }
    public void setStyleSpans(StyleSpans styleSpans) {
        balises.clear();

        // Conversion d'un styleSpans en quelques chose de serialisable
        int nbStyles = styleSpans.getSpanCount();

        for(int i=0; i<nbStyles; i++) {
            StyleSpan styleSpan = styleSpans.getStyleSpan(i);

            int length = styleSpan.getLength();
            String style = (String) styleSpan.getStyle();

            Balise b = new Balise(length, style);
            balises.add(balises.size(), b);
        }
    }



}
