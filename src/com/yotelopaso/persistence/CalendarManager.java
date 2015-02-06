package com.yotelopaso.persistence;

import java.util.ArrayList;
import java.util.List;
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
		container.removeAllContainerFilters();
		container.refresh();
		String propertyId1 = "subjectId";
		String propertyId2 = "publicEvent";
		String propertyId3 = "authorId";
		
		List<Filter> filterList = new ArrayList<Filter>();
		Subject[] array =  subjects.toArray(new Subject[subjects.size()]);
		filterList.add( new Compare.Equal(propertyId3, userId) );
		for (int i = 0; i < array.length; i++) {
			Filter f1 = new Compare.Equal(propertyId1, array[i].getId());
			Filter f2 = new Compare.Equal(propertyId2, true);
			filterList.add(new And(f1, f2));
		}
		Filter[] filters = filterList.toArray(new Filter[filterList.size()]);
		Filter filter = new Or(filters);
		container.addContainerFilter(filter);
		return container;
	}
}
