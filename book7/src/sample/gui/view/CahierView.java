package sample.gui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import sample.gui.Utils.FileOpener;
import sample.model.Cahier;
import sample.model.Toolbox;
import sample.model.Page;

// ------------------------
// Rôle: Classe controllant un cahier
// Création: Clément Torti
// Dernière Modification: Clément Torti
//
public class CahierView extends HBox {
    // Attributs
    private Cahier cahier;
    private String nomModule;
    private Toolbox toolbox;
    private Integer pageIndex = 0;

    // Outlets
    private PageView leftPage;
    private PageView rightPage;


    // Constructeur
    public CahierView(FileOpener fileOpener) {
        // Ajout des 2 pages
        leftPage = new PageView(fileOpener);
        rightPage = new PageView(fileOpener);

        setMargin(leftPage, new Insets(10));
        setMargin(rightPage, new Insets(10));

        HBox.setHgrow(leftPage, Priority.ALWAYS);
        HBox.setHgrow(rightPage, Priority.ALWAYS);

        getChildren().add(leftPage);
        getChildren().add(rightPage);

    }

    // Methodes
    public void setCahier(Cahier _cahier, String _nomModule, Toolbox _toolbox) {
//    public void setCahier(Cahier _cahier, String _nomModule) {
        cahier = _cahier;
        nomModule = _nomModule;
        toolbox = _toolbox;
        setPage(pageIndex); // Afficher la 1ere page par défaut
    }

    public void nextPage() {
        setPage(pageIndex + 2);
    }

    public void previousPage() {
        setPage(pageIndex - 2);
    }

    private void setPage(Integer _pageIndex) {
        if(_pageIndex < 0) {
            return;
        } else if (_pageIndex >= cahier.getPages().size()) {
            cahier.addPage(new Page());
            cahier.addPage(new Page());
        }
        this.pageIndex = _pageIndex;

        // La page a afficher est paire
        if (pageIndex % 2 == 0) {
            leftPage.setPage(cahier.getPages().get(pageIndex), pageIndex, nomModule, cahier.getSection(), toolbox);

            if(cahier.getPages().size() > pageIndex) {
                rightPage.setPage(cahier.getPages().get(pageIndex + 1), pageIndex + 1, nomModule, cahier.getSection(), toolbox);
            }
        } else {
            // La page à afficher est impaire
            leftPage.setPage(cahier.getPages().get(pageIndex-1), pageIndex - 1, nomModule, cahier.getSection(), toolbox);
            rightPage.setPage(cahier.getPages().get(pageIndex), pageIndex, nomModule, cahier.getSection(), toolbox);
        }
    }
}
