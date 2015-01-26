package com.yotelopaso.views.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.EventClick;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.EventClickHandler;
import com.yotelopaso.domain.UserCalendarEvent;
import com.yotelopaso.presenters.CalendarPresenter;
import com.yotelopaso.views.CalendarView;
import com.yotelopaso.views.templates.AbstractHomeViewImpl;

public class CalendarViewImpl extends AbstractHomeViewImpl implements CalendarView, 
ClickListener, EventClickHandler {

	private static final long serialVersionUID = 1L;
	
	private CalendarPresenter presenter;
	BeanItemContainer<UserCalendarEvent> container;
	
	private final VerticalLayout mainLayout = new VerticalLayout();
	private final Calendar calendar = new Calendar();
	private final HorizontalLayout topLayout = new HorizontalLayout();
	private EventWindowImpl window;
	
	private Button addNewEvent;
	
	private Panel panel;
	
	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
		buildView();
	}
	
	List<CalendarViewListener> listeners = new ArrayList<CalendarViewListener>();
	
	private void buildView() {
		panel = new Panel("Mi Calendario");
		panel.setSizeFull();
		
		getRightLayout().addComponent(panel);
		getRightLayout().setExpandRatio(panel, 1.0f);
		
		cleanComponents();
		
		mainLayout.setSizeFull();
		mainLayout.setSpacing(true);
		mainLayout.setMargin(true);
		
		buildTopLayout();
		addLine(mainLayout);
		
		mainLayout.addComponent(calendar);
		mainLayout.setExpandRatio(calendar, 1.0f);
		panel.setContent(mainLayout);
		calendar.setSizeFull();
		calendar.setImmediate(true);
		
		presenter.initCalendar();
		
		calendar.setHandler( (EventClickHandler) this);
		
	}
	
	@Override
	public void initCalendarContainer(Set<UserCalendarEvent> events) {
		// Creo un container como fuente de datos para el calendario
		container = new BeanItemContainer<UserCalendarEvent>(UserCalendarEvent.class);
		// Agrego la lista de eventos al container del calendario
		container.addAll(events);
		// The container must be ordered by the start time. You
		// have to sort the BIC every time after you have added
		// or modified events.
		container.sort(new String[] {"start"}, new boolean[] {true});
		calendar.setContainerDataSource(container, "caption",
				"description", "start", "end", "styleName");
	}
	
	private void buildTopLayout() {
		topLayout.setWidth("100%");
		topLayout.setHeightUndefined();
		topLayout.setMargin(false);
		
		addNewEvent = new Button("Agregar nuevo evento", this);
		addNewEvent.addStyleName("friendly");
		addNewEvent.addStyleName("small");
		
		mainLayout.addComponent(topLayout);
		topLayout.addComponent(addNewEvent);
		topLayout.setComponentAlignment(addNewEvent, Alignment.MIDDLE_RIGHT);
		
	}
	
	@Override
	public void addEventWindow(UserCalendarEvent selectedEvent, Double authorId, Integer careerId) {
		// TODO: completar metodo - por ahora no se usa el parametro subjectId
		window = new EventWindowImpl(this, selectedEvent, authorId, null, careerId);
		presenter.setWindow(window);
		addWindow(window);
	}
	
	@Override
	public void eventAddedSuccesfully() {
		successfullOperation("Evento creado correctamente");
	}
	
	@Override
	public void eventRemovedSuccessfully() {
		successfullOperation("Evento borrado correctamente");
	}
	
	private void successfullOperation(String notificationMessage) {
		calendar.markAsDirty();
		closeCurrentWindow();
		showNotification(notificationMessage);
	}
	
	@Override
	public void showNotification(String message) {
		Notification n = new Notification(message);
		n.setDelayMsec(2500);
		n.show(Page.getCurrent());
	}
	
	@Override
	public void notificateFieldsError() {
		showNotification("Complete los campos requeridos correctamente");
	}
	
	private void cleanComponents() {
		mainLayout.removeAllComponents();
		topLayout.removeAllComponents();
	}
	
	@Override
	public void closeCurrentWindow() {
		closeWindow(window);
	}

	@Override
	public void addListener(CalendarViewListener listener) {
		listeners.add(listener);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void buttonClick(ClickEvent event) {
		//super.buttonClick(event);
		for ( CalendarViewListener listener : listeners ) {
			listener.buttonClick( event.getButton().getCaption(), 
					(BeanItem<UserCalendarEvent>) event.getButton().getData() );
		}
	}
	
	@Override
	public void eventClick(EventClick event) {
		for ( CalendarViewListener listener : listeners ) {
			listener.eventClick( (UserCalendarEvent) event.getCalendarEvent() );
		}
	}

	public CalendarPresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(CalendarPresenter presenter) {
		this.presenter = presenter;
	}

}
