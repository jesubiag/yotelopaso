package com.yotelopaso.persistence;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.yotelopaso.domain.User;
import com.yotelopaso.persistence.NewsManager;
import com.yotelopaso.persistence.UserManager;
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
	
}
