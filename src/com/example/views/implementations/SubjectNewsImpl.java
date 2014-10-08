package com.example.views.implementations;

import com.example.presenters.SubjectNewsPresenter;
import com.example.utils.DateUtils;
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
	private String careerName;
	private String subjectName;
	private HorizontalLayout topHorizontalLayout = new HorizontalLayout();
	private VerticalLayout elementLayout = new VerticalLayout();
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
		
		
		presenter.setContent(careerName, subjectName);
	}

	@Override
	public void buildComponent(String date, String content) {
		
		Label subjectDate = new Label();
		subjectDate.setContentMode(labelContentMode);
		subjectDate.setValue(date);
		
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
		Label title = new Label();
		title.setContentMode(labelContentMode);
		
		HorizontalLayout topHorizontalLayout = new HorizontalLayout();
		VerticalLayout elementLayout = new VerticalLayout();
		
		topHorizontalLayout.setWidth("100%");
		topHorizontalLayout.setSpacing(false);
		
		elementLayout.setMargin(true);
		elementLayout.setSizeFull();
		elementLayout.setSpacing(true);
		
		topHorizontalLayout.addComponents(subjectDate, career, subject);
		elementLayout.addComponents(topHorizontalLayout, contentSubject);
		this.mainLayout.addComponent(elementLayout);
	}

}
