package Swing;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

class ProgressBarApp extends JFrame{
	JProgressBar jProgressBar;
	
	public ProgressBarApp() {
		jProgressBar = new JProgressBar(0, 1000);
		jProgressBar.setBounds(10, 10, 500, 50);
		jProgressBar.setStringPainted(true);
		jProgressBar.setValue(0);
		
		this.add(jProgressBar);
		this.setSize(500, 500);
		this.setVisible(true);
		this.setLayout(null);
	}
	
	public void Iterate()
	{
		int i = 0;
		while(i<=1000)
		{
			i+=10;
			jProgressBar.setValue(i);
			
			try {
				Thread.sleep(150);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}

public class ProgressBar {
	public static void main(String[] args) {
		new ProgressBarApp().Iterate();
	}
}
