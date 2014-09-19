package com.example.persistence;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.filter.Compare;

public class DataManager<T> {
	
	public static final String PERSISTENCE_UNIT = "vaadintest";
	private Class<T> clazz;
	private JPAContainer<T> container;
	
	public DataManager(Class<T> cls) {
		clazz = cls;
		container =  JPAContainerFactory.make(clazz, PERSISTENCE_UNIT);
	}
	
	public void save(T entity) {
		container.addEntity(entity);
	}
	
	public void delete(Object entity) {
		//container.removeItem(itemId);
	}
	
	public <TN extends Number> T getById(TN id) {
		Filter filter = new Compare.Equal("id", id);
		container.addContainerFilter(filter);
		Object itemId = container.getIdByIndex(0);
		T entity = container.getItem(itemId).getEntity();
		container.removeContainerFilter(filter);
		return entity;
	}

	public JPAContainer<T> getContainer() {
		return container;
	}

	public void setContainer(JPAContainer<T> container) {
		this.container = container;
	}

}
