package application.EventHandling;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

public class EventHandlingController {
	@FXML
	private Circle circle;
	private double x, y;
	
	private double offset = 10;
	
	public void MoveUP(ActionEvent e)
	{
		circle.setCenterY(y-=offset);
	}
	public void MoveDOWN(ActionEvent e)
	{
		circle.setCenterY(y+=offset);
	}
	public void MoveLEFT(ActionEvent e)
	{
		circle.setCenterX(x-=offset);
	}
	public void MoveRIGHT(ActionEvent e)
	{
		circle.setCenterX(x+=offset);
	}
}
