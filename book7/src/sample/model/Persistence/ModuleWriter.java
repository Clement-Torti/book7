package sample.model.Persistence;

import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import sample.gui.Utils.FileOpener;
import sample.gui.controller.ArborescenceController;
import sample.gui.controller.BaseController;
import sample.gui.view.PageView;
import sample.model.*;
import sample.model.Enums.Section;
import sample.model.Module;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Classe permettant d'enregistrer un module sur le disque
 */
public class ModuleWriter {
    // Attributs

    // Constructeurs

    // Méthodes

    // -----
    // rôle: Ecrire le module sur le disque
    // param:
    // - module: Module à enregistrer
    // retour: Succés de l'écriture du module

    public boolean ecrire(Module module) {
        try {
            String root= Utils.getRacineProjet() +"/"+Constantes.SAVE_ROOT_FOLDER_NAME+"/";
            String chemin_semestre = root + Constantes.SEMESTRE_NAME + Integer.toString(module.getSemestre());
            String chemin = root+module.getChemin();

            File dossier_semestre = new File(chemin_semestre);

            if(!dossier_semestre.exists())
                dossier_semestre.mkdirs();

            File test_fichier = new File(chemin);
            if(test_fichier.isFile())
                test_fichier.delete();
            FileOutputStream fos = new FileOutputStream(chemin);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(module);
            oos.close();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public boolean toPdf(Module module) {
        // Variables bidon pour la pageView
        FileOpener fo = new FileOpener(new Stage());
        BaseController bc = new ArborescenceController(new Stage());
        Toolbox tb = new Toolbox();

        // Creer un doc PDF
        PDDocument pdfDoc = new PDDocument();


        // Parcourir toutes les pages du cahier
        int index = 0;
        for(Page page: module.getCours().getPages()) {
            index += 1;
            PageView pageView = new PageView(fo, bc, 500, 700);

            // Do the snapshot
            WritableImage pageShot =  pageView.snapshot(null, null);//pageView.snapshot(params, null);
            File file = new File("pageShot" + index + ".png");

            try {
                ImageIO.write(SwingFXUtils.fromFXImage(pageShot, null), "png", file);
            } catch (IOException e) {
                return false;
            }


            // Insert the snpashot into the page and then the PDF
            PDPage pdfPage = new PDPage();
            PDImageXObject pdimage;
            PDPageContentStream content;
            try {
                pdimage = PDImageXObject.createFromFile("pageShot" + index + ".png", pdfDoc);
                content = new PDPageContentStream(pdfDoc, pdfPage);
                content.drawImage(pdimage, 0, 0);
                content.close();
                pdfDoc.addPage(pdfPage);

                //file.delete();
                break;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

        }

        try {
            pdfDoc.save("pdf_file.pdf");
            pdfDoc.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}