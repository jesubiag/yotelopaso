package com.yotelopaso.persistence;

import java.util.Set;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Or;
import com.yotelopaso.domain.Subject;
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
	
	public JPAContainer<UserCalendarEvent> getEventsFromSubscription(Set<Subject> subjects, 
			Double userId ) {
		String property1 = "subjectId";
		String property2 = "publicEvent";
		String property3 = "authorId";
		for (Subject s : subjects) {
			Filter filter1 = new And( new Compare.Equal(property1, s.getId()),
					new Compare.Equal(property2, true));
			Filter filter2 = new Compare.Equal(property3, userId ); 
			Filter filter = new Or(filter1, filter2);
			container.addContainerFilter(filter);
		}
		return container;
	}
}
