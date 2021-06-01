package sample.gui.view.ContenuView;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import sample.model.Contenu.Contenu;
import sample.model.Contenu.TextZone;
import sample.model.Observateur.IObservateur;

// rôle: ContenuView indiquant à la vue comment afficher un TextArea
// Dernière modification: Clément Torti
//
public class TextAreaView extends ContenuView {
    // Attributs
    private TextArea textArea = new TextArea();
    private Text textHolder = new Text();
    private double oldHeight = 0;

    public TextAreaView(Contenu contenu) {
        super(contenu);
    }

    @Override
    public Node afficher() {
        textArea.setText(((TextZone) contenu).getTexte());
        textHolder.setText(textArea.getText());
        return resizableTextArea();
    }

    private TextArea resizableTextArea(){
        // Taille du textAreapar defaut
        textArea.setPrefHeight(textHolder.getLayoutBounds().getHeight() + 20);
        textArea.setPadding(new Insets(0));

        textArea.setWrapText(true);
        textHolder.textProperty().bind(textArea.textProperty());
        textHolder.layoutBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
                ((TextZone)contenu).setTexte(textArea.getText());
                System.out.println(oldValue.getHeight());
                if (oldHeight != newValue.getHeight()) {
                    oldHeight = newValue.getHeight();
                    textArea.setPrefHeight(textHolder.getLayoutBounds().getHeight() + 20);
                }
            }
        });

        return textArea;
    }
}
