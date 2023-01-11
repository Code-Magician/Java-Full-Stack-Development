package application;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Stack;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author Dell
 *
 */
public class Controller  {
	Double MIN_FONT_SIZE = 5.0;						// Minimum Font size that textArea can have.
	Double DEFAULT_FONT_SIZE = 12.0;				// Default Font size of text Area.
	Double MAX_FONT_SIZE = 50.0;					// Maximum Font size that textArea can have.
	
	
	@FXML private TextArea textArea;				// Area where we write text.
	@FXML private Label locationLabel;				// Label which tells us the rows and columns of the caret position.
	@FXML private GridPane replaceAllPane;			// Grid pane that shows up when we click on Replace All Menu Item in Edit.
	@FXML private TextField replaceTextField;		// Element in {replaceAllPane} that takes input string that will be replaced by {replaceWithField}.
	@FXML private TextField replaceWithField;		// Element in {replaceWithField} that takes input string that will be placed in place of {replaceTextField}.
	@FXML private HBox gotoPaneBox;					// HBox that shows up when we click on the Goto MenuItem in Edit menu.
	@FXML private TextField gotoLineField;			// Element in {gotoPaneBox} that takes string as a Input which is row number to place caret.
	
	
	private Stage stage;							// Stores Primary Stage.
	private Scene scene;							// Stores Current Scene.
	
	private File file;								// Stores current file that is opened.
	
	private boolean hasUpdateSaved = true;			// Flag to check if the changes made to the text are saved or not.
	
	private int row = 0, col = 0;					// Stores the row and column number of the caret position.
	
	Stack<String> undoStack;						// stores previous saved texts for Undo Operation.
	String intialContentString = "";				// Stores Initial string it's empty if no file is opened. and contains the content of file if any file is opened.
	String prevString = "";							// Stores the previous Saved String.
	

	
	
	
	/**
	 * @param scene : Currently Opened Scene.
	 * Handles all the code that needs to be initialized before the app starts.
	 */
	public void Initialize(Scene scene) {
//		Setting scene.
		this.scene = scene;
		
//		setting primary stage.
		stage = (Stage)scene.getWindow();
		
//		Making an instance of the stack.
		undoStack = new Stack<String>();
		
//		Setting valid title to the App when it openes first.
		SetValidTitle();
		
//		Setting Change Listener to the the TextArea.
		SetTexAreaChangeListener();
	}
	
	
	
