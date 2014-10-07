package com.example.views.implementations;

import java.util.ArrayList;
import java.util.List;

import com.example.presenters.SubjectsPresenter;
import com.example.views.SubjectsView;
import com.example.views.templates.AbstractHomeViewImpl;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;

public class SubjectsViewImpl extends AbstractHomeViewImpl implements SubjectsView, ClickListener {

	private static final long serialVersionUID = 1L;
	
	private SubjectsPresenter presenter;
	final HorizontalLayout mainLayout = new HorizontalLayout();
	
	public void enter(ViewChangeEvent event) {
		
		super.enter(event);
		
		mainLayout.setSizeFull();
		mainLayout.setMargin(true);
		
		final Panel panel = new Panel("Materias");
		panel.setSizeFull();
		panel.setContent(mainLayout);
		
		getRightLayout().addComponent(panel);
		
		presenter.setContent();
	}
	
	List<SubjectsViewListener> listeners = new ArrayList<SubjectsViewListener>();

	@Override
	public void addListener(SubjectsViewListener listener) {
		listeners.add(listener);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		super.buttonClick(event);
	}

	public SubjectsPresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(SubjectsPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setSubjects(String careerName, int year) {
		this.mainLayout.addComponent(new SubjectsByYearImpl(careerName, year, this));
	}

}
