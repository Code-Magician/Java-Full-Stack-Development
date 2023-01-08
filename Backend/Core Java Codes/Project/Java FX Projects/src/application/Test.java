package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Test extends Application {
	Rectangle rectangle;
	@Override
	public void start(Stage primaryStage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Test");
		
		rectangle = new Rectangle(0, 100, 50, 50);
		
		primaryStage.show();
		int offset = 5;
		while(true)
		{
			rectangle.setX(rectangle.getX() + offset);
			Thread.sleep(100);
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
