package Codeforces.Helper;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Helper {
	public static String defaultString = "-----";
	
	public static Double screenWidth = 1920.0, screenHeight = 1080.0;
	
	
	
	public static Double getScreenWidth() {
		return screenWidth;
	}



	public static void setScreenWidth(Double w) {
		screenWidth = w;
	}



	public static Double getScreenHeight() {
		return screenHeight;
	}



	public static void setScreenHeight(Double h) {
		screenHeight = h;
	}



	public static String GetDateTimeFromUnixFormat(Long unixTime)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		
		return
				Instant.ofEpochSecond(unixTime)
				.atZone(ZoneId.of("GMT-4"))
				.format(formatter);
	}
}
