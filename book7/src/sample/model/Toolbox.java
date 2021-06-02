package sample.model;

import javafx.scene.paint.Color;
import sample.model.Enums.FontStyle;

public class Toolbox {
    Color color;
    FontStyle policeTexte;
    Integer taillePolice;
    Boolean gras;
    Boolean italique;
    Boolean surlignement;
    Boolean soulignement;

    public Toolbox() {
        this.color = Color.BLACK;
        this.policeTexte = FontStyle.TimesNewRoman;
        this.taillePolice = 12;
        this.gras = false;
        this.italique = false;
        this.surlignement = false;
        this.soulignement = false;
    }

    public Toolbox(Color color, FontStyle policeTexte, Integer taillePolice, Boolean gras, Boolean italique, Boolean surlignement, Boolean soulignement) {
        this.color = color;
        this.policeTexte = policeTexte;
        this.taillePolice = taillePolice;
        this.gras = gras;
        this.italique = italique;
        this.surlignement = surlignement;
        this.soulignement = soulignement;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public FontStyle getPoliceTexte() {
        return policeTexte;
    }

    public void setPoliceTexte(FontStyle policeTexte) {
        this.policeTexte = policeTexte;
    }

    public Integer getTaillePolice() {
        return taillePolice;
    }

    public void setTaillePolice(Integer taillePolice) {
        this.taillePolice = taillePolice;
    }

    public Boolean getGras() {
        return gras;
    }

    public void setGras(Boolean gras) {
        this.gras = gras;
    }

    public Boolean getItalique() {
        return italique;
    }

    public void setItalique(Boolean italique) {
        this.italique = italique;
    }

    public Boolean getSurlignement() {
        return surlignement;
    }

    public void setSurlignement(Boolean surlignement) {
        this.surlignement = surlignement;
    }

    public Boolean getSoulignement() {
        return soulignement;
    }

    public void setSoulignement(Boolean soulignement) {
        this.soulignement = soulignement;
    }

    @Override
    public String toString() {
        return "Toolbox{" +
                "color=" + color +
                ", policeTexte=" + policeTexte +
                ", taillePolice=" + taillePolice +
                ", gras=" + gras +
                ", italique=" + italique +
                ", surlignement=" + surlignement +
                ", soulignement=" + soulignement +
                '}';
    }
}
