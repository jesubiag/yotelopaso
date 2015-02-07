package com.yotelopaso.views;

import java.util.Set;

import com.vaadin.data.util.BeanItem;
import com.yotelopaso.domain.UserCalendarEvent;

public interface CalendarView extends AbstractHomeView {
	
	public void initCalendarContainer(Set<UserCalendarEvent> events);
	public void addEventWindow(UserCalendarEvent selectedEvent, Double authorId, Integer careerId);
	public void eventAddedSuccesfully();
	public void eventRemovedSuccessfully();
	public void showNotification(String message);
	public void showEventWindow(UserCalendarEvent event);
	public void notificateFieldsError();
	public void closeCurrentWindow();
	public void setCalendarView(String type);
	
	interface CalendarViewListener extends AbstractHomeViewListener {
		public void buttonClick(String caption, BeanItem<UserCalendarEvent> item);
		public void eventClick(UserCalendarEvent event);
		public void valueChange(String viewType);
		public void initCalendar();
	}
	
	public void addListener(CalendarViewListener listener);

}
