package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Shear;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class JavaFX_Transformation extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root, 1000, 700);
		scene.setFill(Color.WHITE);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Testing JavaFx Effects");
		
		BuildGUI(scene);
		
		primaryStage.show();
	}
	
	private void BuildGUI(Scene scene)
	{
		Rectangle r1 = new Rectangle(520, 20, 150, 200);
		Rectangle r2 = new Rectangle(20, 20, 150, 200);
		
		r1.setFill(Color.RED); 	r2.setFill(Color.GREEN);
		
//		Translation(r1);
//		Rotation(r1);
//		Scaling(r1);
		Shearing(r1);
		
		Group rootGroup = (Group)scene.getRoot();
		rootGroup.getChildren().addAll(r1, r2);
	}
	
	private void Shearing(Rectangle r1) {
		Shear shear = new Shear();
		shear.setPivotX(595);	shear.setPivotY(120);
		shear.setX(-.5); 
		shear.setY(-.1);
		
		r1.getTransforms().add(shear);
	}
	
	private void Scaling(Rectangle r1) {
		Scale scale = new Scale();
		scale.setX(1.5);	scale.setY(1.5);
		scale.setPivotX(595); scale.setPivotY(120);
		
		r1.getTransforms().add(scale);
	}
	
	private void Rotation(Rectangle r1) {
		Rotate rotate = new Rotate();
		rotate.setAngle(45);
		rotate.setPivotX(520);
		rotate.setPivotY(220);
		
		r1.getTransforms().addAll(rotate);
	}
	
	private void Translation(Rectangle r1) {
		Translate translate = new Translate();
		translate.setX(100); translate.setY(100);  translate.setZ(100);
		
		r1.getTransforms().addAll(translate);
	}
}
