package application.SwitchScenes;

import javafx.application.Application;
import javafx.event.ActionEvent;import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SceneSwitchApp extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Scene Switching");
		
		primaryStage.setOnCloseRequest(e -> {
//			If we click on cancel in alert box then e.consume() will stop the Exit function to be executed.
			e.consume(); 
			Exit(primaryStage);
		});
		
		primaryStage.show();
	}
	
	public void Exit(Stage stage)
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit Application");
		alert.setHeaderText("Do you want to Exit this Application?");
		alert.setContentText("Click Ok to Exit Application");
		
		if(alert.showAndWait().get() == ButtonType.OK) {
			stage.close();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
