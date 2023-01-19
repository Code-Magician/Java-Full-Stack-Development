package application;
	
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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));

			Parent root = loader.load();
			
			Scene scene = new Scene(root);
			
			primaryStage.setTitle("Subtitles Generator");
			primaryStage.getIcons().add(new Image("https://cdn.iconscout.com/icon/free/png-256/code-forces-3629285-3031869.png"));
			primaryStage.setScene(scene);
			
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent arg0) {
//					Exiting platform and system.
					Platform.exit();
					System.exit(0);
				}
			});
		
			primaryStage.setMinHeight(400);
			primaryStage.setMinWidth(800);
			primaryStage.setResizable(false);
			
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
