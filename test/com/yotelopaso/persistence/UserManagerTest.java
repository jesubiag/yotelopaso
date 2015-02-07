package com.yotelopaso.persistence;

import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.yotelopaso.domain.Subject;
import com.yotelopaso.domain.User;
import com.yotelopaso.utils.DataInitializer;

public class UserManagerTest {

	UserManager um = new UserManager();
	
	@Before
	public void setUp() {
		DataInitializer.populateTables();
	}

	@Test
	public void testExistenciaUsuario() {
		List<User> l = um.getByProperty("name","Jesus");
		assertFalse(!l.isEmpty());
		
	}
	
	@Test
	public void testChangeUserDefaultSubjects() {
		User u = um.getByProperty("email", "jesu772@gmail.com").get(0);
		Set<Subject> s = u.getSubscriptedSubjects();
		s.size();
		System.out.println("\n************ " + s);
		u.removeSubjects(s);
		s = u.getSubscriptedSubjects();
		s.size();
		System.out.println("\n\n************ " + s);
		um.setNewUserDefaultSubjects(u, "Ingenieria Quimica", 2);
		s = u.getSubscriptedSubjects();
		s.size();
		System.out.println("\n\n************ " + s);
	}
	
}
