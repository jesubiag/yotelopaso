package com.example.views.implementations;

import java.util.ArrayList;
import java.util.List;

import com.example.persistence.SubjectManager;
import com.example.views.SubjectsView;
import com.example.views.templates.AbstractHomeViewImpl;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;

public class SubjectsViewImpl extends AbstractHomeViewImpl implements SubjectsView {

	private static final long serialVersionUID = 1L;
	private SubjectManager subjectMngr;
	
	public void enter(ViewChangeEvent event) {
		
		super.enter(event);
		
		subjectMngr = new SubjectManager();
		
		//Button b = new Button("Hola", this);
		
		Table subsTable = new Table("Materias a lo negro", subjectMngr.getContainer());
		subsTable.setSizeFull();
		
		Panel panel = new Panel("Materias");
		panel.setSizeFull();
		panel.setContent(subsTable);;
		
		getRightLayout().addComponent(panel);
	}
	
	List<SubjectsViewListener> listeners = new ArrayList<SubjectsViewListener>();

	@Override
	public void addListener(SubjectsViewListener listener) {
		listeners.add(listener);
	}

}
