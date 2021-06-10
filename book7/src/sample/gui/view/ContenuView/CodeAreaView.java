package sample.gui.view.ContenuView;

import javafx.scene.Node;
import javafx.scene.text.Font;
import org.fxmisc.richtext.InlineCssTextArea;
import sample.model.Contenu.Contenu;
import sample.model.Observateur.Observable;

public class CodeAreaView extends TextAreaView {
    // Attributs


    // Constructeur
    public CodeAreaView(Contenu contenu) {
        super(contenu);
        textHolder.setFont(new Font("FreeMono", 11));
        textArea.getStyleClass().add("codeAreaView");
        setStyle(0, 0, "-fx-fill", "white");
        setStyle(0, 0, "-fx-font-family", "FreeMono");
        setStyle(0, 0, "-fx-font-size", "11px");
        setStyle(0, 0, "-fx-font-weight", "bold");
    }

    // Methodes

    @Override
    public void update(Observable obs, Object o) {
    }

    @Override
    protected double getMaxFontSize() {
        return 10;
    }
}
