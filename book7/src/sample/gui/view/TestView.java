package sample.gui.view;

import javafx.css.StyleClass;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import org.fxmisc.richtext.InlineCssTextArea;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.fxmisc.richtext.StyledTextArea;
import org.fxmisc.richtext.model.StyleSpan;
import org.fxmisc.richtext.model.StyleSpans;
import javafx.scene.web.WebView;

import java.util.Collection;

public class TestView extends VBox {

    String styleBold = "-fx-font-weight: bold;";
    String styleItalic = "-fx-font-style: italic;";
    String styleFontFamily = "-fx-font-family: 'Fira Sans Condensed ExtraBold';";
    String styleColorRed = "-fx-stroke: red;";

    public TestView(){
//        TextArea mainText = new TextArea();
        InlineCssTextArea mainText = new InlineCssTextArea();
        mainText.appendText("The standard Lorem Ipsum passage, used since the 1500s\n" +
                "\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"\n" +
                "\n" +
                "Section 1.10.32 of \"de Finibus Bonorum et Malorum\", written by Cicero in 45 BC\n" +
                "\"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?\"");
        mainText.setMinWidth(600);
        mainText.setMinHeight(300);

        try {
            mainText.setStyle(10, 20, styleBold);
//            mainText.setStyleClass(15, 25, "italic");
        } catch (IndexOutOfBoundsException e){
            System.out.println(e);
        }

        Button textRed = new Button();
        textRed.setText("font-family");
        textRed.setOnAction((event)->{
            // Index de la sélection Début/Fin
            int debut = mainText.getSelection().getStart();
            int fin = mainText.getSelection().getEnd();

            StyleSpans styleSpans = mainText.getStyleSpans(debut, fin);
            int nbSpan = styleSpans.getSpanCount();

            int currentBegin = debut;
            int currentEnd = debut;

            for(int i=0; i<nbSpan; i++) {
                StyleSpan styleSpan = styleSpans.getStyleSpan(i);
                currentEnd += styleSpan.getLength();

                System.out.println(styleSpan.getStyle());

                String style = (String) styleSpan.getStyle();
                style += styleFontFamily;
                style += styleColorRed;

                mainText.setStyle(currentBegin, currentEnd, style);

                currentBegin = currentEnd;
            }


//            StyleSpans<Collection<String>> var = mainText.getStyleSpans(debut, fin);
//            for (StyleSpan<Collection<String>> v1 : var){
//                v1.getStyle().add("text-red");
//                System.out.println(v1);


//                v1.getStyle().add("text-red");

        });

        Button italic = new Button();
        italic.setText("italic");
        italic.setOnAction((event)->{
            System.out.println(mainText.getStyle());
            // Index de la sélection Début/Fin
            int debut = mainText.getSelection().getStart();
            int fin = mainText.getSelection().getEnd();
            mainText.setStyle(debut, fin, styleItalic);
        });

        Button bold = new Button();
        bold.setText("bold");
        bold.setOnAction((event)->{
            System.out.println(mainText.getStyle());
            // Index de la sélection Début/Fin
            int debut = mainText.getSelection().getStart();
            int fin = mainText.getSelection().getEnd();
            mainText.setStyle(debut, fin, styleBold);
        });


        getChildren().add(mainText);
        getChildren().add(textRed);
        getChildren().add(italic);
        getChildren().add(bold);
        }



}
