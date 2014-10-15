package com.example.persistence;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.yotelopaso.domain.News;
import com.yotelopaso.persistence.NewsManager;
import com.yotelopaso.utils.DataInitializer;

public class NewsManagerTest {
	
	NewsManager nm = new NewsManager();
	
	@Before
	public void setUp() {
		DataInitializer.populateTables();
	}

	@Test
	// news.subject.name
	public void testExistenciaNoticia() {
		List<News> l = nm.getByProperty("subject.name", "Simulacion");
		assertTrue(!l.isEmpty());
	}
	@Test
	public void testNoticiaVacia() {
		List<News> l = nm.getAll();
		Integer flag=0;
		for (News n : l) {
			if (n.getContent() == "" || n.getContent() == null )  {
				flag=1;
			}
		}
		assertTrue(flag==0);
	}

}
