package com.yotelopaso.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	public static final String MONTHLY = "MONTHLY";
	public static final String WEEKLY = "WEEKLY";
	public static final String DAILY = "DAILY";
	
	public static String dateFormat(Date date) {
		return new SimpleDateFormat("dd-MM-YYYY HH:mm").format(date);
		
	}
	
	public static Date getStartDate(String type) {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		switch (type) {
		case MONTHLY:
			c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
			break;
		case WEEKLY:
			c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
			break;
		case DAILY:
			c.set(Calendar.HOUR_OF_DAY, c.getActualMinimum(Calendar.HOUR_OF_DAY));
			break;
		}
		return c.getTime();
	}
	
	public static Date getEndDate(String type) {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		switch (type) {
		case MONTHLY:
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			break;
		case WEEKLY:
			c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			break;
		case DAILY:
			c.set(Calendar.HOUR_OF_DAY, c.getActualMaximum(Calendar.HOUR_OF_DAY));
			break;
		}
		return c.getTime();
	}

}
