package com.yotelopaso.views.implementations;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.VerticalLayout;
import com.yotelopaso.domain.UserCalendarEvent;
import com.yotelopaso.persistence.UserCalendarEventManager;
import com.yotelopaso.presenters.CalendarPresenter;
import com.yotelopaso.views.CalendarView;
import com.yotelopaso.views.templates.AbstractHomeViewImpl;

public class CalendarViewImpl extends AbstractHomeViewImpl implements CalendarView {

	private static final long serialVersionUID = 1L;
	
	private CalendarPresenter presenter;
	private final VerticalLayout mainLayout = new VerticalLayout();
	private final Calendar calendar = new Calendar();
	
	@Override
	public void enter(ViewChangeEvent event) {
		
		super.enter(event);
		
		mainLayout.setSizeFull();
		mainLayout.setMargin(true);
		
		calendar.setSizeFull();
		
		// TODO: De prueba, borrar
		UserCalendarEventManager mngr = new UserCalendarEventManager();
		
		// Creo un container como fuente de datos para el calendario
		final BeanItemContainer<UserCalendarEvent> container =
				new BeanItemContainer<UserCalendarEvent>(UserCalendarEvent.class);

		// Creo eventos del calendario
		GregorianCalendar start = new GregorianCalendar();
		GregorianCalendar end   = new GregorianCalendar();
		end.add(java.util.Calendar.HOUR, 2);
		UserCalendarEvent evento1 = new UserCalendarEvent(12345678D, 35, "Soy el caption234000", 
				"Soy la description\n Y salto de linea",
		        start.getTime(), end.getTime());
		start.add(java.util.Calendar.HOUR, 1);
		end.add(java.util.Calendar.HOUR, 4);
		UserCalendarEvent evento2 = new UserCalendarEvent(12345678D, 35, "Soy el caption567000", 
				"Soy la description\n Y salto de linea2",
		        start.getTime(), end.getTime());
		start.add(java.util.Calendar.HOUR, 7);
		end.add(java.util.Calendar.HOUR, 10);
		UserCalendarEvent evento3 = new UserCalendarEvent(12345678D, 35, "Soy el caption890000", 
				"Soy la description\n Y salto de linea3",
		        start.getTime(), end.getTime());
		
		// Guardo los eventos
		/*mngr.save(evento1);
		mngr.save(evento2);
		mngr.save(evento3);*/
		
		// Obtengo la lista de eventos del usuario actual que se mostrarán en el calendario
		List<UserCalendarEvent> l = mngr.getCurrentUserEvents();
		
		// Agrego la lista de eventos al container del calendario
		container.addAll(l);
		// Así se agrega individualmente un evento al container
		//container.addBean(evento);

		// The container must be ordered by the start time. You
		// have to sort the BIC every time after you have added
		// or modified events.
		container.sort(new String[]{"start"}, new boolean[]{true});
		
		calendar.setContainerDataSource(container, "caption",
				"description", "start", "end", "styleName");
		
		mainLayout.addComponent(calendar);
		
		getRightLayout().addComponent(mainLayout);
	}
	
	List<CalendarViewListener> listeners = new ArrayList<CalendarViewListener>();

	@Override
	public void addListener(CalendarViewListener listener) {
		listeners.add(listener);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		//super.buttonClick(event);
		for ( CalendarViewListener listener : listeners ) {
			listener.buttonClick( event.getButton().getCaption() );
		}
	}

}
