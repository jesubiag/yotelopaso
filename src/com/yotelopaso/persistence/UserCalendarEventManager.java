package com.yotelopaso.persistence;

import java.util.List;

import com.yotelopaso.domain.UserCalendarEvent;

public class UserCalendarEventManager extends DataManager<UserCalendarEvent>{
	
	private UserManager userManager = new UserManager();
	
	public UserCalendarEventManager() {
		super(UserCalendarEvent.class);
	}
	
	@Override
	public Long save(UserCalendarEvent event) {
		Long id = (Long) super.save(event);
		event.setId(id);
		userManager.addUserEvent(event);
		return id;
	}
	
	public List<UserCalendarEvent> getCurrentUserEvents() {
		return userManager.getCurrentUserEvents();
	}
	
}
