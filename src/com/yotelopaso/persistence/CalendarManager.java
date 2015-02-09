package com.yotelopaso.persistence;

import java.util.ArrayList;
import java.util.Date;
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
		return (Long) super.save(event);
	}
	
	public void delete(UserCalendarEvent event) {
		super.delete(event.getId());
	}
	
	public JPAContainer<UserCalendarEvent> getEventsFromSubscription(Set<Subject> subjects, 
			Double userId ) {
		container.removeAllContainerFilters();
		container.refresh();
		String propertyId1 = "subjectId";
		String propertyId2 = "publicEvent";
		String propertyId3 = "authorId";
		String propertyId4 = "end";
		
		List<Filter> filterList = new ArrayList<Filter>();
		Subject[] array =  subjects.toArray(new Subject[subjects.size()]);
		filterList.add( new Compare.Equal(propertyId3, userId) );
		for (int i = 0; i < array.length; i++) {
			Filter f1 = new Compare.Equal(propertyId1, array[i].getId());
			Filter f2 = new Compare.Equal(propertyId2, true);
			filterList.add(new And(f1, f2));
		}
		Filter f3 = new Compare.GreaterOrEqual(propertyId4, new Date());
		Filter[] filters = filterList.toArray(new Filter[filterList.size()]);
		Filter f4 = new Or(filters);
		Filter filter = new And(f3, f4);
		container.addContainerFilter(filter);
		return container;
	}
	
	public JPAContainer<UserCalendarEvent> getCalendarEvents(Set<Subject> subjects, 
			Double userId) {
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
