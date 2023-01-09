package application.KeyEvents;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
		Parent root = loader.load();
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Scene Switching");
		
		
		Controller controller = loader.getController();
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case W: 
					controller.MoveUP();
					break;
				case S:
					controller.MoveDOWN();
					break;
				case A:
					controller.MoveLEFT();
					break;
				case D:
					controller.MoveRIGHT();
					break;
				default:
					break;
				}
			}
			
		});
		
		primaryStage.show();
	}
}
