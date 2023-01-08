package application;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JavaFX_Text  extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Java FX Text Demonstration");
		
		int posX = 100, posY = 10, offset = 50;
		String textString = "Testing Text Class of JAVA FX", fontString = "Abyssinica SIL";
		
		Text t1 = new Text(), t2 = new Text(), t3 = new Text(), t4 = new Text();
		
		t1.setX(posX);	t1.setY(posY);	posY += offset;
		t1.setText(textString);
		
		t2.setX(posX); t2.setY(posY);	posY += offset;
		t2.setText(textString);
		t2.setStrikethrough(true);
		t2.setFont(Font.font(fontString, FontWeight.SEMI_BOLD, FontPosture.ITALIC, 20));
		
		t3.setX(posX); t3.setY(posY);	posY += offset;
		t3.setText(textString);
		t3.setStroke(Color.GREEN);
		t3.setStrokeWidth(2);
		
		t4.setX(posX); 	t4.setY(posY);	posY += offset;
		t4.setText(textString);
		t4.setUnderline(true);
		
		root.getChildren().addAll(t1, t2, t3, t4);
		
		primaryStage.show();
	}
}
