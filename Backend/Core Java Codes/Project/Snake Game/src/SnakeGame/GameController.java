package SnakeGame;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;





public class GameController {
	@FXML private AnchorPane gamePanel;							// Base Game Panel.
	@FXML private AnchorPane gameOverPanel;						// Panel that appears when game is over.
	@FXML private Label scoreLabel;								// Label that displays score.
	@FXML private Button pauseButton;							// Button that pauses the game.
	
	private double SCREEN_WIDTH, SCREEN_HEIGHT;					// {gamePanel} width and height.
	
	private Scene scene;										// Main scene of the game.
	private Stage stage;										// Primary Stage.
	
	private SnakeController snakeController;					// Instance of Snake Controller class.
	private ArrayList<Food> foods;								// List that stores all the instance of Food class.
	
	private AnimationTimer updateTimer;							// Timer that runs inside the update game method each frame.
	
	long score;													// Players current score.
	boolean pause = false;										// Tell is the game paused or not.
	int rad;													// Radius of the circle used as a snake part.
	
	int cnt = 999;												// Helper Variable to make food items. It Increases by value 1 per frame.
	
	
	
	
	
	/**
	 * Initialized import things that are needed to be done before the game starts.
	 * @param scene Main Scene of the game.
	 */
	public void Initialize(Scene scene)
	{		
//		Setting Scene and Stage.
		this.scene = scene;
		stage = (Stage)scene.getWindow();
		
//		Making Instance of SnakeController and Food class.
		snakeController = new SnakeController(gamePanel);
		foods = new ArrayList<>();
		
//		Setting gamePanel Width and Height.
		SCREEN_WIDTH = gamePanel.getWidth();
		SCREEN_HEIGHT = gamePanel.getHeight();
		
//		Setting Initial Score.
		score = 0;
		
//		Setting Radius of circle used as a snake part.
		rad = (int)snakeController.head.part.getRadius();
		
//		Setting Focus on Button to false.
		pauseButton.setFocusTraversable(false);
		
//		Setting key Listeners on the Game.
		SetKeyListener();
		
//		Runs Every frame and Updates the Game.
		UpdateGame();
	}
	
	
	
	/**
	 * Updates the Game each frame.
	 */
	private void UpdateGame()
	{
//		Animation Timer that runs once per frame.
	   updateTimer = new AnimationTimer() {
			
			@Override
			public void handle(long arg0) {
				if(snakeController.HasNoParts())	return;
				
				cnt++;
				CheckFoodCollision();
				CheckBoundaryCrossed();
				
				snakeController.UpdatePool();
				
				if(snakeController.IsSelfCollided())
				{
					GameOver();
				}
			}
		};
		
		updateTimer.start();
	}
	
	
	
	/**
	 * Handles the change in position of the head of the snake if it crossed boundary.
	 */
	private void CheckBoundaryCrossed() {
		if(snakeController.HasNoParts()) return;
		
//		Head part of the snake.
		SnakePart headPart = snakeController.head;
		
//		Getting the Coordinates of head of the snake.
		double x =  headPart.part.translateXProperty().get();
		double y =   headPart.part.translateYProperty().get();
		
//		Flag to check if head crossed window boundaries or not.
		boolean did = false;
		
//		If head did not crossed any boundary previously then check if it crossed now or not.
		if(!did) {
			if(x > SCREEN_WIDTH + rad) 	{ did = true;		x = -rad ; 			}
			else if(x < -rad)	 		{ did = true;		x = SCREEN_WIDTH  + rad; 	}
		
			if(y > SCREEN_HEIGHT + rad) { did = true;		y = -rad ; 			}
			else if(y < -rad)			{ did = true;		y = SCREEN_HEIGHT + rad;	}
		}
		
//		If head crossed the boundaries then set the new calculated position.
		if(did) 
		{
			did = false;
			
//			Stop the Current animation.
			headPart.anim.stop();
			
//			Set the positions.
			headPart.part.setTranslateX(x);
			headPart.part.setTranslateY(y);
			
//			Play the Animation again.
			headPart.anim.play();
		}
	}
	
	
	
