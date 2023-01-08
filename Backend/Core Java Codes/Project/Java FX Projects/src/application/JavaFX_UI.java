package application;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class JavaFX_UI extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane root = new GridPane();
		root.setGridLinesVisible(true);
		root.setHgap(10);  	root.setVgap(10);
		
		Scene scene = new Scene(root, 500, 700);
		scene.setFill(Color.WHITE);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Testing JavaFx Effects");
		
		
//		DemonstrateLabels(scene);
//		DemonstrateButtons(scene);
//		DemonstrateRadioButton(scene); // Similar is the Check Box.
//		TextField :- See Grid Layout for Text Field Demonstration
//		DemonstratePasswordTextField(scene);
//		DemonstrateHyperlink(scene);
//		DemonstrateSlider(scene);
//		DemonstrateProgressBar(scene);
//		DemonstrateProgressIndicator(scene);
//		DemonstrateScrollbar(scene);
//		DemonstrateFileChooser(scene);
//		DemonstrateMenuBar(scene);
//		Tooltip is Not Included
		
		
		primaryStage.show();
	}
	
	
	private void DemonstrateMenuBar(Scene scene)
	{
		MenuBar menuBar = new MenuBar();
		
		Menu fileMenu = new Menu("File");
		MenuItem f1 = new MenuItem("F1"), f2 = new MenuItem("F2");
		fileMenu.getItems().addAll(f1, f2);
		
		Menu editMenu = new Menu("Edit");
		MenuItem e1 = new MenuItem("E1"), e2 = new MenuItem("E2");
		editMenu.getItems().addAll(e1, e2);
		
		Menu viewMenu = new Menu("View");
		MenuItem v1 = new MenuItem("V1"), v2 = new MenuItem("V2");
		viewMenu.getItems().addAll(v1, v2);
		
		menuBar.getMenus().addAll(fileMenu, editMenu, viewMenu);
		
		BorderPane root = new BorderPane();
		scene.setRoot(root);
		root.setTop(menuBar);
	}
	
	private void DemonstrateFileChooser(Scene scene)
	{
		FileChooser chooser = new FileChooser();
		
		Button openButton = new Button("Open File");
		openButton.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent arg0) {
						chooser.setTitle("OpenFile");
						chooser.showOpenDialog(scene.getWindow());
						System.out.println("Opened File : " + chooser);
					}
		});
		Button saveButton = new Button("Save File");
		openButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				chooser.setTitle("Save File");
				chooser.showSaveDialog(scene.getWindow());
				System.out.println("Saved File : " + chooser);
			}
		});
		
		GridPane root = (GridPane)scene.getRoot();
		root.addRow(0, openButton);
		root.addRow(1, saveButton);
	}
	
	private void DemonstrateScrollbar(Scene scene )
	{
		ScrollBar hBar = new ScrollBar(), vBar = new ScrollBar();
		
		hBar.setMin(0); 	hBar.setMax(100); 	hBar.setValue(10); 	hBar.setUnitIncrement(12); hBar.setBlockIncrement(10);
		vBar.setMin(0); 	vBar.setMax(100); 	vBar.setValue(10); 	vBar.setUnitIncrement(12); vBar.setOrientation(Orientation.VERTICAL);
		
		GridPane root = (GridPane)scene.getRoot();
		root.addRow(0, hBar);
		root.addRow(1, vBar);
	}
	
	
	private void DemonstrateProgressIndicator(Scene scene)
	{
		ProgressIndicator progressIndicator1 = new ProgressIndicator(), progressIndicator2 = new ProgressIndicator();
		progressIndicator2.setProgress(.8);
		
		GridPane root = (GridPane)scene.getRoot();
		root.addRow(0, progressIndicator1);
		root.addRow(1, progressIndicator2);
	}
	
	private void DemonstrateProgressBar(Scene scene) {
		ProgressBar progressBar1 = new ProgressBar(), progressBar2 = new ProgressBar();
		progressBar1.setMinHeight(100); 	progressBar1.setMinWidth(300);
		progressBar2.setMinHeight(100); progressBar2.setMinWidth(300);
		progressBar2.setProgress(.3);
		
		GridPane root = (GridPane)scene.getRoot();
		root.addRow(0, progressBar1);
		root.addRow(1, progressBar2);
	}
	
	
	private void DemonstrateSlider(Scene scene )
	{
		Slider slider = new Slider(0, 100, 50);
		slider.setMinSize(200, 100);
		
		GridPane root = (GridPane)scene.getRoot();
		root.getChildren().add(slider);
	}
	
	private void DemonstrateHyperlink(Scene scene)
	{
		Image img = new Image("https://www.shutterstock.com/image-vector/image-gallery-icon-symbol-premium-260nw-760626760.jpg");
		ImageView view = new ImageView(img);
		view.setFitHeight(100);   view.setFitWidth(100);
		
		Hyperlink hyperlink = new Hyperlink("Google.com");
		hyperlink.setOnAction(e->System.out.println("Link Clicked"));
		hyperlink.setGraphic(view);
		
		GridPane root = (GridPane)scene.getRoot();
		root.getChildren().add(hyperlink);
	}
	
	private void DemonstratePasswordTextField(Scene scene)
	{
		PasswordField passwordField = new PasswordField();
		passwordField.setPromptText("Enter Password");
		
		TextField textField = new TextField();
		
		GridPane root = (GridPane)scene.getRoot();
		root.addRow(0, new Label("Username : "), textField);
		root.addRow(1, new Label("Password : "), passwordField);
	}
	
	private void DemonstrateRadioButton(Scene scene)
	{
		ToggleGroup toggleGroup = new ToggleGroup();
		RadioButton b1 = new RadioButton("Option 1");
		RadioButton b2 = new RadioButton("Option 2");
		RadioButton b3 = new RadioButton("Option 3");
		RadioButton b4 = new RadioButton("Option 4");
		
		b1.setToggleGroup(toggleGroup);
		b2.setToggleGroup(toggleGroup);
		b3.setToggleGroup(toggleGroup);
		b4.setToggleGroup(toggleGroup);
		
		GridPane root = (GridPane)scene.getRoot();
		root.addColumn(0, b1, b2, b3, b4);
	}
	
	private void DemonstrateButtons(Scene scene)
	{
		Image img = new Image("https://www.shutterstock.com/image-vector/image-gallery-icon-symbol-premium-260nw-760626760.jpg");
		ImageView view = new ImageView(img);
		view.setFitHeight(100);   view.setFitWidth(100);
		
		Button b1 = new Button("Simple Button");
		
		Button b2 = new Button("ImageButton", view);
		b2.setWrapText(true);
		
		Button b3 = new Button();
		b3.setGraphic(view);
		
		Button b4 = new Button("Event Listener Button");
		b4.setEffect(new DropShadow());
		b4.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Clicked");
			}
		});
		
		
		GridPane root = (GridPane)scene.getRoot();
		root.addRow(0, b1, b2);
		root.addRow(1, b3, b4);
	}
	
	private void DemonstrateLabels(Scene scene) {
		Image img = new Image("https://www.shutterstock.com/image-vector/image-gallery-icon-symbol-premium-260nw-760626760.jpg");
		ImageView imgView = new ImageView(img);
		
		Label l1 = new Label("First Label"), l2 = new Label("Second Label", imgView);
		
		GridPane rootGroup =(GridPane)scene.getRoot();
		rootGroup.addRow(0, l1);
		rootGroup.addRow(1, l2);
	}
}
