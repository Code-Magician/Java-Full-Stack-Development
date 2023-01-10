package application;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Stack;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller implements Initializable {
	Double MIN_FONT_SIZE = 5.0;
	Double DEFAULT_FONT_SIZE = 12.0;
	Double MAX_FONT_SIZE = 50.0;
	
	@FXML private TextArea textArea;
	@FXML private Label locationLabel;
	
	
	private Scene scene;
	private Stage stage;
	
	private File file;
	
	boolean hasUpdateSaved = true;
	
	Stack<String> undoStack;
	String intialContentString = "";
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		undoStack = new Stack<String>();
		
		textArea.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				if(hasUpdateSaved)
				{
					hasUpdateSaved = false;
					SetValidTitle();
				}
				else if(file == null && textArea.getText().isEmpty())
				{
					hasUpdateSaved = true;
					SetValidTitle();
				}
				
				
				SetCaretPosition();
//				System.out.println(hasUpdateSaved);
			}
		});
	}
	
	public void SetCaretPosition() {
		int i =  textArea.getCaretPosition();
		System.out.println(i);
	}
	
	
	public void SetSceneNStage(Scene scene)
	{
		this.scene = scene;
		stage = (Stage)scene.getWindow();
		System.out.println(stage.toString());
	}
	
	public void SetValidTitle()
	{
		if(hasUpdateSaved)
		{
			stage.setTitle(file!=null?file.getName():"Untitled");
		}
		else {
			if(file == null)	stage.setTitle("*Untitled");
			else 				stage.setTitle("*"+file.getName());
		}
	}
	
	public void TextUpdated()
	{
		hasUpdateSaved = false;
		SetValidTitle();
	}
	
	
	public void Func_File_New() {
		file = null;
		hasUpdateSaved = false;
		SetValidTitle();
		
		textArea.clear();
	}
	
	public void Func_File_Open()
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open File");
		
		file = fileChooser.showOpenDialog(stage);
		
		if(file != null)
			textArea.clear();
		
		try {
			FileReader fr = new FileReader(file.getAbsoluteFile());
			BufferedReader br = new BufferedReader(fr);
			
			String lineString;
			while((lineString = br.readLine()) != null)
			{
				String contentString = textArea.getText();
				if(contentString.isEmpty())		contentString = lineString;
				else	contentString += ('\n' + lineString);
				textArea.setText(contentString);
			}
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		if(file != null) {
			intialContentString = textArea.getText();
			hasUpdateSaved = true;
			SetValidTitle();
		}
	}
	
	public void Func_File_Save()
	{	
		if(file == null)
		{
			Func_File_SaveAs();
			return;
		}
		
		undoStack.add(textArea.getText());
		try (PrintWriter pw = new PrintWriter(file)) {
//			FileWriter fw = new FileWriter(file.getAbsoluteFile());
//			BufferedWriter bw = new BufferedWriter(fw);
//			
//			bw.write(textArea.getText());
//			bw.flush();		bw.close();
			
			pw.print(textArea.getText());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		hasUpdateSaved = true;
		SetValidTitle();
	}
	
	public void Func_File_SaveAs()
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save File");
		
		file = fileChooser.showSaveDialog(stage);
		if(file!=null)	undoStack.add(textArea.getText());
		
		try (PrintWriter pw = new PrintWriter(file)) {
//			FileWriter fw = new FileWriter(file.getAbsoluteFile());
//			BufferedWriter bw = new BufferedWriter(fw);
//			
//			bw.write(textArea.getText());
//			bw.flush();		bw.close();
			
			pw.print(textArea.getText());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		hasUpdateSaved = true;
		SetValidTitle();
	}
	
	public void Func_File_Print()
	{
		
	}
	
	public void Func_File_Exit()
	{
		if(!hasUpdateSaved)
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Notepad");
			alert.setHeaderText("Do you want to save Changes to "+ (file!=null?file.getName():"Untitled") + "?");
			
			ButtonType saveButtonType = new ButtonType("Save", ButtonData.YES);
			ButtonType dSaveButtonType = new ButtonType("Don't Save", ButtonData.NO);
			
			alert.getButtonTypes().remove(0);
			alert.getButtonTypes().addAll(dSaveButtonType, saveButtonType);
			
			ButtonType type = alert.showAndWait().get();
			
			if(type == saveButtonType)
			{
				System.out.println("Saving");
				Func_File_Save();
			}
			else if(type == ButtonType.CANCEL)
			{
				return;
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
		textArea.setText(textArea.getText().replace(textArea.getSelectedText(), "XYZ"));
	}
	
	public void Edit_GotoLine()
	{
		textArea.positionCaret(0);
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
