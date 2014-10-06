package com.example.persistence;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.example.domain.Subject;
import com.example.utils.DataInitializer;

public class SubjectManagerTest {
	
	SubjectManager sm = new SubjectManager();

	@Test
	public void test() {
		DataInitializer.populateTables();
		List<Subject> l = sm.getByProperty("year", 2);
		assertTrue("l no es nulo", !l.isEmpty());
	}

}
