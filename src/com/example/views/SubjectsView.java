package com.example.views;

import com.example.persistence.SubjectManager;
import com.example.views.templates.AbstractHomeView;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;

public class SubjectsView extends AbstractHomeView {

	private static final long serialVersionUID = 1L;
	private SubjectManager subjectMngr;
	
	public void enter(ViewChangeEvent event) {
		
		super.enter(event);
		
		subjectMngr = new SubjectManager();
		
		Table subsTable = new Table("Materias a lo negro", subjectMngr.getContainer());
		subsTable.setSizeFull();
		
		Panel panel = new Panel("Materias");
		panel.setSizeFull();
		panel.setContent(subsTable);;
		
		getRightLayout().addComponent(panel);
	}

}
