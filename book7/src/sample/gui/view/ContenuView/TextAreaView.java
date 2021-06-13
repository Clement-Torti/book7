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
import sample.model.Contenu.Contenu;
import sample.model.Contenu.TextZone;
import sample.model.Observateur.Observable;
import sample.model.Toolbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Implémentation de ContenuView chargée d'afficher une zone de texte éditable
 */
public class TextAreaView extends ContenuView{
    // Attributs
    protected InlineCssTextArea textArea = new InlineCssTextArea();
    protected Text textHolder = new Text(); // Permet de déterminer la hauteur de la textArea en fonction de son contenu
    private double oldHeight = 0; // Taille du la TextArea
    private double maxFontSize = 14; // Taille maximal de la police dans la textArea
    // Copie locale des valeurs de la toolbox
    private Color selectedColor = Color.BLACK;
    private Boolean selectedGras = false;
    private Boolean selectedItalic = false;
    private Boolean selectedUnderline = false;


    // Constructeur
    public TextAreaView(Contenu contenu) {
        super(contenu);
    }


    @Override
    public Node afficher() {
        return resizableTextArea();
    }


    /**
     * Construction du composant à afficher
     * @return La textArea
     */
    private InlineCssTextArea resizableTextArea(){
        // Définition du style
        textArea.getStyleClass().add("textAreaView");
        textArea.setWrapText(true);

        // Ajout du texte si le contenu n'est pas vide
        if(((TextZone) contenu).getTexte() != null) {
            textArea.appendText(((TextZone) contenu).getTexte());
        }

        // Mise à jour du style du texte si le contenu n'est pas vide
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

        // Taille daximum de la police
        maxFontSize = getMaxFontSize();
        textHolder.setFont(new Font(maxFontSize));
        // Taille de la textArea à l'initialisation
        textArea.setPrefHeight(textHolder.getLayoutBounds().getHeight() + 20);
        // Bindage de l'élément graphique (textArea) avec de la donnée (textHolder)
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

            // Mise à jour du modèle
            ((TextZone)contenu).setTexte(textArea.getText());
            ((TextZone)contenu).setStyleSpans(textArea.getStyleSpans(0, textArea.getLength()));

            // Ne pas faire de MAJ du style sur une CodeAreaView
            if(this instanceof CodeAreaView) {
                return;
            }

            // Mettre à jour les styles en fonction des données de la toolBox
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

    /**
     * Défini du style sur une partie de la textArea
     * @param start index du 1er caractère à styliser
     * @param stop index du dernier caractère à styliser
     * @param key Clef  css à modifier
     * @param value Valeur css à attribuer
     */
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

    /**
     * Trouve la plus grande taille de police utilisée dans la textArea
     * @return La plus grande taille de police utilisée
     */
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
