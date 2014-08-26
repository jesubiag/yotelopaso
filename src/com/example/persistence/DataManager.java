package com.example.persistence;

import com.vaadin.addon.jpacontainer.JPAContainer;

public class DataManager {
	
	public static final String PERSISTENCE_UNIT = "vaadintest";
	
	public static <T> void save(JPAContainer<T> container, T entity) {
		
		container.addEntity(entity);
		
	}
	
	public static void delete(JPAContainer<Object> container, Object entity) {
		//container.removeItem(itemId);
	}
	
	

}
