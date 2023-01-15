package SnakeGame;

import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;





public class Food {
	public Circle food;					// Element visible in gamePanel as a Food Item.
	private double rad = 10;			// Radius of Food Item.
	private ScaleTransition scaleAnim;	// scale Animation on food.
	
	
	
	
	/**
	 * @param root   <br> gamePanel
	 * @param width  <br> width of GamePanel
	 * @param height <br> Height of GamePanel
	 */
	public Food(AnchorPane root,double width, double height) {
//		Making a new Circle at random position between the width x height Rectangle.
		food = new Circle(Math.random()*width, Math.random()*height, rad);
		
//		Setting Border size, color and circle fill color.
		food.setStroke(Color.RED); 	food.setStrokeWidth(5);
		food.setFill(Color.YELLOW);
		
		SetAnimation();
		
//		Adding the food item to the gamePanel.
		root.getChildren().add(food);
	}
	
	private  void SetAnimation() {
		scaleAnim = new ScaleTransition(Duration.millis(500), food);
		scaleAnim.setFromX(0.5);  	scaleAnim.setToX(1.5);
		scaleAnim.setFromY(0.5);  	scaleAnim.setToY(1.5);
		scaleAnim.setAutoReverse(true);
		scaleAnim.setCycleCount(Animation.INDEFINITE);
		
		scaleAnim.play();
	}
}
