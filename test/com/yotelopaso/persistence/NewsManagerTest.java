package com.yotelopaso.persistence;

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
	// HACER EL ABM
	@Test
	public void testABM() {
	//dar de alta una noticia
	News nuevaNew = new News();
	//los campos obligatorios existen
	nuevaNew.setId(3L);
	nuevaNew.setTitle("Nueva Noticia");  
	nuevaNew.setContent("Esta es el contenido de la nueva noticia creada");
	nm.save(nuevaNew);
	assertTrue(nuevaNew.getTitle()!=null);
	assertTrue(nuevaNew.getContent()!=null);
	assertTrue(nuevaNew.getId()!= null);
	//modificar noticia y verificar que haya cambiado
	nuevaNew.setContent("Nuevo contenido");
	nm.save(nuevaNew);
	assertTrue(nuevaNew.getContent()=="Nuevo contenido");
	//eliminar una noticia y al buscarla que no exista
	nm.delete(3L);
	assertTrue(nm.getById(3L)== null);
	
	}
}
