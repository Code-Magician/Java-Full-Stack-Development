package application;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloWorld extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Button hwButton = new Button("Hello World");
		hwButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("App Says Hello World.");
			}
		});
		
		StackPane rootPane = new StackPane();
		rootPane.getChildren().add(hwButton);
		
		Scene scene = new Scene(rootPane, 250, 250);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Hello World Java FX App.");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
