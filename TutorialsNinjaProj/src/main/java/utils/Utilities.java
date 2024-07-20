package utils;

import java.util.Date;

public class Utilities {

	public static String generateTimeStamp() {
		Date date = new Date();
		return date.toString().replaceAll("[ :]", "_");
	}
}
