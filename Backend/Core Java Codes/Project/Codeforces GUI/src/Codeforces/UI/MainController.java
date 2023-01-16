package Codeforces.UI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;




public class MainController implements Initializable {
	@FXML private TextField textField;
	@FXML private Button visualizeButton;
	
	private String handle;

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setTextFieldInputChangeListener();
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
					
					handle = inputText;
					visualizeButton.setDisable(false);
				}
		);
	}


	public String getHandle() {
		return handle;
	}


}
