package Swing;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

class IPApplication extends JFrame implements ActionListener
{
	JTextArea jTextArea;
	JLabel jLabel;
	JButton jButton;
	
	public IPApplication() {
		jTextArea = new JTextArea("Enter Site URL here.");
		jLabel = new JLabel("IP will be Displayed Here.");
		jButton = new JButton("Find IP");
		
		jTextArea.setBounds(200, 150, 200, 50);
		jLabel.setBounds(200, 200, 200, 50);
		jButton.setBounds(200, 250, 200, 50);
		
		jButton.addActionListener(this);
		
		add(jTextArea);
		add(jLabel);
		add(jButton);
		
		this.setSize(400, 400);
		this.setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String urlString = jTextArea.getText().trim();
		
		try {
			String ipString = java.net.InetAddress.getByName(urlString).getHostAddress();
			jLabel.setText("IP Address is : " + ipString);
		}catch (UnknownHostException unknownHostException) {
			jLabel.setText("URL is Incorrect :" + unknownHostException.getMessage());
		}
		catch (Exception e2) {
			jLabel.setText("Something Went Wrong : " + e2.getMessage());
		}
	}
}

public class FindingIPAddress {
	public static void main(String[] args) {
		IPApplication ipApplication = new IPApplication();
	}
}
