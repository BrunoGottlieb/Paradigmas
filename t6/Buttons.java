import javafx.application.Platform;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Buttons{

    public static void exit(){
        Platform.exit();
    }

    public static void reload(){
        System.out.println("\nUm novo arquivo sera baixado. Aguarde um momento.");
        OnlineDownload.download(EnadeUFSMExplorer.onlineURL);
    }

    public static void about(){
        Label aboutText = new Label("ENADE UFSM Explorer by Bruno Gottlieb");
        StackPane aboutLayout = new StackPane();
        aboutLayout.getChildren().add(aboutText);
        Scene aboutScene = new Scene(aboutLayout, 500, 80);

        Stage janelinha = new Stage();
        janelinha.setTitle("About");
        janelinha.setScene(aboutScene);

        janelinha.setX(200);
        janelinha.setY(100);
        janelinha.show();
    }

    /*public static void selectLine(TableView table, ObservableList<DataEntry> data){
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < data.size()) {
           System.out.println("Removing "+selectedIndex);
           data.remove(selectedIndex);
        } else {
          System.out.println("Selected:"+selectedIndex);
        }
    }*/

    /*Button btnDel = new Button("Del");
    btnDel.setOnAction(ev -> {
      int selectedIndex = table.getSelectionModel().getSelectedIndex();
      if (selectedIndex >= 0 && selectedIndex < data.size()) {
         System.out.println("Removing "+selectedIndex);
         data.remove(selectedIndex);
      } else {
        System.out.println("Selected:"+selectedIndex);
      }
  });*/

    public static void source(){
        Label aboutText = new Label("Insira a URL desejada:");
        TextField textField = new TextField();
        Button confirmButton = new Button("Confirm");

        VBox vb = new VBox();
        vb.setSpacing(10);
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(aboutText, textField, confirmButton);

        Scene sourceScene = new Scene(vb, 500, 100);
        Stage janelinha = new Stage();
        janelinha.setTitle("Source");
        janelinha.setScene(sourceScene);

        janelinha.setX(200);
        janelinha.setY(100);
        janelinha.show();

        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                EnadeUFSMExplorer.onlineURL = textField.getText();
                System.out.println("URL alterada para: " + textField.getText());
            }
        });
    }
}
