package com.yotelopaso.persistence;

import java.util.Set;

import com.yotelopaso.domain.UserCalendarEvent;

public class CalendarManager extends DataManager<UserCalendarEvent>{
	
	private UserManager userManager = new UserManager();
	
	public CalendarManager() {
		super(UserCalendarEvent.class);
	}
	
	@Override
	public Long save(UserCalendarEvent event) {
		String c = event.getEventType().getColor();
		event.setStyleName(c);
		if (event.getCareer() == null) event.setCareer(userManager.getCurrentUser().getCareer());
		Long id = (Long) super.save(event);
		if ( event.getId() == null ) {
			event.setId(id);
			userManager.addUserEvent(event);
		}
		return id;
	}
	
	public void delete(UserCalendarEvent event) {
		super.delete(event.getId());
		userManager.removeUserEvent(event);
	}
	
	public Set<UserCalendarEvent> getCurrentUserEvents() {
		return userManager.getCurrentUserEvents();
	}
	
}
