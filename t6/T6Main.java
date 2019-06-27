import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.*;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.io.File;

public class T6Main extends Application {

    public static final String csvPath = "C:\\t6\\enadecc.txt";
    public static String onlineURL = "https://docs.google.com/spreadsheets/d/e/2PACX-1vTO06Jdr3J1kPYoTPRkdUaq8XuslvSD5--FPMht-ilVBT1gExJXDPTiX0P3FsrxV5VKUZJrIUtH1wvN/pub?gid=0&single=true&output=csv";

    public class DataEntry {
        private SimpleStringProperty col_Ano;
        private SimpleStringProperty col_Prova;
        private SimpleStringProperty col_TipoQuestao;
        private SimpleStringProperty col_idquestao;
        private SimpleStringProperty col_objeto;
        private SimpleStringProperty col_acertosCurso;
        private SimpleStringProperty col_acertosRegiao;
        private SimpleStringProperty col_acertosBrasil;
        private SimpleStringProperty col_acertosDif;

        private DataEntry(String b, String c, String d, String e, String f, String i, String j, String k, String l) {
            this.col_Ano = new SimpleStringProperty(b);
            this.col_Prova = new SimpleStringProperty(c);
            this.col_TipoQuestao = new SimpleStringProperty(d);
            this.col_idquestao = new SimpleStringProperty(e);
            this.col_objeto = new SimpleStringProperty(f);
            this.col_acertosCurso = new SimpleStringProperty(i);
            this.col_acertosRegiao = new SimpleStringProperty(j);
            this.col_acertosBrasil = new SimpleStringProperty(k);
            this.col_acertosDif = new SimpleStringProperty(l);
        }

        public SimpleStringProperty firstProperty() {
            return col_Ano;
        }
        public SimpleStringProperty secondProperty() {
            return col_Prova;
        }
        public SimpleStringProperty thirdProperty() {
            return col_TipoQuestao;
        }
        public SimpleStringProperty fourthProperty() {
            return col_idquestao;
        }
        public SimpleStringProperty fifthProperty() {
            return col_objeto;
        }
        public SimpleStringProperty sixthProperty() {
            return col_acertosCurso;
        }
        public SimpleStringProperty seventhProperty() {
            return col_acertosRegiao;
        }
        public SimpleStringProperty eighthProperty() {
            return col_acertosBrasil;
        }
        public SimpleStringProperty ninthProperty() {
            return col_acertosDif;
        }

        public String getFirst() {
            return col_Ano.get();
        }
        public String getSecond() {
            return col_Prova.get();
        }
        public String getThird() {
            return col_TipoQuestao.get();
        }
        public String getFourth() {
            return col_idquestao.get();
        }
        public String getFifth() {
            return col_objeto.get();
        }
        public String getSixth() {
            return col_acertosCurso.get();
        }
        public String getSeventh() {
            return col_acertosRegiao.get();
        }
        public String getEighth() {
            return col_acertosBrasil.get();
        }
        public String getNinth() {
            return col_acertosDif.get();
        }

        public void setFirst(String b) {
            col_Ano.set(b);
        }
        public void setSecond(String c) {
            col_Prova.set(c);
        }
        public void setThird(String d) {
            col_TipoQuestao.set(d);
        }
        public void setFourth(String e) {
            col_idquestao.set(e);
        }
        public void setFifth(String f) {
            col_objeto.set(f);
        }
        public void setSixth(String i) {
            col_acertosCurso.set(i);
        }
        public void setSeventh(String j) {
            col_acertosRegiao.set(j);
        }
        public void setEighth(String k) {
            col_acertosBrasil.set(k);
        }
        public void setNinth(String l) {
            col_acertosDif.set(l);
        }
    }


