package com.yotelopaso.persistence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.yotelopaso.domain.Subject;
import com.yotelopaso.persistence.SubjectManager;
import com.yotelopaso.utils.DataInitializer;

public class SubjectManagerTest {
	
	SubjectManager sm = new SubjectManager();
	
	@Before
	public void setUp() {
		DataInitializer.populateTables();
	}

	@Test
	public void test() {
		List<Subject> l = sm.getByProperty("year", 2);
		assertTrue("l no es nulo", !l.isEmpty());
		
	}
	
	// VALOR DEL ANO 1-5
	@Test
	public void test1al5() {
	List<Subject> l = sm.filterByCareer("Ingeniería en Sistemas");
	for (Subject s : l) {
		assertTrue(s.getYear()>=1);
		assertTrue(s.getYear()<=5);
	}
	}
	
	
	@Test
	public void otherTest() {
		sm.filterByCareerAndYear("Ingeniería en Sistemas", 3);
		Subject s = sm.getByProperty("name", "Comunicaciones").get(0);
		assertNotNull(s);
		//verificar que comunicaciones sea la que busco
		assertTrue(s.getName()== "Comunicaciones");		
	}

}
