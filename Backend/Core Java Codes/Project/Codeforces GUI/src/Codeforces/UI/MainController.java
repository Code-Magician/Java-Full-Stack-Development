package Codeforces.UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Codeforces.API.FetchResult;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;




public class MainController implements Initializable {
	@FXML private TextField textField;
	@FXML private Button visualizeButton;
	@FXML private ProgressIndicator progressIndicator;
	
	private String handle;
	
	private SceneSwitchProvider sceneSwitchProvider;
	private FetchResult fetchResult;

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		sceneSwitchProvider = new SceneSwitchProvider();
		fetchResult = new FetchResult();
		
		setTextFieldInputChangeListener();
	}
	
	
	
	public void VisualizeButton(ActionEvent e) 
	{
//		progressIndicator.setVisible(true);
		System.out.println("Started");

		fetchResult.setHandle(handle);
		fetchResult.Fetch();
		
//		progressIndicator.setVisible(false);
		System.out.println("Ended");
		
//		Check for any errors.
		sceneSwitchProvider.GotoScene(e, SceneSwitchProvider.profileScene);
	}

	
	
	public void setTextFieldInputChangeListener()
	{
		textField.textProperty().addListener(
				(x, y, z)->{
					String inputText = textField.getText();
					int n = textField.getText().length();
					
					if(n <= 1)	{
						visualizeButton.setDisable(true);
						return;
					}
					
					handle = inputText.trim();
					
					visualizeButton.setDisable(false);
				}
		);
	}


	public String getHandle() {
		return handle;
	}
	
	
	
	
	
	
}
