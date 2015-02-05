package com.yotelopaso.components;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import com.yotelopaso.domain.UserCalendarEvent;
import com.yotelopaso.persistence.SubjectManager;
import com.yotelopaso.persistence.UserManager;
import com.yotelopaso.utils.DateUtils;

public class EventWindowRO extends Window {
	
	private static final long serialVersionUID = -1169340266628812344L;
	
	final private VerticalLayout mainLayout = new VerticalLayout();
	final private Label dates = new Label();
	final private Label caption = new Label();
	final private Label description = new Label();
	final private Label author = new Label();
	private SubjectManager sm = new SubjectManager();
	private UserManager um = new UserManager();
	
	public EventWindowRO(UserCalendarEvent event) {
			
		setContent(mainLayout);
		setWidth("650px");
		setModal(true);
		setResizable(false);
		center();
		
		setLabelsProperties(dates, caption, description, author);
		
		setCaption(sm.getById(event.getSubjectId()) +
				" - " +
				event.getEventType());
		
		dates.setValue("<ins>" + DateUtils.dateFormat( event.getStart() ) + "</ins>" +
				"<small> hasta </small>" +
				"<ins>" + DateUtils.dateFormat( event.getEnd() ) + "</ins>");
		dates.setSizeUndefined();
		dates.setStyleName(ValoTheme.LABEL_SMALL);
		
		author.setValue("Autor: " + 
				um.getById( event.getAuthorId() ));
		author.setStyleName(ValoTheme.LABEL_TINY);
		author.setSizeUndefined();
		
		caption.setValue("<b>" +event.getCaption() + "</b>");
		caption.setSizeUndefined();
		
		description.setValue(event.getDescription());
		description.setWidth("100%");
		
		final HorizontalLayout topLayout = new HorizontalLayout();
		topLayout.setSizeFull();
		topLayout.addComponents(caption, dates);
		topLayout.setComponentAlignment(caption, Alignment.MIDDLE_LEFT);
		topLayout.setComponentAlignment(dates, Alignment.BOTTOM_RIGHT);
		
		final HorizontalLayout auxLayout = new HorizontalLayout();
		auxLayout.setSizeFull();
		auxLayout.addComponent(author);
		auxLayout.setComponentAlignment(author, Alignment.MIDDLE_LEFT);
		
		final Panel descriptionLayout = new Panel();
		descriptionLayout.addStyleName(ValoTheme.PANEL_WELL);
		descriptionLayout.setWidth("100%");
		descriptionLayout.setHeight("300px");
		descriptionLayout.setContent(description);
		
		mainLayout.addComponents(topLayout, descriptionLayout, auxLayout);
		mainLayout.setMargin(true);
	}
	
	private void setLabelsProperties(Label... labels) {
		for (int i=0; i < labels.length; i++) {
			labels[i].setContentMode(ContentMode.HTML);
		}
	}

}
