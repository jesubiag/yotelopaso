package com.yotelopaso.persistence;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import com.yotelopaso.domain.UserCalendarEvent;
import com.yotelopaso.utils.DataInitializer;

public class UserCalendarEventTest {
	
	CalendarManager mngr = new CalendarManager();
	
	@Before
	public void setUp() {
		DataInitializer.populateTables();
	}
	
	@Test
	public void testSaveGetDelete() {
		UserCalendarEvent event = new UserCalendarEvent(123456D, 42, "Soy el caption", 
				"Soy la description\n Y salto de linea", new GregorianCalendar().getTime());
		event.setId(1L);
		mngr.save(event);
		assertTrue("Evento guardado correctamente", mngr.getById(1L).getSubjectId() == 42);
		mngr.delete(1L);
		assertTrue("Evento borrado correctamente", mngr.getById(1L) == null);
	}

	// verificar fechas
	@Test
	public void testfechas() {
		UserCalendarEvent event = new UserCalendarEvent(123456D, 42, "Soy el caption", 
				"Soy la description\n Y salto de linea", new GregorianCalendar().getTime());
		//event.getStart();
		//event.getEnd();
		assertTrue(event.getStart().before(event.getEnd()));
	}
	
}
