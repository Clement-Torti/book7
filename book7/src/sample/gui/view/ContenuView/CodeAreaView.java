package sample.gui.view.ContenuView;

import javafx.scene.Node;
import org.fxmisc.richtext.InlineCssTextArea;
import sample.model.Contenu.Contenu;
import sample.model.Observateur.Observable;

public class CodeAreaView extends TextAreaView {
    // Attributs


    // Constructeur
    public CodeAreaView(Contenu contenu) {
        super(contenu);
        textArea.getStyleClass().add("codeAreaView");
        setStyle(0, 0, "-fx-stroke", "white");
        setStyle(0, 0, "-fx-font-family", "FreeMono");
    }

    // Methodes

    @Override
    public void update(Observable obs, Object o) {
    }

}
