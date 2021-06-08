package sample.model;

import javafx.scene.paint.Color;
import sample.model.Enums.FontStyle;
import sample.model.Observateur.Observable;

public class Toolbox extends Observable {
    public enum ToolboxUpdate {
        COULEUR,
        GRAS,
        ITALIC,
        SOULIGNER,
        POLICE,
        TAILLE_POLICE,
        MOTIF,
        ALIGNEMENT
    }

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
        notifier(ToolboxUpdate.COULEUR);
    }

    public FontStyle getPoliceTexte() {
        return policeTexte;
    }

    public void setPoliceTexte(FontStyle policeTexte) {
        this.policeTexte = policeTexte;
        notifier(ToolboxUpdate.POLICE);
    }

    public Integer getTaillePolice() {
        return taillePolice;
    }

    public void setTaillePolice(Integer taillePolice) {
        this.taillePolice = taillePolice;
        notifier(ToolboxUpdate.TAILLE_POLICE);
    }

    public Boolean getGras() {
        return gras;
    }

    public void setGras(Boolean gras) {
        this.gras = gras;
        notifier(ToolboxUpdate.GRAS);
    }

    public Boolean getItalique() {
        return italique;
    }

    public void setItalique(Boolean italique) {
        this.italique = italique;
        notifier(ToolboxUpdate.ITALIC);
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
        notifier(ToolboxUpdate.SOULIGNER);
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
