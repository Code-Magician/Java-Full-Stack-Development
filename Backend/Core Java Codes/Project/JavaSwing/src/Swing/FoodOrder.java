package Swing;

import java.awt.event.*;
import javax.swing.*;

class FoodOrderApp extends JFrame implements ActionListener, ItemListener{
	JLabel l1;
	JCheckBox f1, f2, f3;
	JButton jButton;
	
	public FoodOrderApp() {
		l1 = new JLabel("Items Selected : NaN");
		f1 = new JCheckBox("Pizza @100");
		f2 = new JCheckBox("Burger @50"); 
		f3 = new JCheckBox("ColdDrink @ 49");
		jButton = new JButton("Order Food");
		
		l1.setBounds(100, 10, 500, 50);
		
		f1.setHorizontalAlignment(JCheckBox.CENTER);
		f2.setHorizontalAlignment(JCheckBox.CENTER);
		f3.setHorizontalAlignment(JCheckBox.CENTER);
		
		f1.setBounds(100, 50, 150, 50);
		f2.setBounds(100, 100, 150, 50);
		f3.setBounds(100, 150, 150, 50);
		
		f1.addItemListener(this);
		f2.addItemListener(this);
		f3.addItemListener(this);
		
		jButton.setHorizontalAlignment(JButton.CENTER);
		jButton.setBounds(100, 200, 150, 50);
		jButton.addActionListener(this);
		
		this.add(l1);
		this.add(f1);
		this.add(f2);
		this.add(f3);
		this.add(jButton);
		
		this.setSize(500, 500);
		this.setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		StringBuilder itemsSelectedString = new StringBuilder("ItemsSelected : ");
		if(f1.isSelected())
		{
			itemsSelectedString.append(f1.getText());
			itemsSelectedString.append(" | ");
		}
		
		if(f2.isSelected())
		{
			itemsSelectedString.append(f2.getText());
			itemsSelectedString.append(" | ");
		}
		
		if(f3.isSelected())
		{
			itemsSelectedString.append(f3.getText());
			itemsSelectedString.append(" | ");
		}
		
		l1.setText(itemsSelectedString.toString());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StringBuilder orderString = new StringBuilder("Ordered Items List\n");
		int totalAmt = 0;
		if(f1.isSelected())
		{
			orderString.append("\n");
			orderString.append(f1.getText());
			
			totalAmt += 100;
		}
		
		if(f2.isSelected())
		{
			orderString.append("\n");
			orderString.append(f2.getText());
			
			totalAmt += 50;
		}
		
		if(f3.isSelected())
		{
			orderString.append("\n");
			orderString.append(f3.getText());
			
			totalAmt += 49;
		}
		
		orderString.append("\n\n ----------- \n Total Amount : ");
		orderString.append(totalAmt);
		
		JOptionPane.showMessageDialog(this, orderString);
	}
}

public class FoodOrder {
	public static void main(String[] args) {
		FoodOrderApp foodOrderApp = new FoodOrderApp();
	}
}
