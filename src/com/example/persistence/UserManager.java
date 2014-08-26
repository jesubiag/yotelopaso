package com.example.persistence;

import java.util.Collection;

import com.example.domain.User;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.filter.Compare;

public class UserManager extends DataManager {
	
	static JPAContainer<User> users = JPAContainerFactory.make(User.class, PERSISTENCE_UNIT);
	
	public static void save(User user) {
		
		DataManager.save(users, user);
		
	}
	
	public static boolean isRegistered(Double id) {
		
		//TODO: implementación fea, tratar de mejorar
		Filter filter = new Compare.Equal("id", id);
		users.addContainerFilter(filter);
		Collection<Object> result = users.getItemIds();
		users.removeAllContainerFilters();
		if (result.isEmpty() || result == null) {
			return false;
		} else {
			return true;
		}
		
	}
	
	public static User getById(Double id) {
		Filter filter = new Compare.Equal("id", id);
		users.addContainerFilter(filter);
		Object itemId = users.getIdByIndex(0);
		return users.getItem(itemId).getEntity();
	}

}
