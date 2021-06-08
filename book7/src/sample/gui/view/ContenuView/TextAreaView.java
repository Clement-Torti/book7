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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.fxmisc.richtext.InlineCssTextArea;
import org.fxmisc.richtext.model.StyleSpan;
import sample.gui.controller.ModuleController;
import sample.model.Contenu.Contenu;
import sample.model.Contenu.TextZone;
import sample.model.Observateur.IObservateur;
import sample.model.Observateur.Observable;
import sample.model.Toolbox;

import java.time.LocalDateTime;

// rôle: ContenuView indiquant à la vue comment afficher un TextArea
// Dernière modification: Clément Torti
//
public class TextAreaView extends ContenuView{
    // Attributs
    private InlineCssTextArea textArea = new InlineCssTextArea();
    private Text textHolder = new Text();
    private double oldHeight = 0;

    public TextAreaView(Contenu contenu) {
        super(contenu);
    }

    @Override
    public Node afficher() {
        return resizableTextArea();
    }

    private InlineCssTextArea resizableTextArea(){
        textArea.getStyleClass().add("textAreaView");
        textArea.setWrapText(true);

        if(((TextZone) contenu).getTexte() != null) {
            textArea.appendText(((TextZone) contenu).getTexte());
        }

        if(textArea.getText() != null && ((TextZone) contenu).getStyleSpans() != null) {
            textHolder.setText(textArea.getText());

            int current = 0;
            for(StyleSpan s: ((TextZone) contenu).getStyleSpans()) {
                textArea.getStyleSpans(0, textArea.getLength()).append(s);
                textArea.setStyle(current, current + s.getLength(), (String) s.getStyle());

                current += s.getLength();
            }

            System.out.println(textArea.getStyleSpans(0, textArea.getLength()));

        } else {
            textHolder.setText("");
        }

        textArea.setPrefHeight(textHolder.getLayoutBounds().getHeight() + 20);

        textHolder.textProperty().bind(textArea.textProperty());
        textHolder.layoutBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
                // Ajouter une operation

                // ajoutOperation(contenu, LocalDateTime.now());
                // MAJ du modele
                ((TextZone)contenu).setTexte(textArea.getText());
                ((TextZone)contenu).setStyleSpans(textArea.getStyleSpans(0, textArea.getLength()));

                sauvegarder();

                if (oldHeight != newValue.getHeight()) {
                    oldHeight = newValue.getHeight();
                    textArea.setMinHeight(textHolder.getLayoutBounds().getHeight() + 20);
                }
            }
        });

        return textArea;
    }


    @Override
    public void update(Observable obs, Object o) {
        Toolbox toolBox = (Toolbox) obs;
        Toolbox.ToolboxUpdate update = (Toolbox.ToolboxUpdate) o;

        // La textAreaView est-elle concernée ?
        int start = textArea.getSelection().getStart();
        int stop = textArea.getSelection().getEnd();

        switch (update) {
            case COULEUR:
                // Connaitre la couleur selectionnee
                Color c = toolBox.getColor();
                String css = "-fx-stroke: #" + c.toString().substring(2, 8) + ";";

                textArea.setStyle(textArea.getText().length(), textArea.getText().length(), css);

                // Si du texte est selectionné
                if(start != stop) {
                    textArea.setStyle(start, stop, css);
                }

                break;
            case GRAS:
                break;
            case ITALIC:
                break;
            case SOULIGNER:
                break;
            case POLICE:
                break;
            case TAILLE_POLICE:
                break;
            case MOTIF:
                break;
            case ALIGNEMENT:
                break;
            default:
                System.out.println("Unknown update");
        }
    }
}
