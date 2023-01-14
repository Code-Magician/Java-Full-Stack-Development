package SnakeGame;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;





class SnakePart{
	public Circle part;									// Element that is Visible on the gamePanel as the snakePart.
	public TranslateTransition anim;					// Animation on the part.
	public Deque<Point2D> pool;							// Stores the previous positions of the snake Part.
	
	
	
	
	
	/**
	 * @param cX			<br> x position of center;
	 * @param cY			<br> y position of center;
	 * @param rad			<br> radius of circle.
	 * @param timeMillis	<br> Animation time.
	 */
	public SnakePart(double cX, double cY, double rad, Double timeMillis) {
//		Making new Circle and Setting it's boarder color, size and fill color.
		part = new Circle(cX, cY, rad);
		part.setFill(Color.LIGHTGREEN);
		part.setStroke(Color.RED); 	part.setStrokeWidth(5);

//		Making new Animation on the snake part.
		anim = new TranslateTransition(Duration.seconds(timeMillis), part);
		anim.setInterpolator(Interpolator.LINEAR);
		
//		Initializing the pool Queue.
		pool = new ArrayDeque<>();
	}
}





public class SnakeController {
//	Enum to Tell the direction snake is moving in.
	enum Direction{
		UP,
		DOWN,
		LEFT,
		RIGHT
	};
	
	
	
	
	
	private Direction currDirection;							// Stores the Direction in which the snake is moving.
		
	public SnakePart head;										// Stores the Head Part of the snake.
	private ArrayList<SnakePart> snakeParts;					// Stores all the parts of the snake.
	
	private AnchorPane root;									// gamePanel.
	
	Double animTime = 20000.0, animDist = -1000000.0;			// Animation Timer and Distance to cover by head animation.
	
	int cnt = 0;												// Helper variable in Adding Coordinates to the snakeParts pool.
	
	
	
	public SnakeController(AnchorPane root)
	{
//		Setting gamePanel.
		this.root = root;
		
//		Initializing the ArrayList.
		snakeParts = new ArrayList<>();
		
//		Increasing size of the snake.
		IncreaseSize();
		
//		Getting the head of the snake.
		head = snakeParts.get(0);
	}
	
	
	
	/**
	 * This Function sets the coordinates of snake parts in its pool.
	 * Pool will be updates every 1/4th times the total frames/seconds.
	 * Or we can say this function will be invoked every frame. but will update the
	 * Pool only 1/4th times.
	 */
	public void UpdatePool()
	{
//		Increase count.
		cnt++;
		
//		If count this is divisible by 4 then update pool.
		if(cnt%4 == 0)
		{
//			Set count again to zero.
			cnt = 0;
			
//			Traversing All the snake parts in List and adding their current position at the end of the queue.
			for(int i=0; i<snakeParts.size(); i++)
			{
//				Getting the current snake part and its pool.
				Circle part = snakeParts.get(i).part;
				Deque<Point2D> pool = snakeParts.get(i).pool;
				
//				Maintaining the size of pool as 10.
				if(pool.size() >= 10)	pool.removeFirst();
				
//				Adding current position to the end of pool queue.
				pool.addLast(new Point2D(
						part.translateXProperty().get(), 
						part.translateYProperty().get())
				);
			}
		}
		
		
//		If the Animation on head is running then move the other parts.
		if(snakeParts.get(0).anim.getStatus() == javafx.animation.Animation.Status.RUNNING)
			MoveParts();
	}
	
	
	
	/**
	 * Adds the snake part at the end of the list snakeParts.
	 */
	public void IncreaseSize()
	{
		SnakePart snakePart = new SnakePart(0, 0, 20, animTime);
		snakePart.part.setTranslateX(350); 	snakePart.part.setTranslateY(350);
		
		int n = snakeParts.size();
		if(n >= 1)
		{
			SnakePart tail = snakeParts.get(n-1);
			snakePart.part.setTranslateX(tail.part.translateXProperty().get());
			snakePart.part.setTranslateY(tail.part.translateYProperty().get());
		}
		
		root.getChildren().add(snakePart.part);
		snakeParts.add(snakePart);
	}
	
	
	
	public void MoveParts()
	{
		int n = snakeParts.size();
		if(n <= 1)	return;
		
//		for(int i=1; i<n; i++)
//			snakeAnim.get(i).stop();
		
		
		for(int i=n-1; i>=1; i--)
		{
			TranslateTransition currAnim = snakeParts.get(i).anim;
			
			Circle currPart = snakeParts.get(i).part;
			Deque<Point2D> 	nextPool = snakeParts.get(i-1).pool;
			currAnim.setDuration(Duration.millis(1));
			
			
			currAnim.setFromX(currPart.translateXProperty().get());	currAnim.setFromY(currPart.translateYProperty().get());
			currAnim.setToX(nextPool.getFirst().getX());		currAnim.setToY(nextPool.getFirst().getY());
			
			currAnim.play();
		}
//		
//		System.out.println();
//		System.out.println(head.part.translateXProperty().get() + " " + head.part.translateYProperty().get());
//		System.out.println(snakeParts.get(1).part.translateXProperty().get() + " " + snakeParts.get(1).part.translateYProperty().get());
//		System.out.println();
	}
	
	private boolean IsValidDirectionChange(Direction from, Direction to)
	{
		if(to == null)	return false;
		if(from == null) return true;
		
		if(from == Direction.UP && to == Direction.DOWN)	return false;
		if(from == Direction.DOWN && to == Direction.UP)	return false;
		if(from == Direction.LEFT && to == Direction.RIGHT)	return false;
		if(from == Direction.RIGHT && to == Direction.LEFT)	return false;
		
		return true;
	}
	
	
	public boolean IsSelfCollided()
	{
		int n = snakeParts.size();
		
		if(n <= 3) return false;
		
		for(int i=n-1; i>=3; i--)
		{
			Circle part = snakeParts.get(i).part;
			if(head.part.getBoundsInParent().intersects(part.getBoundsInParent()))	return true;
		}
		
		return false;
	}
	
	
	
	public void MoveUp()
	{
		if(!IsValidDirectionChange(currDirection, Direction.UP))	return;
		
		currDirection = Direction.UP;
		
		TranslateTransition headAnim = head.anim;
		headAnim.stop();
		
		headAnim.setByX(0); 	headAnim.setByY(animDist);
		headAnim.play();
	}
	
	
	
	public void MoveDown()
	{
		if(!IsValidDirectionChange(currDirection, Direction.DOWN))	return;
		
		currDirection = Direction.DOWN;
		
		TranslateTransition headAnim = head.anim;
		headAnim.stop();
		
		headAnim.setByX(0); 	headAnim.setByY(-animDist);
		headAnim.play();
	}
	
	
	
	public void MoveLeft()
	{
		if(!IsValidDirectionChange(currDirection, Direction.LEFT))	return;
		
		currDirection = Direction.LEFT;
		
		TranslateTransition headAnim = head.anim;
		headAnim.stop();
		
		headAnim.setByX(animDist); 	headAnim.setByY(0);
		headAnim.play();
	}
	
	
	
	public void MoveRight()
	{
		if(!IsValidDirectionChange(currDirection, Direction.RIGHT))	return;
		
		currDirection = Direction.RIGHT;
		
		TranslateTransition headAnim = head.anim;
		headAnim.stop();
		
		headAnim.setByX(-animDist); 	headAnim.setByY(0);
		headAnim.play();
	}
}
