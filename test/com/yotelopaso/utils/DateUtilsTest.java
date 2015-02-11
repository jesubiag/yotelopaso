package com.yotelopaso.utils;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;

public class DateUtilsTest {
	
	//@Test
	public void testWeekDays() {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		System.out.println("1 - Primer dia es: " + c.getTime());
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		System.out.println("2 - Ultimo dia es: " + c.getTime());
	}
	
	//@Test
	public void testMonthDays() {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		System.out.println("3 - Primer dia es: " + c.getTime());
		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.DAY_OF_MONTH, c2.getActualMaximum(Calendar.DAY_OF_MONTH));
		System.out.println("4 - Ultimo dia es: " + c2.getTime());
	}
	
	//@Test
	public void testGetStartDateMonthly() {
		DateUtils.getStartDate(DateUtils.MONTHLY);
		DateUtils.getEndDate(DateUtils.MONTHLY);
	}
	
	@Test
	public void testdash() { 
		char[] chars = "hola pepe".toCharArray();
		int length = chars.length;
		System.out.println(length);
		for (int i=0; i < length; i++) {
			char c = chars[i];
			System.out.println(i + " : " + c);
			}
		assertTrue(true);
	}

}
