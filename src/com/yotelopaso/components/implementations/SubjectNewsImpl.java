package com.yotelopaso.components.implementations;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.yotelopaso.components.SubjectNews;
import com.yotelopaso.presenters.SubjectNewsPresenter;
import com.yotelopaso.utils.Hr;

public class SubjectNewsImpl extends CustomComponent implements SubjectNews {
	
	private static final long serialVersionUID = 1L;
	private VerticalLayout mainLayout;
	private SubjectNewsPresenter presenter;
	private ClickListener parentView;
	private String careerName;
	private String subjectName;
	ContentMode labelContentMode = ContentMode.HTML;
	
	public SubjectNewsImpl(String careerName, String subjectName, ClickListener parentView) {
		this.presenter = new SubjectNewsPresenter(this);
		this.parentView = parentView;
		this.careerName = careerName;
		this.subjectName = subjectName;
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	private void buildMainLayout() {
		mainLayout = new VerticalLayout();
		//mainLayout.setSizeUndefined();
		mainLayout.setSizeFull();
		
		
		presenter.setContent(careerName, subjectName);
	}

	@Override
	public void buildComponent(String date, String title, String content, Long id, String authorName) {
		
		addStyleName("subjectNews");
		
		
		Label subjectDate = new Label();
		subjectDate.setContentMode(labelContentMode);
		subjectDate.setValue("Publicado: " + date);
		subjectDate.setStyleName(ValoTheme.LABEL_TINY);
		subjectDate.setSizeUndefined();
		
		// subject
		Label subject = new Label();
		subject.setContentMode(labelContentMode);
		subject.setValue(this.subjectName);
		
		// content
		Label contentSubject = new Label();
		contentSubject.setContentMode(labelContentMode);
		contentSubject.setValue(content);
		contentSubject.setWidth("100%");
		
		// title
		Label titleSubject = new Label();
		titleSubject.setContentMode(labelContentMode);
		titleSubject.setValue(title);
		titleSubject.setStyleName(ValoTheme.LABEL_BOLD);
		
		// author
		Label autName = new Label();
		autName.setContentMode(labelContentMode);
		autName.setValue("Autor: " + authorName);
		autName.setStyleName(ValoTheme.LABEL_TINY);
		autName.setSizeUndefined();
		
		Panel panel = new Panel();
		panel.setContent(contentSubject);
		panel.setSizeFull();
		panel.setHeight("100px");
		panel.setStyleName(ValoTheme.PANEL_WELL);
		
		HorizontalLayout topHorizontalLayout = new HorizontalLayout();
		HorizontalLayout botHorizontalLayout = new HorizontalLayout();
		HorizontalLayout auxHorizontalLayout = new HorizontalLayout();
		VerticalLayout elementLayout = new VerticalLayout();
		
		topHorizontalLayout.setSizeFull();
		topHorizontalLayout.setSpacing(false);
		topHorizontalLayout.setMargin(false);
		topHorizontalLayout.addComponents(autName, subjectDate);
		topHorizontalLayout.setComponentAlignment(subjectDate, Alignment.MIDDLE_RIGHT);
		topHorizontalLayout.setComponentAlignment(autName, Alignment.MIDDLE_LEFT);
			
		Button editButton = new Button("Editar");
		editButton.setData(id);
		editButton.addClickListener(parentView);
		editButton.addStyleName(ValoTheme.BUTTON_TINY);
		editButton.setWidth("70%");
		editButton.setHeight("70%");
		
		Button deleteButton = new Button("Eliminar");
		deleteButton.setData(id);
		deleteButton.addClickListener(parentView);
		deleteButton.addStyleName(ValoTheme.BUTTON_TINY);
		deleteButton.setWidth("70%");
		deleteButton.setHeight("70%");
		
		botHorizontalLayout.setSizeUndefined();
		botHorizontalLayout.setSpacing(false);
		botHorizontalLayout.addComponents(editButton,deleteButton);
		
		auxHorizontalLayout.setSizeFull();
		auxHorizontalLayout.setSpacing(false);
		
		elementLayout.setMargin(false);
		elementLayout.setSizeFull();
		elementLayout.setSpacing(false);
		
		
		if (id==null) 
			auxHorizontalLayout.addComponents(titleSubject);
		else {
			auxHorizontalLayout.addComponents(titleSubject, botHorizontalLayout);
			auxHorizontalLayout.setComponentAlignment(botHorizontalLayout, Alignment.MIDDLE_RIGHT);
		}
		
		elementLayout.addComponents(auxHorizontalLayout, panel, topHorizontalLayout, new Hr());
		
		this.mainLayout.addComponent(elementLayout);
	}

}
