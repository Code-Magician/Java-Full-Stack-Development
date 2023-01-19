package Codeforces;
	
import Codeforces.Helper.Helper;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


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
			
//			Set Title
			primaryStage.setTitle("Codeforces Visualizer");
			
//			Set Logo
			primaryStage.getIcons().add(new Image("https://cdn.iconscout.com/icon/free/png-256/code-forces-3629285-3031869.png"));

			
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
			
			
//			Setting size
			primaryStage.setMaximized(true);
			primaryStage.setMinHeight(850);
			primaryStage.setMinWidth(1500);
			System.out.println(primaryStage.getHeight() + " " + primaryStage.getWidth());
			Helper.setScreenHeight(primaryStage.getHeight());
			Helper.setScreenWidth(primaryStage.getWidth());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
