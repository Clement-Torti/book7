package sample.model.Contenu;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import sample.model.Constantes;
import sample.model.Utils;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.image.*;

// ------------------------
// Rôle: Un media représentant une PDF
// Création: Clément Torti
// Dernière Modification: Clément Torti
//
public class PDF extends Media implements Serializable {
    private static final long serialVersionUID = -2049344260833714945L;

    // Attributs
    private int nbPages = 0;
    private String nomFichier;
    transient PDDocument doc;

    // Constructeurs
    public PDF(String relativePath, String alt) throws IOException {
        super(relativePath, alt);

        this.doc = PDDocument.load(new File(Utils.getRacineProjet() + "/" + relativePath));

        this.nbPages = doc.getNumberOfPages();

        Pattern pattern = Pattern.compile(".*/([^/]+)\\.pdf$");

        Matcher matcher = pattern.matcher(relativePath);
        matcher.find();

        this.nomFichier = matcher.group(1);
    }

    public PDF(String relativePath) throws IOException {
        this(relativePath, "");
    }

    // Getters
    public int getNbPages() {
        return nbPages;
    }

    public String getNomFichier() {
        return nomFichier;
    }


    // Methodes
    public ImageBook7 getImageBook7(int i) throws IOException {
        i--;
        // Recupere la page souhaitée
        PDPage page = doc.getPages().get(i);
        String relativePath = this.getRelativePath(i, ".png");
        // On créer une image à partir de la page
        PDFRenderer renderer=new PDFRenderer(this.doc);
        BufferedImage image = renderer.renderImage(i);
        // On creer un fichier qui contient l'image
        ImageIO.write(image,"PNG", new File(Utils.getRacineProjet() + "/" + relativePath));

        // Récupérer le texte de la page
        PDFTextStripper reader = new PDFTextStripper();
        reader.setStartPage(i);
        reader.setEndPage(i);
        String pageText = reader.getText(doc);

        return new ImageBook7(relativePath, pageText);
    }

    private String getRelativePath(int page, String extension){
        SimpleDateFormat formatter = new SimpleDateFormat("dd_HH_mm_ss");
        Date date = new Date();
        String dateStr = formatter.format(date);

        return (Constantes.SLIDES_ROOT_FOLDER_NAME+ "/" + this.nomFichier + "_" + dateStr + "_" + page + extension);
    }
}