	/**
	 * Sets the Change Listener in Text Area.
	 */
	public void SetTexAreaChangeListener()
	{
		textArea.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
//				If the Changes previously were saved then make unsaved true.
				if(hasUpdateSaved)
				{
					hasUpdateSaved = false;
				}
				
//				If text area is empty and there is not file opened then make the save true.
				if(file == null && textArea.getText().isEmpty())
				{
					hasUpdateSaved = true;
				}
				
//				Set valid title.
				SetValidTitle();
				
//				Set caret position on every change in Text Area.
				SetCaretPosition();
			}
		});
	}

	
	
	/**
	 * Changes locationLabel based on caret position.
	 * @deprecated Incomplete
	 */
	public void SetCaretPosition() {
//		String[] strings =  textArea.getText().split("\n");
//		System.out.println();
//		for(String x:strings)
//		{
//			System.out.println(x);
//		}
//		System.out.println(textArea.getCaretPosition());
//		System.out.println();
//		
//		int caret = textArea.getCaretPosition();
//		int sum=0, i = 0;
//		while(caret > sum)	{
//			sum += strings[i].length();
//			i++;
//		}
//		
//		row = i;
//		col = caret-strings[i== 0?i:i-1].length();
//		
		
		
		int caret = textArea.getCaretPosition(), currNewLine = 0;
		String textString = textArea.getText();
		
		System.out.println(textArea.getBaselineOffset());
		
		row = 0; col = 0;
		boolean found = false;
		for(int i=0; i<Math.min(caret+1, textString.length()); i++)
		{
			if(textString.charAt(i) == '\n')
			{
				row++;
				found =true;
				currNewLine = i;
			}
		}
		
		if(!found)	currNewLine = 0;
		col = caret - currNewLine;
		locationLabel.setText("Ln : " + row + ", Col : " + col);
		
	}
	
	
	
	/**
	 * Sets the valid title based on if the file is saved or unsaved and if any file is opened or not.
	 */
	public void SetValidTitle()
	{
//		If all the updates has been saved then set the title with no {*}.
//		file title will be the name of the file if any file is opened else it will be untitled.
		if(hasUpdateSaved)
		{
			stage.setTitle(file!=null?file.getName():"Untitled");
		}
//		If updates are not saved then set the file title with the {*} symbol in preceding it.
//		file title will be the name if any file is opened else it will be Untitled.
		else {
			if(file == null)	stage.setTitle("*Untitled");
			else 				stage.setTitle("*"+file.getName());
		}
	}
	
	
	
	/**
	 * Makes the file unsaved and changes name accordingly.
	 * @deprecated  Not Needed.
	 */
	public void MakeUnsaved()
	{
		hasUpdateSaved = false;
		SetValidTitle();
	}
	
	
	
	/**
	 * If the file is not saved then this alert box appears.
	 */
	public Alert SavingAlert()
	{
//		If the file is not saved then make a confirmation alert box.
//		It has 3 buttons {save, don't save, cancel}
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Notepad");
		alert.setHeaderText("Do you want to save Changes to "+ (file!=null?file.getName():"Untitled") + "?");
			
		ButtonType saveButtonType = new ButtonType("Save", ButtonData.YES);
		ButtonType dSaveButtonType = new ButtonType("Don't Save", ButtonData.NO);
			
		alert.getButtonTypes().remove(0);
		alert.getButtonTypes().addAll(dSaveButtonType, saveButtonType);
			
		return alert;
	}
	
	
	
	/**
	 * Function that Handles New MenuItem in File Menu.
	 * Creates a new file.
	 */
	public void File_New() {
//		Make current file = null
		file = null;

//		Clears the text area.
		textArea.clear();
	}
	
	
	
	/**
	 * Opens a file dialog box to open a file.
	 * <br>
	 * Sets the Choosen file to the {file} variable.
	 * <br>
	 * Sets the text of the file to textArea so we can edit.
	 */
	public void Func_File_Open()
	{
//		Opens a file chooser.
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open File");
		
//		If file is not saved then open saving alert box. else continue to open the open dialog.
		if(!hasUpdateSaved)
		{
//			Alert Box.
			Alert alert = SavingAlert();
			
//			Getting the ButtonType Pressed.
			ButtonType type = alert.showAndWait().get();
			
//			if the ButtonType is null of cross is pressed or Cancel button is pressed then return do noting.
			if(type == null || type == ButtonType.CANCEL)		return;
			
//			If the button type has data yes which is for Save. then save file.
			if(type.getButtonData() == ButtonData.YES)
			{
				Func_File_Save();
				
//				If the user clicked on Open->Save(Alert)->Cancel then just return to previous opened file and do noting.
				if(!hasUpdateSaved)		return;
			}
			
//			If the Button type has Data No then it is for Don't Save. 
			if(type.getButtonData() == ButtonData.NO)
			{
//				Opens a OpenDialog and for choosing file.
				File openedFile = fileChooser.showOpenDialog(stage);
				
//				If any file is not opened then return.
				if(openedFile == null)		return;
				
//				If we opened a new file then it must be already saved.
				hasUpdateSaved = true;
				
//				Set opened file to current file.
				file = openedFile;
			}
		}
		else {
			File openedFile = fileChooser.showOpenDialog(stage);
			
			if(openedFile == null)		return;
			file = openedFile;
		}
		
//		Set the valid title.
		SetValidTitle();
		
		
//		Opens FileReader and reads the text file. 
//		Sets Content of the file in textArea.
		try {
			FileReader fr = new FileReader(file.getAbsoluteFile());
			BufferedReader br = new BufferedReader(fr);
			
			String lineString, contentString = "";
			while((lineString = br.readLine()) != null)
			{
				if(contentString.isEmpty())		contentString = lineString;
				else							contentString += ('\n' + lineString);
			}
			textArea.setText(contentString);
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		intialContentString = textArea.getText();
		prevString = intialContentString;
	}
	
	
	
	/**
	 * If no file is opened then call Save as method.
	 * <br>
	 * else save the content of textArea in the opened file.
	 */
	public void Func_File_Save()
	{	
//		If file is null then call save as
		if(file == null)
		{
			Func_File_SaveAs();
			return;
		}
		
//		Add the previous string into the stack.
		undoStack.add(prevString);
//		Now current text in textArea is the new SavePoint.
//		So set content of textArea to prevString.
		prevString = textArea.getText();
		
		
		try (PrintWriter pw = new PrintWriter(file)) {
			pw.print(textArea.getText());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
//		File is saved.
		hasUpdateSaved = true;
		
//		Set the valid title.
		SetValidTitle();
	}
	
	
	
	
	/**
	 * Makes a new empty file.
	 * <br>
	 * Saves content of the textArea in empty file.
	 */
	public void Func_File_SaveAs()
	{
//		Make an instance of file chooser.
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save File");
		
//		Open save file dialog.
		File savedfile = fileChooser.showSaveDialog(stage);
		
//		If cancel is clicked on save dialog then return do noting.
		if(savedfile == null)	return;
//		Else set saved file to the current file.
		else{
			file = savedfile;
				
//			Add the previous string into the stack.
			undoStack.add(prevString);
//			Now current text in textArea is the new SavePoint.
//			So set content of textArea to prevString.
			prevString = textArea.getText();
		}
		
		try (PrintWriter pw = new PrintWriter(file)) {
			pw.print(textArea.getText());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
//		File is saved.
		hasUpdateSaved = true;
		
//		Set the valid title.
		SetValidTitle();
	}
	
	
	
	/**
	 * Print the content of file as a pdf.
	 * @deprecated Incomplete
	 */
	public void Func_File_Print()
	{
		
	}
	
	
	
	/**
	 * Handles Exit Button.
	 * <br>
	 * If file is unsaved then opens a warning dialog box.
	 */
	public void Func_File_Exit()
	{
		if(!hasUpdateSaved)
		{
//			Alert Box.
			Alert alert = SavingAlert();
			
//			Getting the ButtonType Pressed.
			ButtonType type = alert.showAndWait().get();
			
//			if the ButtonType is null of cross is pressed or Cancel button is pressed then return do noting.
			if(type == null || type == ButtonType.CANCEL)		return;
			
//			If the button type has data yes which is for Save. then save file.
			if(type.getButtonData() == ButtonData.YES) {
				Func_File_Save();
				
				if(!hasUpdateSaved)	return;
			}
		}
		
		stage.close();
	}
	
	
	
	public void Func_Edit_Cut()
	{
		textArea.cut();
		
	}
	
	public void Edit_Copy()
	{
		textArea.copy();
	}
	
	public void Edit_Paste()
	{
		Clipboard clipboard = Clipboard.getSystemClipboard();
		
		if(!clipboard.hasContent(DataFormat.PLAIN_TEXT))
		{
			return;
		}
		
		String string = clipboard.getString();
		textArea.insertText(textArea.getCaretPosition(), string);
	}
	
	public void Edit_Delete()
	{
		textArea.deleteText(textArea.getSelection());
	}
	
	
	public void Edit_ReplaceAll()
	{
		replaceAllPane.setVisible(true);
	}
	
	public void ReplaceAll()
	{
		if(replaceTextField.getText().isEmpty())	return;
		
		textArea.setText(textArea.getText().replace(replaceTextField.getText(), replaceWithField.getText()));
		replaceAllPane.setVisible(false);
	}
	
	public void Edit_GotoLine()
	{
		gotoPaneBox.setVisible(true);
		MoveCaret();
	}
	
	public void MoveCaret()
	{
		String gotoString = gotoLineField.getText();
		if(gotoString.isEmpty())	return;
		
		int pos = row;
		try {
			pos = Integer.parseInt(gotoString);
		}catch (NumberFormatException e) {
			System.out.println("Not a Number. Invalid Caret Position");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		String textString = textArea.getText();
		int cnt = 0;
		for(int i=0; i<textString.length(); i++)
		{
			if(textString.charAt(i) == '\n')	cnt++;
			if(cnt == pos) {
				pos = i;
				break;
			}
		}
		
		if(cnt < pos)
		{
			pos = GetMaxCaretPos();
		}
		
		textArea.positionCaret(pos);
		gotoPaneBox.setVisible(false);
	}
	
	public int GetMaxCaretPos()
	{
		int count = 0;
		String textString = textArea.getText();
		for(int i = 0; i< textString.length(); i++)
		{
			if(textString.charAt(i) == '\n')	count++;
		}
		return count;
	}
	
	public void Edit_SelectAll()
	{
		textArea.selectAll();
	}
	
	
	public void Edit_TimeDate()
	{
		LocalDateTime dt = LocalDateTime.now();
		textArea.appendText(DateTimeFormatter.ofPattern("dd/MMM/yyyy  HH:mm:ss").format(dt));
	}
	
	public void Edit_Undo()
	{
		if(undoStack.isEmpty())
		{
			textArea.setText(intialContentString);
		}
		else {
			String textString = undoStack.pop();
			textArea.setText(textString);
		}
	}
	
	public void View_ZoomIn()
	{
		double font = textArea.getFont().getSize();
		font = Math.min(font+1, MAX_FONT_SIZE);
		textArea.setStyle("-fx-font-size : "+font);
	}
	
	public void View_ZoomOut()
	{
		double font = textArea.getFont().getSize();
		font = Math.max(font-1, MIN_FONT_SIZE);
				textArea.setStyle("-fx-font-size : "+font);
	}
	
	public void View_ZoomReset()
	{
		textArea.setStyle("-fx-font-size : "+DEFAULT_FONT_SIZE);
	}
}
