package Swing;

import java.awt.ActiveEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

class CountApp extends JFrame implements ActionListener{
	JLabel l1, l2;
	JTextArea tArea;
	JButton countButton;
	
	public CountApp() {
		l1 = new JLabel("Characters : 0");
		l2 = new JLabel("Words : 0");
		tArea = new JTextArea();
		countButton = new JButton("Count");
		
		l1.setBounds(50, 50, 200, 50);
		l2.setBounds(300, 50, 200, 50);
		
		tArea.setBounds(137, 75, 137, 137);
		
		countButton.setBounds(225, 300, 100, 50);
		countButton.addActionListener(this);
		
		this.add(l1);
		this.add(l2);
		this.add(tArea);
		this.add(countButton);
		
		this.setSize(500, 500);
		this.setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String contentString = tArea.getText();
		
		l1.setText("Characters : "+ contentString.length());
		l2.setText("Words : " + contentString.split("\\s").length);
	}
}

public class CountCharactersAndWords {
	public static void main(String[] args) {
		CountApp countApp = new CountApp();
	}
}
