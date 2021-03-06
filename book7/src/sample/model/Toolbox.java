package sample.model;

import javafx.scene.paint.Color;
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
    Integer taillePolice;
    Boolean gras;
    Boolean italique;
    Boolean surlignement;
    Boolean soulignement;
    String policeTexte;
    String motif;

    public Toolbox() {
        this.color = Color.BLACK;
        this.policeTexte = "Arial";
        this.taillePolice = 12;
        this.gras = false;
        this.italique = false;
        this.surlignement = false;
        this.soulignement = false;
        this.motif = "GrandCarreaux";
    }

    public Toolbox(Color color, String policeTexte, Integer taillePolice, Boolean gras, Boolean italique, Boolean surlignement, Boolean soulignement) {
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

    public String getPoliceTexte() {
        return policeTexte;
    }

    public void setPoliceTexte(String policeTexte) {
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

    public void flipGras() {
        this.gras = !gras;
        notifier(ToolboxUpdate.GRAS);
    }

    public Boolean getItalique() {
        return italique;
    }

    public void flipItalique() {
        this.italique = !italique;
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

    public void flipSoulignement() {
        this.soulignement = !soulignement;
        notifier(ToolboxUpdate.SOULIGNER);
    }

    public String getMotif(){
        return motif;
    }

    public void setMotif(String motif){
        this.motif = motif;
        notifier(ToolboxUpdate.MOTIF);
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
