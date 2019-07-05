package application;
	
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;


public class Main extends Application {
	
	private Scanner x;
	
	ArrayList<String> urlList = new ArrayList<String>();
	
	//ListView<String> listView;
	
	TableView<TableData> table;
	
	private final ObservableList<TableData> data =
		FXCollections.observableArrayList();
	
	//@Override
	public void start(Stage stage) {
		
		stage.setTitle("Commit Analyzer");

		// --------------------------------------- tabela
		
		//listView = new ListView<>();
		table = new TableView<>();
		
		TableColumn repositorioColumn = new TableColumn("Repositorio");
		repositorioColumn.setMinWidth(400);
		repositorioColumn.setCellValueFactory(new PropertyValueFactory<TableData,String>("repositorio"));
		
		TableColumn<TableData, String> commitsColumn = new TableColumn<>("Numero Commits");
		commitsColumn.setMinWidth(150);
		commitsColumn.setCellValueFactory(new PropertyValueFactory<TableData,String>("numeroCommits"));
		
		TableColumn<TableData, String> tamMedioMessageColumn = new TableColumn<>("Tam Medio Mensagem");
		tamMedioMessageColumn.setMinWidth(150);
		tamMedioMessageColumn.setCellValueFactory(new PropertyValueFactory<TableData,String>("tamMessage"));
		
		
		table.setItems(data);
		table.getColumns().addAll(repositorioColumn, commitsColumn, tamMedioMessageColumn);
		
		
		// -------------------------------------------- Menus

		final Menu fileMenu = new Menu("File");
		final Menu toolsMenu = new Menu("Tools");
		final Menu helpMenu = new Menu("Help");

		final MenuItem subMenuOpen = new MenuItem("Open");
		final MenuItem subMenuExit = new MenuItem("Exit");

		final MenuItem subMenuTools = new MenuItem("Commit analyzer");

		final MenuItem subMenuAbout = new MenuItem("About");

		fileMenu.getItems().addAll(subMenuOpen, subMenuExit);
		toolsMenu.getItems().add(subMenuTools);
		helpMenu.getItems().add(subMenuAbout);

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu, toolsMenu, helpMenu);

		VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setAlignment(Pos.TOP_CENTER);
		vbox.getChildren().addAll(menuBar, table);

		stage.setScene(new Scene(vbox,700,450));
		stage.show();

//----------------------------------------------------Botoes
		
		subMenuExit.setOnAction(e -> Platform.exit());

		subMenuAbout.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Label aboutText = new Label("Commit analyzer by Bruno Gottlieb");
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
				String line = null;
				if(file != null){
					try{
						BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
						while ((line = br.readLine()) != null)
							urlList.add(line);
					}
					catch(Exception e){
						System.out.println("could not find file");
					}
					
					for (String s : urlList)
						data.add(new TableData(s,null,null));
					
					System.out.println("Leu os arquivos com sucesso");
				}
			}
		});

	}

	public static void main(String[] args) {
		/*DemoParseGithubWithGson d = new DemoParseGithubWithGson();
		d.start();*/
		launch(args);
	}
}

