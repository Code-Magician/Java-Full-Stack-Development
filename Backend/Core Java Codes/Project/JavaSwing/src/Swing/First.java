package Swing;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Class Extends JFrame so when we create Object of this class Frame will be created automatically.
*/
class SimpleCheckBox extends JFrame 
{
	public SimpleCheckBox() {
		// Text area to display the text.
		TextArea textArea = new TextArea("Select Your Option.");
		textArea.setBounds(200, 100, 400, 50);
		
		// Check box to record input.
		JCheckBox checkBox = new JCheckBox("Are You Gay?");
		checkBox.setBounds(200, 200, 400, 50);
		
		// Button to display the result in textArea based on check box input.
		JButton button = new JButton(new ImageIcon("E:\\IMG.png"));
		button.setBounds(200, 300, 400, 50);
		
		// Added Action Listener to button to get notified 
		// when the button is clicked and display respective result.
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean selected = checkBox.isSelected();
				
				if(selected)
				{
					textArea.setText("Fuck You!!");
				}
				else {
					textArea.setText("Sigma Male");
				}
				
			}
		});
		
		// Adding textArea, checkBox and button to the Frame.
		this.add(textArea);
		this.add(checkBox);
		this.add(button);
		
		// Setting Properties of Frame.
		this.setSize(1000, 1000);
		this.setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

public class First {
	public static void main(String[] args) {
		SimpleCheckBox box = new SimpleCheckBox();
	}
}
