package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class JavaFX_Layouts extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(new Group(), 500, 500);
		scene.setFill(Color.WHITE);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Testing JavaFx Effects");
		
//		scene.setRoot(UsingBorderLayout());
//		scene.setRoot(UsingHBox());  // Similarly we can make VBox
//		scene.setRoot(UsingStackPane());
//		scene.setRoot(UsingGridPane());
		scene.setRoot(UsingFlowPane());
		
		primaryStage.show();
	}
	
	private FlowPane UsingFlowPane()
	{
		FlowPane flowPane = new FlowPane();
		
		flowPane.setHgap(10); 	flowPane.setVgap(10);
		flowPane.setPrefWidth(100); 	flowPane.setPrefHeight(100);
		
		flowPane.getChildren().addAll(
				new Button("First ajsdflajdldajladladslflsdjl"), 
				new Button("Second adfjadskjfkladjfladjljadflasdlfal"), 
				new Button("Third fadsfjdskjaldjlasd"), 
				new Button("Fourthasldkfjlaksjlkaslkajasdj"));
		
		return flowPane;
	}
	
	private GridPane UsingGridPane() {
		GridPane gridPane = new GridPane();
		
		gridPane.setHgap(10); 	gridPane.setVgap(10);
		gridPane.addRow(0, new Label("First Name "), new TextField());
		gridPane.addRow(1, new Label("Last Name "), new TextField());
		gridPane.addRow(2, new Button("Submit"));
		
		return gridPane;
	}
	
	private StackPane UsingStackPane()
	{
		StackPane stackPane = new StackPane();
		
		Button b1 = new Button("Bottom");
		Button b2 = new Button("Top");
		
		stackPane.setAlignment(Pos.TOP_CENTER);
		stackPane.getChildren().addAll(b1, b2);
		
		return stackPane;
	}
	
	private HBox UsingHBox()
	{
//		Vbox is also similar.
		HBox hBox = new HBox();
		
		hBox.setSpacing(5);
		hBox.setFillHeight(true);
		hBox.setAlignment(Pos.CENTER);
		
		Button b1 = new Button("Button 1");
		Button b2 = new Button("Button 2");
		
		hBox.getChildren().addAll(b1, b2);
		
		return hBox;
	}
	
	private BorderPane UsingBorderLayout()
	{
		BorderPane borderPane = new BorderPane();
		
		borderPane.setLeft(new Label("This is Left"));
		borderPane.setRight(new Label("This is Right"));
		borderPane.setTop(new Label("This is Top"));
		borderPane.setBottom(new Label("This is Bottom"));
		borderPane.setCenter(new Label("This is Center"));
		
		return borderPane;
	}
}
