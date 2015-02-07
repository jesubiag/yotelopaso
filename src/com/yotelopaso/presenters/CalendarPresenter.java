package com.yotelopaso.presenters;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.util.BeanItem;
import com.yotelopaso.components.EventWindow;
import com.yotelopaso.domain.User;
import com.yotelopaso.domain.UserCalendarEvent;
import com.yotelopaso.persistence.CalendarManager;
import com.yotelopaso.persistence.UserManager;
import com.yotelopaso.views.CalendarView;

public class CalendarPresenter extends AbstractHomePresenter<CalendarView> 
implements CalendarView.CalendarViewListener {
	
	private CalendarView view;
	private CalendarManager service;
	private UserManager userService;
	private User currentUser;
	private UserCalendarEvent currentEvent;
	private EventWindow window;
	
	public CalendarPresenter(CalendarView view, CalendarManager service, UserManager userService) {
		super(view);
		this.view = view;
		this.service = service;
		this.userService = userService;
		
		view.addListener(this);
	}
	
	@Override
	public void buttonClick(String caption, BeanItem<UserCalendarEvent> item) {
		if (currentUser == null) currentUser = userService.getCurrentUser();
		panelButtonClick(caption);
		switch (caption) {
		case "Nuevo evento":
			view.addEventWindow(null, currentUser.getId(), currentUser.getCareer().getId());
			break;
		case "Guardar":
			if ( window.requiredFieldsAreValid() ) {
				service.save(item.getBean());
				initCalendar();
				view.eventAddedSuccesfully();
			} else {
				view.notificateFieldsError();
			}
			break;
		case "Cancelar":
			view.closeCurrentWindow();
			break;
		case "Eliminar":
			service.delete(currentEvent);
			initCalendar();
			view.eventRemovedSuccessfully();
			break;
		}
	}
	
	@Override
	public void eventClick(UserCalendarEvent event) {
		currentEvent = event;
		if (currentUser == null) currentUser = userService.getCurrentUser();
		if ( currentUser.getId().equals( currentEvent.getAuthorId() ) ) {
			view.addEventWindow(currentEvent, currentUser.getId(), currentUser.getCareer().getId());
		} else {
			view.showEventWindow(currentEvent);
		}
		
	}
	
	@Override
	public void valueChange(String viewType) {
		view.setCalendarView(viewType);
	}
	
	@Override
	public void initCalendar() {
		if (currentUser == null) currentUser = userService.getCurrentUser();
		Set<UserCalendarEvent> events = new HashSet<UserCalendarEvent>();
		JPAContainer<UserCalendarEvent> c = service.getCalendarEvents(
				currentUser.getSubscriptedSubjects(), currentUser.getId());
		Collection<Object> ids = c.getItemIds();
		for (Object o : ids) {
			events.add(c.getItem(o).getEntity());
		}
		view.initCalendarContainer(events);
	}

	public EventWindow getWindow() {
		return window;
	}

	public void setWindow(EventWindow window) {
		this.window = window;
	}
	
}
