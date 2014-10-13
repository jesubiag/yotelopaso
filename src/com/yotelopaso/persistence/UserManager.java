package com.yotelopaso.persistence;

import com.vaadin.server.VaadinSession;
import com.yotelopaso.domain.User;

public class UserManager extends DataManager<User> {
	
	public UserManager() {
		super(User.class);
	}
	
	public boolean isRegistered(Double id) {
		
		User user = getById(id);
		if (user == null) {
			return false;
		} else {
			return true;
		}
		
	}
	
	public User getCurrentUser() {
		return (User) VaadinSession.getCurrent().getAttribute("currentUser");
	}
	
	public void setCurrentUser(User currentUser) {
		VaadinSession.getCurrent().setAttribute("currentUser", currentUser);
	}

}
