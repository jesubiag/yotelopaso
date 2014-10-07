package com.example.views.implementations;

import com.example.presenters.SubjectNewsPresenter;
import com.example.views.components.SubjectNews;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class SubjectNewsImpl extends CustomComponent implements SubjectNews {
	
	private static final long serialVersionUID = 1L;
	private VerticalLayout mainLayout;
	private SubjectNewsPresenter presenter;
	private ClickListener parentView;
	private Label date;
	private Label title;
	private Label career;
	private Label subject;
	private Label content;
	private String careerName;
	private String subjectName;
	final private HorizontalLayout topHorizontalLayout = new HorizontalLayout();
	final private VerticalLayout elementLayout = new VerticalLayout();
	
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
		
		ContentMode labelContentMode = ContentMode.HTML;
		
		// date
		date = new Label();
		date.setContentMode(labelContentMode);
		
		// career
		career = new Label();
		career.setContentMode(labelContentMode);
		
		// subject
		subject = new Label();
		subject.setContentMode(labelContentMode);
		
		// content
		content = new Label();
		content.setContentMode(labelContentMode);
		
		// title
		title = new Label();
		title.setContentMode(labelContentMode);
		
		topHorizontalLayout.setWidth("100%");
		topHorizontalLayout.setSpacing(true);
		
		elementLayout.setMargin(true);
		elementLayout.setSizeFull();
		elementLayout.setSpacing(true);
		
		presenter.setContent(careerName, subjectName);
	}

	@Override
	public void buildComponent(String date, String content) {
		this.date.setValue(date);
		this.career.setValue(this.careerName);
		this.subject.setValue(this.subjectName);
		this.content.setValue(content);
		topHorizontalLayout.addComponents(this.date, this.career, this.subject);
		elementLayout.addComponents(topHorizontalLayout, this.content);
		mainLayout.addComponent(elementLayout);
	}

}
