package application;

import javax.management.modelmbean.ModelMBeanNotificationBroadcaster;
import javax.xml.xpath.XPathEvaluationResult.XPathResultType;

import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.StrokeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JavaFX_Animation extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root, 1000, 700);
		scene.setFill(Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Testing JavaFx Effects");
		
		BuildGUI(scene);
		
		primaryStage.show();
	}
	
	private void BuildGUI(Scene scene)
	{
		Rectangle rectangle = new Rectangle(400, 250, 200, 250);
		rectangle.setFill(Color.RED);
		rectangle.setStroke(Color.WHITE);	rectangle.setStrokeWidth(3);
		
//		Animations Not Included :- Pause, Sequential, Parallel
//		Rotation(rectangle);
//		Scaling(rectangle);
//		Translation(rectangle);
//		Fading(rectangle);
//		Filling(rectangle);
//		StrokeFilling(rectangle);
		PathAnimation(rectangle);
		
		Group root = (Group)scene.getRoot();
		root.getChildren().add(rectangle);
	}
	
	private void PathAnimation(Rectangle rectangle)
	{
		Path path = new Path();
		path.getElements().add(new MoveTo(100, 100));
		path.getElements().add(new CubicCurveTo(240f, 230f, 500f, 340f, 600, 50f));
		
		PathTransition pathTransition = new PathTransition();
		
		pathTransition.setPath(path);
		pathTransition.setDuration(Duration.millis(1000));
		pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransition.setCycleCount(10);
		pathTransition.setNode(rectangle);
		pathTransition.setAutoReverse(true);
		
		pathTransition.play();
	}
	
	private void StrokeFilling(Rectangle rectangle)
	{
		StrokeTransition strokeTransition = new StrokeTransition(
				Duration.millis(1000), 
				rectangle, 
				Color.GREEN, 
				Color.RED
		);
		
		strokeTransition.setAutoReverse(true);
		strokeTransition.setCycleCount(10);

		strokeTransition.play();
	}
	
	private void Filling(Rectangle rectangle)
	{
		FillTransition fillTransition = new FillTransition(Duration.millis(1000), rectangle);
		
		fillTransition.setAutoReverse(true);
		fillTransition.setFromValue(Color.RED);
		fillTransition.setToValue(Color.GREEN);
		fillTransition.setCycleCount(10);
		
		fillTransition.play();
	}
	
	private void Fading(Rectangle rectangle) {
		FadeTransition fadeTransition  = new FadeTransition(Duration.millis(1000), rectangle);
		
		fadeTransition.setAutoReverse(true);
		fadeTransition.setFromValue(0.1);
		fadeTransition.setToValue(1);
		fadeTransition.setCycleCount(10);
		
		fadeTransition.play();
	}
	
	private void Translation(Rectangle rectangle) {
		TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1000), rectangle);
		
		translateTransition.setAutoReverse(true);
		translateTransition.setByX(200); translateTransition.setByY(100);
		translateTransition.setCycleCount(10);
		
		translateTransition.play();
	}
	
	private void Scaling(Rectangle rectangle)
	{
		ScaleTransition scaleTransition = new ScaleTransition();
		
		scaleTransition.setAutoReverse(true);
		scaleTransition.setDuration(Duration.millis(1000));
		scaleTransition.setFromX(0); scaleTransition.setFromY(0);
		scaleTransition.setToX(1); 	scaleTransition.setToY(1);
		scaleTransition.setByX(0.05); scaleTransition.setByY(0.05);
		scaleTransition.setCycleCount(11);
		
		scaleTransition.setNode(rectangle);
		
		scaleTransition.play();
	}
	
	private void Rotation(Rectangle rectangle)
	{
		RotateTransition rotateTransition = new RotateTransition();
		
		rotateTransition.setDuration(Duration.millis(100));
		rotateTransition.setAxis(Rotate.Z_AXIS);
		rotateTransition.setFromAngle(0);
		rotateTransition.setToAngle(45);
		rotateTransition.setByAngle(1);
		rotateTransition.setCycleCount(100);
		
		rotateTransition.setNode(rectangle);
		rotateTransition.setAutoReverse(true);
		
		rotateTransition.play();
	}
}
