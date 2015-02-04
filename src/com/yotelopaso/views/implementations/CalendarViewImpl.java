package com.yotelopaso.views.implementations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
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
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.DateClickEvent;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.DateClickHandler;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.EventClick;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.EventClickHandler;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.WeekClick;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.WeekClickHandler;
import com.yotelopaso.components.implementations.EventWindowImpl;
import com.yotelopaso.domain.UserCalendarEvent;
import com.yotelopaso.presenters.CalendarPresenter;
import com.yotelopaso.utils.DateUtils;
import com.yotelopaso.views.CalendarView;
import com.yotelopaso.views.implementations.templates.AbstractHomeViewImpl;

public class CalendarViewImpl extends AbstractHomeViewImpl implements CalendarView, 
ClickListener,
ValueChangeListener,
EventClickHandler,
DateClickHandler,
WeekClickHandler {

	private static final long serialVersionUID = 1L;
	private static final String CALENDAR_MONTHLY = DateUtils.MONTHLY;
	private static final String CALENDAR_WEEKLY = DateUtils.WEEKLY;
	private static final String CALENDAR_DAILY = DateUtils.DAILY;
	private static final List<String> CALENDAR_VIEWS = Arrays.asList(CALENDAR_MONTHLY, CALENDAR_WEEKLY, CALENDAR_DAILY);
	
	private CalendarPresenter presenter;
	BeanItemContainer<UserCalendarEvent> container;
	
	private final VerticalLayout mainLayout = new VerticalLayout();
	private final Calendar calendar = new Calendar();
	private final HorizontalLayout topLayout = new HorizontalLayout();
	private EventWindowImpl window;
	
	private Button addNewEvent;
	private NativeSelect viewSelection;
	
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
		
		rightLayout.addComponent(panel);
		rightLayout.setExpandRatio(panel, 1.0f);
		
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
		
		setCalendarHandlers();
		
	}
	
	private void setCalendarHandlers() {
		calendar.setHandler( (EventClickHandler) this );
		calendar.setHandler( (DateClickHandler) this );
		calendar.setHandler( (WeekClickHandler) this );
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
		
		viewSelection = new NativeSelect("Tipo de vista");
		viewSelection.addItems(CALENDAR_VIEWS);
		viewSelection.setItemCaption(CALENDAR_MONTHLY, "Vista mensual");
		viewSelection.setItemCaption(CALENDAR_WEEKLY, "Vista semanal");
		viewSelection.setItemCaption(CALENDAR_DAILY, "Vista diaria");
		viewSelection.setNullSelectionAllowed(false);
		viewSelection.setValue(CALENDAR_WEEKLY);
		viewSelection.addValueChangeListener( (ValueChangeListener) this );
		
		addNewEvent = new Button("Agregar nuevo evento", this);
		addNewEvent.addStyleName("friendly");
		addNewEvent.addStyleName("small");
		
		mainLayout.addComponent(topLayout);
		topLayout.addComponents(viewSelection, addNewEvent);
		topLayout.setComponentAlignment(viewSelection, Alignment.MIDDLE_LEFT);
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
	
	@Override
	public void setCalendarView(String type) {
		calendar.setStartDate( DateUtils.getStartDate(type) );
		calendar.setEndDate( DateUtils.getEndDate(type) );
	}

	public CalendarPresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(CalendarPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		for ( CalendarViewListener listener : listeners ) {
			listener.valueChange( String.valueOf(event.getProperty().getValue()) );
		}
	}

	@Override
	public void dateClick(DateClickEvent event) {
		viewSelection.setValue(CALENDAR_DAILY);
	}

	@Override
	public void weekClick(WeekClick event) {
		viewSelection.setValue(CALENDAR_WEEKLY);
	}

}