    public void readFromFile() {
        String fileName;
        try{
            BufferedReader br = new BufferedReader(new FileReader(csvPath));
            String line;
            while ((line = br.readLine()) != null) {
                String[] tmp = line.split("-");
                 data.add(new DataEntry(tmp[1],tmp[2], tmp[3], tmp[4], tmp[5], tmp[8], tmp[9], tmp[10], tmp[11]));
            }
            br.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

  private final ObservableList<DataEntry> data =
    FXCollections.observableArrayList(
      new DataEntry("Bbbbb", "Ccccc", "Ddddd", "Eeeee", "FFFFFF", "Iiiii", "Jjjjj", "Kkkkk", "Lllll"));

  @Override
  public void start(Stage stage) {
    readFromFile();
    // ---------------------------------- Menus

   stage.setTitle("Illuminati");

   final Menu fileMenu = new Menu("File");
   final Menu helpMenu = new Menu("Help");

   final MenuItem subMenuReload = new MenuItem("Reload");
   final MenuItem subMenuSource = new MenuItem("Source");
   final MenuItem subMenuExit = new MenuItem("Exit");
   final MenuItem subMenuAbout = new MenuItem("About");

   fileMenu.getItems().addAll(subMenuReload, subMenuSource, subMenuExit);
   helpMenu.getItems().add(subMenuAbout);

   MenuBar menuBar = new MenuBar();
   menuBar.getMenus().addAll(fileMenu, helpMenu);

   // ------------------Botoes----------------------

   subMenuExit.setOnAction(e -> Buttons.exit());
   subMenuAbout.setOnAction(e -> Buttons.about());
   subMenuSource.setOnAction(e -> Buttons.source());
   subMenuReload.setOnAction(e -> Buttons.reload());

   // ----------------------------------------------

    TableView table = new TableView();
    TableColumn col_Ano = new TableColumn("Ano");
    TableColumn col_Prova = new TableColumn("Prova");
    TableColumn col_TipoQuestao = new TableColumn("Tipo Questao");
    TableColumn col_idquestao = new TableColumn("ID Questao");
    TableColumn col_objeto = new TableColumn("Objeto");
    TableColumn col_acertosCurso = new TableColumn("Acertos Curso");
    TableColumn col_acertosRegiao = new TableColumn("Acertos Regiao");
    TableColumn col_acertosBrasil = new TableColumn("Acertos Brasil");
    TableColumn col_acertosDif = new TableColumn("Acertos Dif");
    table.getColumns().addAll(col_Ano, col_Prova, col_TipoQuestao, col_idquestao, col_objeto, col_acertosCurso, col_acertosRegiao, col_acertosBrasil, col_acertosDif);

    col_Ano.setCellValueFactory(
      new PropertyValueFactory<DataEntry,String>("first"));
    col_Prova.setCellValueFactory(
      new PropertyValueFactory<DataEntry,String>("second"));
    col_TipoQuestao.setCellValueFactory(
      new PropertyValueFactory<DataEntry,String>("third"));
    col_idquestao.setCellValueFactory(
      new PropertyValueFactory<DataEntry,String>("fourth"));
    col_objeto.setCellValueFactory(
      new PropertyValueFactory<DataEntry,String>("fifth"));
    col_acertosCurso.setCellValueFactory(
      new PropertyValueFactory<DataEntry,String>("sixth"));
    col_acertosRegiao.setCellValueFactory(
      new PropertyValueFactory<DataEntry,String>("seventh"));
    col_acertosBrasil.setCellValueFactory(
      new PropertyValueFactory<DataEntry,String>("eighth"));
    col_acertosDif.setCellValueFactory(
      new PropertyValueFactory<DataEntry,String>("ninth"));

    table.setItems(data);

    Button btnDel = new Button("Del");

    VBox vbox = new VBox();
    vbox.setSpacing(5);
    vbox.getChildren().addAll(menuBar, table, btnDel);

    stage.setScene(new Scene(vbox,700,400));
    stage.show();

    }

   public static void main(String[] args) {

       File f = new File(csvPath);
       if(f.exists() && !f.isDirectory())
            System.out.println("O arquivo enade ja existe.");
       else{
           System.out.println("O arquivo enade nao existe. Um novo sera baixado.");
           OnlineDownload.download(onlineURL);
       }
       launch(args);
   }
}
