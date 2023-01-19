package Codeforces.UI;

import Codeforces.API.FetchResult;
import Codeforces.Helper.Helper;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;




public class MainController  {
	@FXML private AnchorPane panel;										// Panel on which all the elements are rendered.
	@FXML private TextField textField;									// Test Field that takes UserName as input.
	@FXML private Button visualizeButton;								// Button that fetches data for the UserName.
	@FXML private ProgressIndicator progressIndicator;					// Progress indicator which shows until the data is fetched.
	@FXML private Label commentLabel;									// Displays the message if any error occurs while fetching data.
	@FXML private ImageView logoImageView;								// ImageView which displays LOGO.
	
	
	private String handle;												// User's UserName.
	
	private SceneSwitchProvider sceneSwitchProvider;					// Helps in switching scenes.
	private FetchResult fetchResult;									// Stores all the data that are fetched.

	private String logoUrlString = "https://codeforces.org/s/95681/images/codeforces-sponsored-by-ton.png";
	
	
	
	
	/**
	 * All the initialization part is done in this function.
	 * @throws InterruptedException 
	 */
	public void initialize() {
		sceneSwitchProvider = new SceneSwitchProvider();
		fetchResult = new FetchResult();
		
		commentLabel.setText("By Priyansh Singh");
		
		setTextFieldInputChangeListener();
		SetLogo();
	}
	
	
	
	/**
	 * Sets the LOGO at the center.
	 */
	public void SetLogo() {
		Image img = new Image(logoUrlString, 800, 250, true, true);
		
        if (img != null) {
            double w = img.getWidth();
            double h = img.getHeight();
            
            double mid = logoImageView.getFitWidth()/2.0;
            
            logoImageView.setImage(img);
            
            logoImageView.setFitWidth(w);
            logoImageView.setFitHeight(h);
            
            logoImageView.setTranslateX((mid) - (w/ 3));

        }
    }
	
	
	
	/**
	 * Fetches data of the username that user gave as input in textfield.
	 * @param e Action event.
	 * @throws InterruptedException 
	 */
	public void VisualizeButton(ActionEvent e) throws InterruptedException 
	{
		visualizeButton.setDisable(true);
		
		fetchResult.setHandle(handle);
		String statuString = fetchResult.Fetch();
		
		System.out.println(statuString);
		if(statuString != null)
		{
			commentLabel.setText(statuString);
			visualizeButton.setDisable(false);
			return;
		}
		
		
		textField.setText("");
		sceneSwitchProvider.GotoScene(e, SceneSwitchProvider.profileScene);
	}

	
	
	/**
	 * This method adds a Change Listener to the text property of the TextField.
	 */
	public void setTextFieldInputChangeListener()
	{
		textField.textProperty().addListener(
				(x, y, z)->{
					String inputText = textField.getText();
					int n = textField.getText().length();
					
					if(n <= 2)	{
						visualizeButton.setDisable(true);
						return;
					}
					
					System.out.println(panel.getWidth() + " " + panel.getHeight());
					
					handle = inputText.trim();
					visualizeButton.setDisable(false);
				}
		);
	}


	public String getHandle() {
		return handle;
	}
}
























