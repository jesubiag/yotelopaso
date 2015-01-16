package com.yotelopaso.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.vaadin.addon.jpacontainer.EntityProvider;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;

/**Administrador abstracto de entidades genérico.
 * Se hereda definiendo el tipo de clase a administrar.
 *
 * @param <T> Nombre de la clase a administrar
 */
public class DataManager<T> {
	
	public static final String PERSISTENCE_UNIT = "vaadintest";
	protected Class<T> clazz;
	protected String tableName;
	protected JPAContainer<T> container;
	protected EntityProvider<T> ep;
	protected EntityManager em;
	
	public DataManager(Class<T> cls) {
		clazz = cls;
		tableName = clazz.getSimpleName();
		container =  JPAContainerFactory.make(clazz, PERSISTENCE_UNIT);
		ep = container.getEntityProvider();
		em = ep.getEntityManager();
	}
	
	/**
	 * 
	 * @param entity
	 * @return the Object id of the persisted entity
	 */
	public Object save(T entity) {
		return container.addEntity(entity);
	}
	
	public <TN extends Number> void delete(TN id) {
		T entity = em.merge(getById(id));
		//container.removeItem(id);
		container.removeItem(id);
		em.remove(entity);
	}
	
	@SuppressWarnings("unchecked")
	public <TP> List<T> getByProperty(String entityProperty, TP property) {
		String jpql = "SELECT e FROM " + tableName 
				+ " e WHERE e." + entityProperty 
				+ "= :property";
		Query query = em.createQuery(jpql);
		query.setParameter("property", property);
		return query.getResultList();
	}
	
	public <TN extends Number> T getById(TN id) {
		//return ep.getEntity(container, id);
		return em.find(clazz, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		String jpql = "SELECT e FROM " + tableName + " e";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	public JPAContainer<T> getContainer() {
		return container;
	}

	public void setContainer(JPAContainer<T> container) {
		this.container = container;
	}

}
