package SnakeGame;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;





public class Food {
	public Circle food;				// Element visible in gamePanel as a Food Item.
	private double rad = 10;		// Radius of Food Item.
	
	
	
	
	
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
		
//		Adding the food item to the gamePanel.
		root.getChildren().add(food);
	}
}
