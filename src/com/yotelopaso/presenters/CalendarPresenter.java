package com.yotelopaso.presenters;

import java.util.Set;

import com.vaadin.data.util.BeanItem;
import com.yotelopaso.domain.User;
import com.yotelopaso.domain.UserCalendarEvent;
import com.yotelopaso.persistence.CalendarManager;
import com.yotelopaso.persistence.UserManager;
import com.yotelopaso.views.CalendarView;
import com.yotelopaso.views.components.EventWindow;

public class CalendarPresenter extends AbstractHomePresenter<CalendarView> 
implements CalendarView.CalendarViewListener {
	
	private CalendarView view;
	private CalendarManager service;
	private UserManager userService = new UserManager();
	private User currentUser;
	private UserCalendarEvent currentEvent;
	private EventWindow window;
	
	public CalendarPresenter(CalendarView view, CalendarManager service) {
		super(view);
		this.view = view;
		this.service = service;
		
		view.addListener(this);
	}
	
	@Override
	public void buttonClick(String caption, BeanItem<UserCalendarEvent> item) {
		if (currentUser == null) currentUser = userService.getCurrentUser();
		panelButtonClick(caption);
		switch (caption) {
		case "Agregar nuevo evento":
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
		case "Borrar":
			service.delete(currentEvent);
			initCalendar();
			view.eventRemovedSuccessfully();
			break;
		}
	}
	
	@Override
	public void eventClick(UserCalendarEvent event) {
		if (currentUser == null) currentUser = userService.getCurrentUser();
		currentEvent = event;
		if ( currentUser.getId().equals( currentEvent.getAuthorId() ) ) {
			view.addEventWindow(currentEvent, currentUser.getId(), currentUser.getCareer().getId());
		} else {
			view.showNotification("Debes ser el autor del evento para poder editarlo");
		}
		
	}
	
	@Override
	public void valueChange(String viewType) {
		view.setCalendarView(viewType);
	}
	
	@Override
	public void initCalendar() {
		Set<UserCalendarEvent> events = service.getCurrentUserEvents();
		view.initCalendarContainer(events);
	}

	public EventWindow getWindow() {
		return window;
	}

	public void setWindow(EventWindow window) {
		this.window = window;
	}
	
}
