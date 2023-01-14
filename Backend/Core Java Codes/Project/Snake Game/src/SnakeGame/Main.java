package SnakeGame;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
//			Opening an FXML file.
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));

//			Loading the FXML file.
			Parent root = loader.load();
			
//			Making new Scene.
			Scene scene = new Scene(root);
			
//			Setting Scene on the stage.
			primaryStage.setScene(scene);
			
//			Setting Height and Width of the stage.
			primaryStage.setHeight(700);
			primaryStage.setWidth(700);
			
//			Making Window Non-Resizable.
			primaryStage.setResizable(false);
			
//			Setting On cross button pressed.
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent arg0) {
//					Exiting platform and system.
					Platform.exit();
					System.exit(0);
				}
			});
			
			primaryStage.show();

//			Getting the controller used in FXML file and calling initialization method.
			GameController controller = loader.getController();
			controller.Initialize(scene);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
