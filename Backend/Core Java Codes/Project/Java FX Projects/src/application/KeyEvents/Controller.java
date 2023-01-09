package application.KeyEvents;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

public class Controller  {
	@FXML
	private Circle circle;
	private double x, y;
	
	private double offset = 10;
	
	public void MoveUP()
	{
		circle.setCenterY(y-=offset);
	}
	public void MoveDOWN()
	{
		circle.setCenterY(y+=offset);
	}
	public void MoveLEFT()
	{
		circle.setCenterX(x-=offset);
	}
	public void MoveRIGHT()
	{
		circle.setCenterX(x+=offset);
	}
}
