package com.example.views.implementations;

import java.util.ArrayList;
import java.util.List;

import com.example.presenters.SubjectsByYearPresenter;
import com.example.views.components.SubjectsByYear;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class SubjectsByYearImpl extends CustomComponent implements SubjectsByYear, ClickListener {

	private static final long serialVersionUID = 1L;
	
	private VerticalLayout mainLayout;
	private VerticalLayout contentLayout;
	private String careerName;
	private int year;
	private Label title;
	private SubjectsByYearPresenter presenter;

	public SubjectsByYearImpl(final String careerName, final int year) {
		this.presenter = new SubjectsByYearPresenter(this);
		this.careerName = careerName;
		this.year = year;
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	private void buildMainLayout() {
		mainLayout = new VerticalLayout();
		
		// title
		title = new Label(careerName);
		
		// contentLayout
		contentLayout = new VerticalLayout();
		presenter.setContent(careerName, year);
		
		mainLayout.addComponents(title, contentLayout);
		
	}
	
	List<SubjectsByYearListener> listeners = new ArrayList<SubjectsByYearListener>();

	@Override
	public void addListener(SubjectsByYearListener listener) {
		listeners.add(listener);
	}
	
	public void addButtonToContentLayout(String buttonCaption) {
		Button subjectButton = new Button(buttonCaption);
		subjectButton.addStyleName(ValoTheme.BUTTON_LINK);
		subjectButton.addClickListener(this);
		getContentLayout().addComponent(subjectButton);
	}

	public VerticalLayout getContentLayout() {
		return contentLayout;
	}

	public void setContentLayout(VerticalLayout contentLayout) {
		this.contentLayout = contentLayout;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
	}

}
