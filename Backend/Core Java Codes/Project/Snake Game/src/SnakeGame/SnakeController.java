package SnakeGame;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

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
	double rate = 2.5;											// Speed of snake.
	
	
	
	
	
	public SnakeController(AnchorPane root)
	{
//		Setting gamePanel.
		this.root = root;
		
//		Initializing the ArrayList.
		snakeParts = new ArrayList<>();
		
//		Making Initial snake with 3 parts.
		MakeInitialSnake();
		
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
				if(pool.size() >= 4)	pool.removeFirst();
				
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
//		Make a snake part with center at (0, 0) and radius = 20.
		SnakePart snakePart = new SnakePart(0, 0, 20, animTime);
		snakePart.part.setTranslateX(350); 	snakePart.part.setTranslateY(350);
		
//		If snakeParts List is not empty then at this new part at the end.
		int n = snakeParts.size();
		if(n >= 1)
		{
			SnakePart tail = snakeParts.get(n-1);
			snakePart.part.setTranslateX(tail.part.translateXProperty().get());
			snakePart.part.setTranslateY(tail.part.translateYProperty().get());
		}
		
//		Add the new snake part to the gamePanel and List.
		root.getChildren().add(snakePart.part);
		snakeParts.add(snakePart);
	}
	
	
	
	/**
	 * Moving the snake Parts.
	 */
	public void MoveParts()
	{
//		Getting snake size.
		int n = snakeParts.size();

//		If snake has less than 1 part then there is noting to move so return.
		if(n <= 1)	return;
		
//		Moving the last part to the second last then second last to third last and so on.
		for(int i=n-1; i>=1; i--)
		{
//			Getting animation of part which will be moved.
			TranslateTransition currAnim = snakeParts.get(i).anim;
			
//			Getting visible element of part which will be moved.
			Circle currPart = snakeParts.get(i).part;

//			Getting pool of the next part to the current part.
			Deque<Point2D> 	nextPool = snakeParts.get(i-1).pool;
			
//			Animation duration of current part.
			currAnim.setDuration(Duration.millis(1));
			
//			Setting position to move to.
			currAnim.setFromX(currPart.translateXProperty().get());	currAnim.setFromY(currPart.translateYProperty().get());
			currAnim.setToX(nextPool.getFirst().getX());			currAnim.setToY(nextPool.getFirst().getY());
			
			currAnim.play();
		}
	}
	
	
	
	/**
	 * Checks if we can move the snake in particular direction if it's moving in a certain direction.
	 */
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
	
	
	
	/**
	 * @return true if snake collided with itself.
	 */
	public boolean IsSelfCollided()
	{
//		If size of snake is <= 3 which is initial size then return false.
		int n = snakeParts.size();
		if(n <= 3) return false;
		
//		Else check for collision.
		for(int i=n-1; i>=3; i--)
		{
			Circle part = snakeParts.get(i).part;
			if(head.part.getBoundsInParent().intersects(part.getBoundsInParent()))	return true;
		}
		
		return false;
	}
	
	
	
	/**
	 * Moves the snake in up direction.
	 */
	public void MoveUp()
	{
//		Checking if we can move up or not.
		if(!IsValidDirectionChange(currDirection, Direction.UP))	return;
		
//		Setting current direction.
		currDirection = Direction.UP;
		
//		Getting the animation.
		TranslateTransition headAnim = head.anim;
		
//		Stopping the previous animation.
		headAnim.stop();
		
//		Setting Position and Playing animation again.
		headAnim.setByX(0); 	headAnim.setByY(animDist);
		headAnim.play();
	}
	
	
	
	public void MoveDown()
	{
//		Checking if we can move up or not.
		if(!IsValidDirectionChange(currDirection, Direction.DOWN))	return;
		
//		Setting current direction.
		currDirection = Direction.DOWN;
		
//		Getting the animation.
		TranslateTransition headAnim = head.anim;
		
//		Stopping the previous animation.
		headAnim.stop();
		
//		Setting Position and Playing animation again.
		headAnim.setByX(0); 	headAnim.setByY(-animDist);
		headAnim.play();
	}
	
	
	
	public void MoveLeft()
	{
//		Checking if we can move up or not.
		if(!IsValidDirectionChange(currDirection, Direction.LEFT))	return;
		
//		Setting current direction.
		currDirection = Direction.LEFT;
		
//		Getting the animation.
		TranslateTransition headAnim = head.anim;
		
//		Stopping the previous animation.
		headAnim.stop();
		
//		Setting Position and Playing animation again.
		headAnim.setByX(animDist); 	headAnim.setByY(0);
		headAnim.play();
	}
	
	
	
	public void MoveRight()
	{
//		Checking if we can move up or not.
		if(!IsValidDirectionChange(currDirection, Direction.RIGHT))	return;
		
//		Setting current direction.
		currDirection = Direction.RIGHT;
		
//		Getting the animation.
		TranslateTransition headAnim = head.anim;
		
//		Stopping the previous animation.
		headAnim.stop();
		
//		Setting Position and Playing animation again.
		headAnim.setByX(-animDist); 	headAnim.setByY(0);
		headAnim.play();
	}
	
	
	
	/**
	 * Remove Snake from the game Panel and clear the snakeParts list.
	 */
	public void RemoveSnake()
	{
		for(SnakePart x:snakeParts)
			root.getChildren().remove(x.part);
		
		snakeParts.clear();
		
//		Set head and it's direction to null.
		head = null;
		currDirection  = null;
	}
	
	
	
	/**
	 * @return true if snake has no parts else false.
	 */
	public boolean HasNoParts()
	{
		return snakeParts.size() == 0? true : false;
	}
	
	
	
	/**
	 * Makes a new snake with 3 parts, Sets it speed and makes it move up.
	 */
	public void MakeInitialSnake()
	{
		IncreaseSize();
		IncreaseSize();
		IncreaseSize();
		
		head = snakeParts.get(0);
		head.anim.setRate(rate);
		
		MoveUp();
	}
}
