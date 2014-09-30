package com.example.persistence;

import java.util.Collection;

import com.example.domain.User;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.server.VaadinSession;

public class UserManager extends DataManager<User> {
	
	public UserManager() {
		super(User.class);
	}
	
	public boolean isRegistered(Double id) {
		
		//TODO: implementación fea, tratar de mejorar
		Filter filter = new Compare.Equal("id", id);
		JPAContainer<User> c = getContainer();
		c.addContainerFilter(filter);
		Collection<Object> result = c.getItemIds();
		c.removeContainerFilter(filter);
		if (result.isEmpty() || result == null) {
			return false;
		} else {
			return true;
		}
		
	}
	
	public User getCurrentUser() {
		return (User) VaadinSession.getCurrent().getAttribute("currentUser");
	}

}
