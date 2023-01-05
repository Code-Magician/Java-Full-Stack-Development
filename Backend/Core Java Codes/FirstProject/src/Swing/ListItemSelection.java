package Swing;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class ListItemSelectionApp extends JFrame implements ListSelectionListener
{
	JTable jTable;
	
	public ListItemSelectionApp() {
		
		String[][] rowStrings = {
				{"5212", "Priyansh", "Bareilly"},
				{"5178", "Vipul", "Delhi"},
				{"5251", "Ritanshu", "Ranchi"}
		};
		String[] columnStrings = {"UID", "NAME", "CITY"};
		
		jTable = new JTable(rowStrings, columnStrings);
		jTable.setBounds(10, 10, 500, 200);
		
		ListSelectionModel selectionModel = jTable.getSelectionModel();
		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selectionModel.addListSelectionListener(this);
		
		JScrollPane scrollPane = new JScrollPane(jTable);
		
		this.add(scrollPane);
		
		this.setSize(400, 800);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		int rows[] = jTable.getSelectedRows();
		int columns[] = jTable.getSelectedColumns();
		
		String dataString = "";
		for(int i = 0; i < rows.length; i++)
		{
			for(int j = 0; j < columns.length; j++)
			{
				dataString = (String)jTable.getValueAt(rows[i], columns[i]);
			}
		}
		
		System.out.println("Table ITem Selected : " + dataString);
	}	
}

public class ListItemSelection {
	public static void main(String[] args) {
		new ListItemSelectionApp();
	}
}
