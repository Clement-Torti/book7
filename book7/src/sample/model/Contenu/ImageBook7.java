package sample.model.Contenu;

import java.io.Serializable;



/**
 * Un media représentant une image
 */
public class ImageBook7 extends Media implements Serializable {
    private static final long serialVersionUID = 6525207693293343048L;


    // Constructeurs
    public ImageBook7(String relativePath, String alt) {
        super(relativePath, alt);
    }

    public ImageBook7(String relativePath) {
        super(relativePath);
    }


}
