package com.example.views.implementations;

import java.util.ArrayList;
import java.util.List;

import com.example.views.SubjectsView;
import com.example.views.templates.AbstractHomeViewImpl;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HasComponents.ComponentAttachListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class SubjectsViewImpl extends AbstractHomeViewImpl implements SubjectsView, ComponentAttachListener, ClickListener {

	private static final long serialVersionUID = 1L;
	
	private Label userCareerName;
	
	public void enter(ViewChangeEvent event) {
		
		super.enter(event);
		
		final VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setSizeFull();
		mainLayout.setMargin(true);
		
		final Panel panel = new Panel("Materias");
		panel.setSizeFull();
		panel.setContent(mainLayout);
		
		getRightLayout().addComponent(panel);
		
		userCareerName = new Label();
		getRightLayout().addComponentAttachListener(this);
		
		getRightLayout().addComponent(new SubjectsByYearImpl("Ingeniería en Sistemas", 4));
	}
	
	List<SubjectsViewListener> listeners = new ArrayList<SubjectsViewListener>();

	@Override
	public void addListener(SubjectsViewListener listener) {
		listeners.add(listener);
	}

	@Override
	public void componentAttachedToContainer(ComponentAttachEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		super.buttonClick(event);
	}

}