	/**
	 * Handles Collision between the Snake head and Food Item.
	 */
	private void CheckFoodCollision() {
//		Head part of the snake.
		SnakePart headPart = snakeController.head;
		
//		Getting all the food items present currently on the gamePanel
//		and checking if they have collided with the head of the snake.
		for(Food x:foods)
		{
			if(headPart.part.getBoundsInParent().intersects(x.food.getBoundsInParent()))
			{
//				Updating Score Board.
				UpdateScore();
				
//				Removing the food item with which the head collided from the gamePanel as well as foods List.
				gamePanel.getChildren().remove(x.food);
				foods.remove(x);
				
//				Increasing the size of the snake.
				snakeController.IncreaseSize();
				
//				Creating a new Food instance and adding it to the foods list.
				if(foods.size() <= 10) {
					foods.add(new Food(gamePanel, SCREEN_WIDTH, SCREEN_HEIGHT));
					cnt = 1;
				}
			}
		}
		
		
		if(cnt %600 == 0)
		{
			cnt = 1;
			
//			Creating a new Food instance and adding it to the foods list.
			foods.add(new Food(gamePanel, SCREEN_WIDTH, SCREEN_HEIGHT));
		}
	}
	
	
	
	/**
	 * When Snake Touches itself.
	 * Then this function is invoked.
	 */
	private void GameOver()
	{	
//		Pause the game and make pause button disable.
		pause = true; 	TogglePause();
		pauseButton.setDisable(true);
		
//		Clear all food items from gamePanel and clear the foods list.
		for(Food x:foods)	gamePanel.getChildren().remove(x.food);
		foods.clear();
		
//		Remove the snake from the gamePanel.
		snakeController.RemoveSnake();
		
//		Make game over Panel visible.
		gameOverPanel.setVisible(true);
	}
	
	
	
	/**
	 * Reset the game as a new one.
	 */
	public void Restart()
	{
//		Make Game over Panel invisible.
		gameOverPanel.setVisible(false);
		
//		Make score 0 and update score label.
		score = 0;
		scoreLabel.setText("Score : 0");
		
//		Make the new snake.
		snakeController.MakeInitialSnake();
		
//		Unpause the game and make the pause button enabled.
		pause = false;	TogglePause();
		pauseButton.setDisable(false);
	}
	
	
	
	/**
	 * Updates the score and ScoreBoard.
	 */
	private void UpdateScore()
	{
		score++;
		scoreLabel.setText("Score : " + score);	
	}
	
	
	
	/**
	 * Pauses and Un-Pauses the game.
	 */
	public void TogglePause()
	{
//		If pause is false then UN-Pause the game. Start the update timer and move the snake head.
		if(!pause)	{
			pauseButton.setText("Pause");
			updateTimer.start();
			if(!snakeController.HasNoParts()) snakeController.head.anim.play();
		}
//		Else pause the game. Stop the update timer and pause the snake head.
		else {	
			pauseButton.setText("Play");
			updateTimer.stop();
			if(!snakeController.HasNoParts()) snakeController.head.anim.pause();
		}
		
		pause = !pause;
	}
	
	
	
	/**
	 * Setting Key Listeners to the gamePanel.
	 */
	public void SetKeyListener()
	{
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent key) {
				if(snakeController.HasNoParts())	key.consume();
				
				switch (key.getCode()) {
				case UP:
				case W:
					snakeController.MoveUp();
					break;
				case DOWN:
				case S:
					snakeController.MoveDown();
					break;
				case LEFT:
				case A:
					snakeController.MoveLeft();
					break;
				case RIGHT:
				case D:
					snakeController.MoveRight();
					break;
				default:
					System.out.println("Not a Valid Key");
					break;
				}
			}
		});
	}
}
