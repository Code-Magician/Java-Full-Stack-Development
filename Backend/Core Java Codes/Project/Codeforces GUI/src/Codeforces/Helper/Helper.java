package Codeforces.Helper;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Helper {
	public static String defaultString = "-----";
	
	public static String GetDateTimeFromUnixFormat(Long unixTime)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss z");
		
		return
				Instant.ofEpochSecond(unixTime)
				.atZone(ZoneId.of("GMT-4"))
				.format(formatter);
	}
}
