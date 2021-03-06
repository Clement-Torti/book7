package sample.gui.view.ContenuView;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.fxmisc.richtext.InlineCssTextArea;
import org.fxmisc.richtext.model.StyleSpan;
import org.fxmisc.richtext.model.StyleSpans;
import sample.model.Contenu.CodeZone;
import sample.model.Contenu.Contenu;
import sample.model.Contenu.TextZone;
import sample.model.Observateur.Observable;
import sample.model.Toolbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// rôle: ContenuView indiquant à la vue comment afficher un TextArea
// Dernière modification: Clément Torti
//
public class TextAreaView extends ContenuView{
    // Attributs
    protected InlineCssTextArea textArea = new InlineCssTextArea();
    protected Text textHolder = new Text();
    private double oldHeight = 0;
    private Color selectedColor = Color.BLACK;
    private Boolean selectedGras = false;
    private Boolean selectedItalic = false;
    private Boolean selectedUnderline = false;
    private double maxFontSize = 14;

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

        } else {
            textHolder.setText("");
        }

        maxFontSize = getMaxFontSize();
        textHolder.setFont(new Font(maxFontSize));
        textArea.setPrefHeight(textHolder.getLayoutBounds().getHeight() + 20);
        textHolder.textProperty().bind(textArea.textProperty());

        // Event quand la taille de la textArea doit être MAJ
        textHolder.layoutBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {

                if (oldHeight != newValue.getHeight()) {
                    oldHeight = newValue.getHeight();
                    textArea.setPrefHeight(textHolder.getLayoutBounds().getHeight() + 20);
                }
            }
        });

        // Event quand le contenu du cahier change
        textArea.textProperty().addListener((event) -> {
            if(((TextZone)contenu).getTexte().equals(textArea.getText())) {
                return;
            }

            ((TextZone)contenu).setTexte(textArea.getText());
            ((TextZone)contenu).setStyleSpans(textArea.getStyleSpans(0, textArea.getLength()));

            if(this instanceof CodeAreaView) {
                return;
            }

            // Mettre à jour les styles en fonction de la toolBox
            String value = "#" + selectedColor.toString().substring(2, 8);
            setStyle(textArea.getText().length()-1, textArea.getText().length(), "-fx-fill", value);

            if(selectedGras) {
                setStyle(textArea.getText().length()-1, textArea.getText().length(), "-fx-font-weight", "bold");
            } else {
                setStyle(textArea.getText().length()-1, textArea.getText().length(), "-fx-font-weight", "normal");
            }

            if(selectedItalic) {
                setStyle(textArea.getText().length()-1, textArea.getText().length(), "-fx-font-style", "italic");
            } else {
                setStyle(textArea.getText().length()-1, textArea.getText().length(), "-fx-font-style", "normal");
            }

            if(selectedUnderline) {
                setStyle(textArea.getText().length()-1, textArea.getText().length(), "-fx-underline", "true");
            } else {
                setStyle(textArea.getText().length()-1, textArea.getText().length(), "-fx-underline", "false");
            }

            sauvegarder();
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

        // Est-ce le contenuView concerné ?
        if(start != stop) {
            sauvegarder();
        }

        String key = "";
        String value = "";
        switch (update) {
            case COULEUR:
                // Connaitre la couleur selectionnee
                selectedColor = toolBox.getColor();
                value = "#" + selectedColor.toString().substring(2, 8);

                // Si du texte est selectionné
                if(start != stop) {
                    setStyle(start, stop, "-fx-fill", value);
                }

                break;
            case GRAS:
                // Connaitre la valeur du gras
                selectedGras = toolBox.getGras();
                value = "normal";

                if(toolBox.getGras()) {
                    value = "bold";
                }

                if(start != stop) {
                    setStyle(start, stop, "-fx-font-weight", value);
                }
                break;

            case ITALIC:
                selectedItalic = toolBox.getItalique();
                value = "normal";

                if(toolBox.getItalique()) {
                    value = "italic";
                }

                if(start != stop) {
                    setStyle(start, stop, "-fx-font-style", value);
                }

                break;

            case SOULIGNER:
                selectedUnderline = toolBox.getSoulignement();
                value = "false";

                if(toolBox.getSoulignement()) {
                    value = "true";
                }

                if(start != stop) {
                    setStyle(start, stop, "-fx-underline", value);
                }

                break;
            case POLICE:
                value = toolBox.getPoliceTexte();

                if(start != stop) {
                    setStyle(start, stop, "-fx-font-family", "'" + value + "'");
                }
                break;
            case TAILLE_POLICE:
                if(maxFontSize < toolBox.getTaillePolice()) {
                    maxFontSize = toolBox.getTaillePolice();
                    textHolder.setFont(new Font("'" + toolBox.getPoliceTexte() + "'", maxFontSize));
                }
                value = toolBox.getTaillePolice().toString() + "px";

                if(start != stop) {
                    setStyle(start, stop, "-fx-font-size", value);
                }
                break;
            case MOTIF:
                break;
            case ALIGNEMENT:
                break;
            default:
        }
    }


    protected void setStyle(int start, int stop, String key, String value) {
        StyleSpans styleSpans = textArea.getStyleSpans(start, stop);

        int nbSpan = styleSpans.getSpanCount();

        int currentBegin = start;
        int currentEnd = start;


        for(int i=0; i<nbSpan; i++) {
            StyleSpan styleSpan = styleSpans.getStyleSpan(i);
            currentEnd += styleSpan.getLength();

            List<String> styles = Arrays.asList(((String) styleSpan.getStyle()).split(";"));
            List<String> newStyles = new ArrayList<>();

            // Conservation des anciennes propriété css
            for(String style: styles) {
                if(!style.contains(key) && style.replaceAll("\\s+","") != "") {
                    newStyles.add(style + ";");
                }
            }
            // Ajout de la nouvelle
            newStyles.add(key + ": " + value + ";");


            StringBuilder sb = new StringBuilder();
            for (String s : newStyles)
            {
                sb.append(s);
            }

            textArea.setStyle(currentBegin, currentEnd, sb.toString());

            currentBegin = currentEnd;
        }
    }


    protected double getMaxFontSize() {
        double maxFontSize = 0;

        StyleSpans stylesSpans = textArea.getStyleSpans(0, textArea.getLength());

        int nbSpans = stylesSpans.getSpanCount();

        for(int i=0; i<nbSpans; i++) {
            StyleSpan styleSpan = stylesSpans.getStyleSpan(i);

            List<String> styles = Arrays.asList(((String) styleSpan.getStyle()).split(";"));

            // Conservation des anciennes propriété css
            for(String style: styles) {
                if(style.contains("-fx-font-size:")) {
                    String fontSize =  style.replaceAll("[^0-9]", "");
                    Integer size = Integer.parseInt(fontSize);

                    if(size > maxFontSize) {
                        maxFontSize = size;
                    }
                }
            }
        }

        if(maxFontSize == 0) {
            maxFontSize = 14;
        }

        return maxFontSize;
    }

}
