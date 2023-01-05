package Swing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

class ColorChooserApp extends JFrame implements ActionListener
{
	JButton jButton;
	JTextArea jTextArea;
	
	public ColorChooserApp() {
		jButton = new JButton("Pad Color");
		jTextArea = new JTextArea("Background Of This Will Be Colored.");
		
		jTextArea.setBounds(10, 10, 250, 250);
		jTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		jButton.setBounds(10, 500, 100, 35);
		jButton.addActionListener(this);
		
		add(jButton);	add(jTextArea);
		setSize(1000, 1500);
		setVisible(true);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Color c = JColorChooser.showDialog(this, "Choose Color", Color.GREEN);
		jTextArea.setBackground(c);
		getContentPane().setBackground(c);
	}
}

public class ColorChooser {
	public static void main(String[] args) {
		new ColorChooserApp();
	}
}
