package Swing;

import java.awt.event.*;
import java.nio.channels.NonReadableChannelException;

import javax.swing.*;

class LanguageSelectorApp extends JFrame implements ActionListener
{
	JLabel jLabel;
	JComboBox<String> cBox;
	JButton jButton;
	
	public LanguageSelectorApp() {
		String[] languageStrings = {"C++", "JAVA", "C#", "PHP", "JavaScript"};
		
		jLabel = new JLabel("Programming Language Selected : NaN");
		cBox = new JComboBox<>(languageStrings);
		jButton = new JButton("Select");
		
		jLabel.setBounds(50, 10, 500, 50);
		cBox.setBounds(50, 50, 150, 100);
		jButton.setBounds(250, 50, 100, 50);
		
		jButton.addActionListener(this);
		
		this.add(jLabel);
		this.add(cBox);
		this.add(jButton);
		
		this.setSize(500, 500);
		this.setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		jLabel.setText("Programming Language Selected : " + cBox.getItemAt(cBox.getSelectedIndex()).toString());
	}
}

public class LanguageSelector {
	public static void main(String[] args) {
		new LanguageSelectorApp();
	}
}
