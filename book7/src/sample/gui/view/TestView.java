package sample.gui.view;

import javafx.css.StyleClass;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import org.fxmisc.richtext.InlineCssTextArea;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.fxmisc.richtext.StyledTextArea;

import java.util.Collection;

public class TestView extends VBox {

    public TestView(){
//        TextArea mainText = new TextArea();
        StyleClassedTextArea mainText = new StyleClassedTextArea();
        mainText.appendText("The standard Lorem Ipsum passage, used since the 1500s\n" +
                "\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"\n" +
                "\n" +
                "Section 1.10.32 of \"de Finibus Bonorum et Malorum\", written by Cicero in 45 BC\n" +
                "\"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?\"");
        mainText.setMinWidth(600);
        mainText.setMinHeight(300);

        try {
            mainText.setStyleClass(10, 20, "bold");
        } catch (IndexOutOfBoundsException e){
            System.out.println(e);
        }

        Button textRed = new Button();
        textRed.setText("text-red");
        textRed.setOnAction((event)->{
            // Index de la sélection Début/Fin
            int debut = mainText.getSelection().getStart();
            int fin = mainText.getSelection().getEnd();
            System.out.println(mainText.getStyleSpans(debut, fin));
            System.out.println();
        });

        Button italic = new Button();
        italic.setText("italic");
        italic.setOnAction((event)->{
            System.out.println(mainText.getStyle());
            // Index de la sélection Début/Fin
            int debut = mainText.getSelection().getStart();
            int fin = mainText.getSelection().getEnd();
            mainText.setStyleClass(debut, fin, "italic");
        });


        getChildren().add(mainText);
        getChildren().add(textRed);
        getChildren().add(italic);
        }


}
