package application.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class Controller implements Initializable {
	@FXML public ChoiceBox<String> choiceBox;
	@FXML public Label label;

	String[] choiceStrings = {
			"Pizza",
			"Burger",
			"Fries"
	};
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		choiceBox.getItems().addAll(choiceStrings);
//		:: is a method reference operator.
		choiceBox.setOnAction(this::displayItem); 
	}
	
	private void displayItem(ActionEvent e)
	{
		label.setText(choiceBox.getValue());
	}
}
