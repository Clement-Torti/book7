package sample.model.Contenu;

import sample.model.Enums.Alignement;
import sample.model.Enums.Disposition;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Modèle représentant un contenu d'un cahier
 */
public abstract class Contenu implements Serializable {
    private static final long serialVersionUID = 6149141948119544784L;


    // Attributs
    private String id;
    protected Map<String, String> metadonnees = new HashMap<String, String>();
    private Alignement alignement; // Non utilisé pour le moment
    private Disposition disposition; // Non utilisé pour le moment

}
