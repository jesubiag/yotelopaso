package com.yotelopaso.views.implementations;

import java.util.Date;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.yotelopaso.domain.UserCalendarEvent;
import com.yotelopaso.domain.UserCalendarEvent.CalendarEventType;
import com.yotelopaso.persistence.SubjectManager;
import com.yotelopaso.views.components.EventWindow;

public class EventWindowImpl extends Window implements EventWindow {

	private static final long serialVersionUID = 2467638202573090272L;
	
	private VerticalLayout layout;
	private DateField startDate;
	private DateField endDate;
	private TextField caption;
	private TextArea description;
	private Button saveButton;
	private Button cancelButton;
	private BeanItem<UserCalendarEvent> item;
	private Integer careerId;
	private ComboBox subject;
	private ComboBox type;
	private FieldGroup binder;
	private boolean isNew = false;
	private SubjectManager sm = new SubjectManager();
	
	public final static Resolution RESOLUTION = Resolution.MINUTE;
	
	public EventWindowImpl(ClickListener parentView, UserCalendarEvent selectedEvent, 
			Double authorId, Integer subjectId, Integer careerId) {
		this.careerId = careerId;
		initWindow();
		UserCalendarEvent event = selectedEvent;
		if ( selectedEvent == null ) {
			event = createNewEvent(authorId);
			isNew = true;
		}
		initItem(event);
		buildLayout(parentView);
		bindFields();
	}
	
	public void initWindow() {
		layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		
		setContent(layout);
		setWidth("650px");
		setModal(true);
		center();
	}
	
	public void initItem(UserCalendarEvent event) {
		item = new BeanItem<UserCalendarEvent>(event);
		item.addItemProperty("authorId", new ObjectProperty<Double>(event.getAuthorId()));
		item.addItemProperty("subjectId", new ObjectProperty<Integer>(event.getSubjectId()));
		item.addItemProperty("eventType", new ObjectProperty<CalendarEventType>(event.getEventType()));
		item.addItemProperty("start", new ObjectProperty<Date>(event.getStart()));
		item.addItemProperty("end", new ObjectProperty<Date>(event.getEnd()));
		item.addItemProperty("caption", new ObjectProperty<String>(event.getCaption()));
		item.addItemProperty("description", new ObjectProperty<String>(event.getDescription()));
	}
	
	public void buildLayout(ClickListener parentView) {
		saveButton = new Button("Guardar", parentView);
		saveButton.setData(item);
		saveButton.setStyleName("small");
		cancelButton = new Button("Cancelar", parentView);
		cancelButton.setStyleName("small");
		
		FormLayout form = new FormLayout();
		binder = new FieldGroup(item);
		binder.setBuffered(true);
		
		startDate = new DateField("Fecha de inicio");
		startDate.setResolution(RESOLUTION);
		startDate.setWidth("100%");
		endDate = new DateField("Fecha de fin");
		endDate.setResolution(RESOLUTION);
		endDate.setWidth("100%");
		subject = new ComboBox("Materia", sm.filterContainerByCareer(this.careerId));
		subject.setWidth("100%");
		subject.setInputPrompt("Seleccione una materia");
		subject.setItemCaptionPropertyId("name");
		subject.setItemCaptionMode(ItemCaptionMode.PROPERTY);
		subject.setFilteringMode(FilteringMode.CONTAINS);
		subject.setImmediate(true);
		subject.setNullSelectionAllowed(false);
		subject.setRequired(true);
		subject.setValidationVisible(false);
		subject.addValidator(new BeanValidator(UserCalendarEvent.class, "subjectId"));
		type = new ComboBox("Tipo de evento", CalendarEventType.getAll());
		type.setWidth("100%");
		type.setInputPrompt("Seleccione el tipo de evento");
		type.setImmediate(true);
		type.setNullSelectionAllowed(false);
		type.setRequired(true);
		type.setValidationVisible(false);
		type.addValidator(new BeanValidator(UserCalendarEvent.class, "eventType"));
		caption = new TextField("Asunto");
		caption.setWidth("100%");
		description = new TextArea("Descripción");
		description.setWidth("100%");
		
		form.addComponents(startDate, endDate, subject, type, caption, description);
		
		HorizontalLayout buttons = new HorizontalLayout();
		buttons.setSpacing(true);
		if ( !isNew ) {
			Button deleteButton = new Button("Borrar", parentView);
			deleteButton.setStyleName("small");
			buttons.addComponent(deleteButton);
		}
		buttons.addComponents(saveButton, cancelButton);
		layout.addComponents(form, buttons);
		layout.setComponentAlignment(buttons, Alignment.BOTTOM_RIGHT);
	}
	
	private void bindFields() {
		binder.bind(startDate, "start");
		binder.bind(endDate, "end");
		binder.bind(subject, "subjectId");
		binder.bind(type, "eventType");
		binder.bind(caption, "caption");
		binder.bind(description, "description");
	}
	
	private UserCalendarEvent createNewEvent(Double authorId) {
		UserCalendarEvent event = new UserCalendarEvent();
		event.setAuthorId(authorId);
		event.setSubjectId(0);
		event.setEventType(CalendarEventType.NONE);
		event.setCaption("");
		event.setDescription("");
		event.setStart(new Date());
		event.setEnd(new Date());
		return event;
	}

	public BeanItem<UserCalendarEvent> getItem() {
		return item;
	}

	public void setItem(BeanItem<UserCalendarEvent> item) {
		this.item = item;
	}
	
	public boolean requiredFieldsAreValid() {
		if ( subject.isValid() && type.isValid() ) {
			try {
				commitChanges();
			} catch (CommitException e) {
				e.printStackTrace();
			}
			return true;
		} else {
			subject.setValidationVisible(true);
			type.setValidationVisible(true);
			return false;
		}
	}
	
	public void discardItem() {
		binder.discard();
	}
	
	private void commitChanges() throws CommitException {
		binder.commit();
	}
	
}
