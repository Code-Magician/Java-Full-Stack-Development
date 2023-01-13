package SnakeGame;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameController {
	@FXML private AnchorPane gamePanel;
	
	private Scene scene;
	private Stage stage;
	
	private SnakeController snakeController;
	
	
	
	public void Initialize(Scene scene)
	{
		this.scene = scene;
		stage = (Stage)scene.getWindow();
		
		snakeController = new SnakeController(gamePanel);
		
		SetKeyListener();
	}
	
	
	public void SetKeyListener()
	{
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent key) {
				switch (key.getCode()) {
				case UP:
				case W:
					System.out.println("Moving Up");
					snakeController.MoveUp();
					break;
				case DOWN:
				case S:
					System.out.println("Moving Down");
					snakeController.MoveDown();
					break;
				case LEFT:
				case A:
					System.out.println("Moving Left");
					snakeController.MoveLeft();
					break;
				case RIGHT:
				case D:
					System.out.println("Moving Right");
					snakeController.MoveRight();
					break;
				default:
					System.out.println("Noting Happens");
					break;
				}
			}
		});
	}
}
