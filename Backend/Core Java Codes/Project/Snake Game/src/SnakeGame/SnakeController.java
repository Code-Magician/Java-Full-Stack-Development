package SnakeGame;

import java.io.ObjectInputFilter.Status;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.text.ChangedCharSetException;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class SnakeController {
	private Circle head;
	private ArrayList<Circle> snake;
	private ArrayList<TranslateTransition> snakeAnim;
	
	private AnchorPane root;
	
	Double animTime = 200.0, animDist = -10000.0;
	
	
	public SnakeController(AnchorPane root)
	{
		this.root = root;
		
		snake = new ArrayList<>();
		snakeAnim = new ArrayList<>();
		
		IncreaseSize();
		IncreaseSize1();
		
		head = snake.get(0);
		
//		head.translateXProperty().addListener(new ChangeListener<Number>() {
//
//			@Override
//			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
//				MoveParts();
//			}
//		});
//		
//		head.translateYProperty().addListener(new ChangeListener<Number>() {
//
//			@Override
//			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
//				MoveParts();
//			}
//		});
		
		Timer myTimer  = new Timer();
		myTimer.scheduleAtFixedRate(
				new TimerTask() {
					
					@Override
					public void run() {
						if(snakeAnim.get(0).getStatus() == javafx.animation.Animation.Status.RUNNING)
							MoveParts();
					}
				}, 0, 10);
	}
	
	public void IncreaseSize()
	{
		Circle x = new Circle(350, 350, 20);
		x.setFill(Color.GREEN);
		x.setStroke(Color.BLACK); 	x.setStrokeWidth(2);

		TranslateTransition transition = new TranslateTransition(Duration.seconds(animTime), x);
		transition.setInterpolator(Interpolator.LINEAR);
		
		
		root.getChildren().add(x);
		snake.add(x);
		snakeAnim.add(transition);
	}
	public void IncreaseSize1()
	{
		Circle x = new Circle(350, 365, 20);
		x.setFill(Color.GREEN);
		x.setStroke(Color.BLACK); 	x.setStrokeWidth(2);
		
		TranslateTransition transition = new TranslateTransition(Duration.seconds(animTime), x);
		transition.setInterpolator(Interpolator.LINEAR);
		
		
		root.getChildren().add(x);
		snake.add(x);
		snakeAnim.add(transition);
	}
	
	
	
	public void MoveParts()
	{
		int n = snake.size();
		if(n <= 1)	return;
		
		for(int i=1; i<n; i++)
			snakeAnim.get(i).stop();
		
		for(int i=n-1; i>=1; i--)
		{
			TranslateTransition currAnim = snakeAnim.get(i);
			TranslateTransition nextAnim = snakeAnim.get(i-1);
			
			Circle currPart = snake.get(i), nextPart = snake.get(i-1);
			currAnim.setDuration(Duration.millis(50));
			
			currAnim.setFromX(currPart.translateXProperty().get());	currAnim.setFromY(currPart.translateYProperty().get());
			currAnim.setToX(nextPart.translateXProperty().get());		currAnim.setToY(nextPart.translateYProperty().get());
			
			currAnim.play();
		}
		
		System.out.println();
		System.out.println(head.translateXProperty().get() + " " + head.translateYProperty().get());
		System.out.println(snake.get(1).translateXProperty().get() + " " + snake.get(1).translateYProperty().get());
		System.out.println();
	}
	
	
	
	public void MoveUp()
	{
		for(TranslateTransition anim:snakeAnim)
			anim.stop();
		
		TranslateTransition headAnim = snakeAnim.get(0);
		headAnim.setByX(0); 	headAnim.setByY(animDist);
		headAnim.play();
	}
	public void MoveDown()
	{
		for(TranslateTransition anim:snakeAnim)
			anim.stop();
		
		TranslateTransition headAnim = snakeAnim.get(0);
		headAnim.setByX(0); 	headAnim.setByY(-animDist);
		headAnim.play();
	}
	public void MoveLeft()
	{
		for(TranslateTransition anim:snakeAnim)
			anim.stop();
		
		TranslateTransition headAnim = snakeAnim.get(0);
		headAnim.setByX(animDist); 	headAnim.setByY(0);
		headAnim.play();
	}
	public void MoveRight()
	{
		for(TranslateTransition anim:snakeAnim)
			anim.stop();
		
		TranslateTransition headAnim = snakeAnim.get(0);
		headAnim.setByX(-animDist); 	headAnim.setByY(0);
		headAnim.play();
	}
}
