package application;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

public class JavaFX_3D_Shapes extends Application {
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
		
		PerspectiveCamera camera = new PerspectiveCamera();
		camera.setTranslateX(0); 	camera.setTranslateY(0); 	camera.setTranslateZ(-100);
		scene.setCamera(camera);
		
		
//		CreateBox(scene);
//		CreateCylinder(scene);
		CreateSphere(scene);
		
		
		primaryStage.show();
	}
	
	
	private void CreateSphere(Scene scene) {
		Sphere sphere = new Sphere();
		sphere.setRadius(100);
		sphere.setTranslateX(200); 		sphere.setTranslateY(200);  sphere.setTranslateZ(0);
		
		Group rootGroup = (Group)scene.getRoot();
		rootGroup.getChildren().add(sphere);
	}
	
	private void CreateCylinder(Scene scene)
	{
		Cylinder cylinder = new Cylinder();
		cylinder.setHeight(300);  	cylinder.setRadius(50);
		cylinder.setTranslateX(100); 	cylinder.setTranslateY(200);
		
		Group rootGroup = (Group)scene.getRoot();
		rootGroup.getChildren().add(cylinder);
	}
	
	private void CreateBox(Scene scene)
	{
		Box box = new Box();
		
		box.setHeight(100);
		box.setWidth(200);
		box.setDepth(400);
		box.setTranslateX(200);
		box.setTranslateY(200);
		box.setTranslateZ(0);
		
		Group rootGroup = (Group)scene.getRoot();
		rootGroup.getChildren().add(box);
	}
}	
