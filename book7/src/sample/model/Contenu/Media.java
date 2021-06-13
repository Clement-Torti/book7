package sample.model.Contenu;

import java.io.Serializable;
import java.net.URI;
import java.net.URL;


/**
 * Un contenu représentant un media, c'est-à-dire un contenu extèrieur au cahier
 */
public abstract class Media extends Contenu implements Serializable {
    private static final long serialVersionUID = -6573313072129458148L;


    // Attributs
    private String relativePath;


    // Getter
    public String getRelativePath() {
        return relativePath;
    }


    /**
     *
     * @param relativePath chemin du media importé
     * @param alt Représentation textuelle du media
     */
    public Media(String relativePath, String alt) {
        super();
        this.relativePath = relativePath;
        this.metadonnees.put("alt", alt);
    }


    public Media(String relativePath) {
        this(relativePath, "");
    }
}
