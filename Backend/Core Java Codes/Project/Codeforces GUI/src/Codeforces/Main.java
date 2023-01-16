package Codeforces;
	
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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("UI/Main.fxml"));

//			Loading the FXML file.
			Parent root = loader.load();
			
//			Making new Scene.
			Scene scene = new Scene(root);
			
//			Setting Scene on the stage.
			primaryStage.setScene(scene);
			
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
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
		
	}
}
