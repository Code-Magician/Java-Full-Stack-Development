package Codeforces.UI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


class SceneSwitchProvider {
	public static String profileScene = "Profile.fxml";
	public static String mainScene = "Main.fxml";
	
	
	public void GotoScene(ActionEvent e, String sceneName) 
	{
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(sceneName));
			
			Scene scene = new Scene(root);
			
			Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			stage.setScene(scene);

			stage.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
