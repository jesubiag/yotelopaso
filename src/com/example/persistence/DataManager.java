package com.example.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.vaadin.addon.jpacontainer.EntityProvider;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.filter.Compare;

public class DataManager<T> {
	
	public static final String PERSISTENCE_UNIT = "vaadintest";
	protected Class<T> clazz;
	protected JPAContainer<T> container;
	protected EntityProvider<T> ep;
	protected EntityManager em;
	
	public DataManager(Class<T> cls) {
		clazz = cls;
		container =  JPAContainerFactory.make(clazz, PERSISTENCE_UNIT);
		ep = container.getEntityProvider();
		em = ep.getEntityManager();
	}
	
	public void save(T entity) {
		container.addEntity(entity);
	}
	
	public void delete(Object entity) {
		//container.removeItem(itemId);
	}
	
	public <TP> T getByProperty(TP property) {
		return ep.getEntity(container, property);
	}
	
	public <TN extends Number> T getById(TN id) {
		return getByProperty(id);
	}
	
	public List<T> getAll() {
		String jpql = "SELECT e FROM :tableName e";
		Query query = em.createNamedQuery(jpql);
		query.setParameter("tableName", clazz.toString());
		//query.setParameter("tableName", clazz.toString().toUpperCase());
		return query.getResultList();
	}

	public JPAContainer<T> getContainer() {
		return container;
	}

	public void setContainer(JPAContainer<T> container) {
		this.container = container;
	}

}
