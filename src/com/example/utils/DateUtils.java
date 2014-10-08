package com.example.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static String dateFormat(Date date) {
		return new SimpleDateFormat("dd-MM-YYYY HH:mm").format(date);
		
	}

}
