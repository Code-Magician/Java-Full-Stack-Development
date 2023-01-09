package application.TreeView;

import java.net.URL;
import java.util.ResourceBundle;

import javax.security.sasl.SaslException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class TreeViewController implements Initializable{
	@FXML private TreeView treeView;
	@FXML private Label label;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TreeItem<String> rootTreeItem = new TreeItem<>("Files");
		
		TreeItem<String> branchTreeItem1 = new TreeItem<String>("Pictures");
		TreeItem<String> branchTreeItem2 = new TreeItem<String>("Videos");
		TreeItem<String> branchTreeItem3 = new TreeItem<String>("Music");
		
		TreeItem<String> leafItem1 = new TreeItem<String>("P1");
		TreeItem<String> leafItem2 = new TreeItem<String>("P2");
		TreeItem<String> leafItem3 = new TreeItem<String>("L1");
		TreeItem<String> leafItem4 = new TreeItem<String>("L2");
		TreeItem<String> leafItem5 = new TreeItem<String>("M1");
		TreeItem<String> leafItem6 = new TreeItem<String>("M2");
		
		branchTreeItem1.getChildren().addAll(leafItem1, leafItem2);
		branchTreeItem2.getChildren().addAll(leafItem3, leafItem4);
		branchTreeItem1.getChildren().addAll(leafItem5, leafItem6);
		rootTreeItem.getChildren().addAll(branchTreeItem1, branchTreeItem2, branchTreeItem3);
		
		treeView.setRoot(rootTreeItem);
	}
	
	public void SelectedItem()
	{
		TreeItem<String> selectedItem = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
		
		if(selectedItem != null)
		{
			label.setText(selectedItem.getValue());
		}
		else 
		{
			label.setText("Null Item");
		}
	}
}
