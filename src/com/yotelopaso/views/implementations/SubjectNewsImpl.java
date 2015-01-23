package com.yotelopaso.views.implementations;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.yotelopaso.presenters.SubjectNewsPresenter;
import com.yotelopaso.utils.Hr;
import com.yotelopaso.views.components.SubjectNews;

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
	public void buildComponent(String date, String title, String content, Long id, String authorMail) {
		
		addStyleName("subjectNews");
		
		Label subjectDate = new Label();
		subjectDate.setContentMode(labelContentMode);
		subjectDate.setValue(date);
		// no esta funcionando el estilo
		subjectDate.addStyleName("subjectNews");
		
		// career
		Label career = new Label();
		career.setContentMode(labelContentMode);
		career.setValue(this.careerName);
		
		// subject
		Label subject = new Label();
		subject.setContentMode(labelContentMode);
		subject.setValue(this.subjectName);
		
		// content
		Label contentSubject = new Label();
		contentSubject.setContentMode(labelContentMode);
		contentSubject.setValue(content);
		
		// title
		Label titleSubject = new Label();
		titleSubject.setContentMode(labelContentMode);
		titleSubject.setValue(title);
		
		// author
		Label autMail = new Label();
		autMail.setContentMode(labelContentMode);
		autMail.setValue(authorMail);
		
		HorizontalLayout topHorizontalLayout = new HorizontalLayout();
		VerticalLayout elementLayout = new VerticalLayout();
		
		topHorizontalLayout.setWidth("100%");
		topHorizontalLayout.setSpacing(true);
		topHorizontalLayout.setMargin(false);
		topHorizontalLayout.setSizeUndefined();
		
		Button editButton = new Button("Editar");
		editButton.setData(id);
		editButton.addClickListener(parentView);
		//editButton.addStyleName("primary");
		editButton.setWidth("60%");
		editButton.setHeight("80%");
		
		Button deleteButton = new Button("Eliminar");
		deleteButton.setData(id);
		deleteButton.addClickListener(parentView);
		//deleteButton.addStyleName("primary");
		deleteButton.setWidth("60%");
		deleteButton.setHeight("80%");
		
		
		elementLayout.setMargin(false);
		elementLayout.setSizeFull();
		elementLayout.setSpacing(true);
		
		if (id==null) 
			topHorizontalLayout.addComponents(subjectDate, career, subject, autMail);
		else
			topHorizontalLayout.addComponents(subjectDate, career, subject, autMail, editButton, deleteButton);
		
		elementLayout.addComponents(topHorizontalLayout, titleSubject, contentSubject, new Hr());
		this.mainLayout.addComponent(elementLayout);
	}

}
