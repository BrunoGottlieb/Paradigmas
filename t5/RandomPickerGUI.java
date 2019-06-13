import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Control;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import java.util.List;
import java.io.File;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

// Exemplo em JavaFX com tratamento de evento associado a um objeto da classe Button
// Ver mais sobre classes anonimas em:
// https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html


public class RandomPickerGUI extends Application {

    public String[] rows = new String[10];

   public static void main(String[] args) {
      launch(args);
   }
   public void start(Stage stage){
       stage.setTitle("Illuminati");
       VBox vbox = new VBox();
       vbox.setSpacing(10);
       vbox.setAlignment(Pos.CENTER);

       TextArea textArea = new TextArea();
       final Menu fileMenu = new Menu("File");
       final Menu optionsMenu = new Menu("Options");

       final MenuItem subMenuOpen = new MenuItem("Open");
       final MenuItem subMenuExit = new MenuItem("Exit");
       final MenuItem subMenuAbout = new MenuItem("About");

       fileMenu.getItems().add(subMenuOpen);
       fileMenu.getItems().add(subMenuExit);
       optionsMenu.getItems().add(subMenuAbout);

       MenuBar menuBar = new MenuBar();
       menuBar.getMenus().addAll(fileMenu, optionsMenu);

       Button shuffleButton = new Button("Shuffle");
       //shuffleButton.

// ----------------------------- Acoes de botoes

       subMenuExit.setOnAction(e -> Platform.exit());

       subMenuAbout.setOnAction(new EventHandler<ActionEvent>() {
           public void handle(ActionEvent event) {
               Label aboutText = new Label("Randomizer by Bruno Gottlieb");
               StackPane aboutLayout = new StackPane();
               aboutLayout.getChildren().add(aboutText);
               Scene aboutScene = new Scene(aboutLayout, 300, 80);

               Stage janelinha = new Stage();
               janelinha.setTitle("About");
               janelinha.setScene(aboutScene);

               janelinha.setX(200);
               janelinha.setY(100);
               janelinha.show();
           }
       });

       subMenuOpen.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent event) {
              FileChooser fileChooser = new FileChooser();
              fileChooser.setTitle("Open Resource File");
              fileChooser.showOpenDialog(stage);
          }
      });

      shuffleButton.setOnAction(new EventHandler<ActionEvent>() {
         public void handle(ActionEvent event) {
             rows = textArea.getText().split("\n");
             //System.out.println("Nomes obtidos:");
             /*for(int i=0; rows[i] != null; i++)
                System.out.println(rows[i]);*/
            //OfflineRandom.randMeth(rows);
            String[] tmp = rows.clone();
            Platform.runLater(() -> OfflineRandom.randMeth(tmp));
        }
    });
// ---------------------------------------------------------------
      vbox.getChildren().addAll(menuBar, textArea, shuffleButton);
      stage.setScene(new Scene(vbox, 300, 275));
      stage.show();
   }

}
