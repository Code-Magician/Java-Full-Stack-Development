package application.SwitchScenes;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Scene1Controller {
	@FXML	public TextField inTextField;
	@FXML	public AnchorPane scenePane;
	
	public void GOTOScene2(ActionEvent e) throws IOException 
	{
//		Opening the FXML file
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
//		Loading it and making it root.
		Parent root = loader.load();
		
//		As scene 2 is loaded in loader. getting the instance of the controller used in scene 2 fxml file.
		Scene2Controller scene2Controller = loader.getController();
		scene2Controller.DisplayInput(inTextField.getText());
		
//		Creating new scene with loaded root.
		Scene scene = new Scene(root);
		
//		Getting Primary Stage and Setting scene
		Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	public void Exit(ActionEvent e)
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit Application");
		alert.setHeaderText("Do you want to Exit this Application?");
		alert.setContentText("Click Ok to Exit Application");
		
		if(alert.showAndWait().get() == ButtonType.OK) {
			Stage stage = (Stage)scenePane.getScene().getWindow();
			stage.close();
		}
	}
}
