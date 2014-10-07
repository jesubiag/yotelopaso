package com.example.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.tomcat.jni.User;
import org.junit.Test;

import com.example.domain.Career;
import com.example.domain.News;
import com.vaadin.addon.jpacontainer.JPAContainer;

public class SomeTest {

//	@Test
	public void test() {
		Career c = new Career(1, "Hola");
		CareerManager cm = new CareerManager();
		JPAContainer<Career> co = cm.getContainer();
		co.addEntity(c);
		assertTrue(1 == 1);
	}
	
	//@Test
	public void newsTest() {
		NewsManager nm = new NewsManager();
		List<News> ln = nm.getAllFromCareer("Ingeniería en Sistemas");
		assertTrue(ln.isEmpty());
	}
	
	@Test
	public void namesTest() {
		Class<User> c = User.class;
		String cn = c.getSimpleName();
		assertEquals("User", cn);
	}

}
