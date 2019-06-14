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
import javafx.scene.control.Button;
import java.io.*;

// Exemplo em JavaFX com tratamento de evento associado a um objeto da classe Button
// Ver mais sobre classes anonimas em:
// https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html


public class RandomPickerGUI extends Application {

    public String[] rows = new String[50];
    private static String[] resultado;
    private int aux = 1;

    public static Label lbl = new Label("");
    public static Button nextButton = new Button("Next");

    private Scanner x;

   public static void main(String[] args) {
      launch(args);
   }
   public void start(Stage stage){
       OfflineRandom.Interface = 1;
       OnlineRandom.Interface = 1;

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
       nextButton.setVisible(false);


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
              fileChooser.setTitle("Escolha o arquivo desejado");
              File file = fileChooser.showOpenDialog(stage);
              String tmp = "";
              if(file != null){
                  try{
                      x = new Scanner(new File(file.getAbsolutePath()));
                  }
                  catch(Exception e){
                      System.out.println("could not find file");
                  }
                  while(x.hasNext())
                      tmp = tmp.concat(x.next()+"\n");
                  textArea.setText(tmp);
              }
          }
      });

      shuffleButton.setOnAction(new EventHandler<ActionEvent>() {
         public void handle(ActionEvent event) {
             rows = textArea.getText().split("\n");
             String[] tmp = rows.clone();
             aux = 1;
             if(tmp.length < 2){
                 System.out.println("\nA lista deve possuir ao menos 2 nomes\n");
                 return;
             }
             System.out.println("\nTeste de conexao sera executado, aguarde um instante...\n");
             try{
                 switch(CheckInternetConnection.check()){
                     case 0: Platform.runLater(() -> OfflineRandom.randMeth(tmp));
                     break;
                     case 1: Platform.runLater(() -> OnlineRandom.onlineRandom(tmp));
                     break;
                 }
             }catch(Exception erro){
          	      return;
             }

        }
    });

    nextButton.setOnAction(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
            lbl.setText(resultado[aux]); aux++;
            if(aux == resultado.length)
                nextButton.setVisible(false);
		}
	});
// ---------------------------------------------------------------
      vbox.getChildren().addAll(menuBar, textArea, shuffleButton, lbl, nextButton);
      stage.setScene(new Scene(vbox, 300, 275));
      stage.show();
   }

   public static void SetRows(String[] string){
       resultado = string.clone();
       nextButton.setVisible(true);
       lbl.setText(resultado[0]);
   }

}
