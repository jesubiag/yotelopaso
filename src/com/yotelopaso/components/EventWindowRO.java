package com.yotelopaso.components;

import com.vaadin.shared.ui.label.ContentMode;
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
	final private Label title = new Label();
	final private Label caption = new Label();
	final private Label description = new Label();
	final private Label author = new Label();
	private SubjectManager sm = new SubjectManager();
	private UserManager um = new UserManager();
	
	public EventWindowRO(UserCalendarEvent event) {
		mainLayout.setSizeFull();
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		setContent(mainLayout);
		setWidth("650px");
		setHeight("450px");
		setModal(true);
		setResizable(false);
		center();
		
		setLabelsProperties(dates, title, caption, description, author);
		
		title.setValue("<b>" +
				sm.getById(event.getSubjectId()) +
				" - " +
				event.getEventType() +
				"</b>");
		
		dates.setValue("Desde " +
				DateUtils.dateFormat( event.getStart() ) +
				" hasta " +
				DateUtils.dateFormat( event.getEnd() ));
		
		author.setValue("Por: " +
				um.getById( event.getAuthorId() ));
		
		caption.setValue("<b>" +event.getCaption() + "</b>");
		
		description.setValue(event.getDescription());
		description.setWidth("615px");
		description.setHeightUndefined();
		
		final VerticalLayout topLayout = new VerticalLayout();
		topLayout.setWidth("650px");
		topLayout.setHeight("125px");
		topLayout.addComponents(title, dates, author, caption);
		
		final Panel descriptionLayout = new Panel();
		descriptionLayout.addStyleName(ValoTheme.PANEL_BORDERLESS);
		descriptionLayout.setWidth("630px");
		descriptionLayout.setHeight("320px");
		descriptionLayout.setContent(description);
		
		mainLayout.addComponents(topLayout, descriptionLayout);
		mainLayout.setExpandRatio(descriptionLayout, 1.0f);
	}
	
	private void setLabelsProperties(Label... labels) {
		for (int i=0; i < labels.length; i++) {
			labels[i].setContentMode(ContentMode.HTML);
		}
	}

}
