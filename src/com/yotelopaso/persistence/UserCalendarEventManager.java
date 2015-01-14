package com.yotelopaso.persistence;

import com.vaadin.ui.components.calendar.ContainerEventProvider;
import com.yotelopaso.domain.UserCalendarEvent;

public class UserCalendarEventManager extends DataManager<UserCalendarEvent>{
	
	private ContainerEventProvider cep = 
			new ContainerEventProvider(container) {
				private static final long serialVersionUID = 1L;
				
				public void addEvent(UserCalendarEvent event) {
					UserCalendarEvent entity = new UserCalendarEvent(event.getAuthorId(),
							event.getSubjectId(), event.getCaption(), event.getDescription(),
							event.getStart(), event.getEnd());
					container.addEntity(entity);
		}
	};
	
	public UserCalendarEventManager() {
		super(UserCalendarEvent.class);
	}

	public ContainerEventProvider getCep() {
		return cep;
	}

	public void setCep(ContainerEventProvider cep) {
		this.cep = cep;
	}
	
}
