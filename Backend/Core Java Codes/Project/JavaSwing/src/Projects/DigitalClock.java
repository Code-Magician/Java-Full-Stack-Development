package Projects;

import java.awt.Color;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

class DateTime{
	public static String getDateString() {
		LocalDate localDate = LocalDate.now();
		String dateString = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
		return dateString;
	}
	
	public static String getTimeString() {
		Calendar calendar = Calendar.getInstance();
		
		int hrs;
		
		hrs = calendar.get(Calendar.HOUR_OF_DAY);
		String amPmString = "";
		if(hrs > 12)
			amPmString = "PM";
		else 
			amPmString = "AM";
		
		Date time = calendar.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss ");
		String timeString = simpleDateFormat.format(time) + amPmString;
		
		return timeString;
	}
}

class DigitalClockApp extends JFrame implements Runnable {
	JLabel timeLabel, dateLabel;
	
	public DigitalClockApp() {
		super("Digital Clock");
		
		timeLabel = new JLabel(DateTime.getTimeString());
		dateLabel = new JLabel(DateTime.getDateString());
		
		timeLabel.setBounds(10, 10, 100, 30);
		dateLabel.setBounds(10, 60, 100, 30);
		
		timeLabel.setHorizontalAlignment(JLabel.CENTER);
		dateLabel.setHorizontalAlignment(JLabel.CENTER);
		
		timeLabel.setBackground(Color.GRAY);
		dateLabel.setBackground(Color.gray);
		
		timeLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		dateLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		add(timeLabel);
		add(dateLabel);
		
		this.setSize(300, 100);
		setBackground(Color.black);
		setVisible(true);
		setLayout(new GridLayout(1, 2, 20, 20));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void run() {
		try {
			while(true)
			{
				timeLabel.setText(DateTime.getTimeString());
				dateLabel.setText(DateTime.getDateString());
				
				Thread.sleep(1000);
			}
		}
		catch (Exception e) {
			System.out.println("Something went wrong : " + e.getMessage());
		}
	}
}

public class DigitalClock {
	public static void main(String[] args) {
		new Thread(new DigitalClockApp()).start();;
	}
}
