package com.yotelopaso.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.yotelopaso.views.implementations.templates.AbstractHomeViewImpl;

public class EditorNoticiasView extends AbstractHomeViewImpl implements View {
	
	private static final long serialVersionUID = 1L;

	public EditorNoticiasView(){}
	
	public void enter(ViewChangeEvent event){
		super.enter(event);
		
		//NewsEditorImpl newsEditor = new NewsEditorImpl();
		//Editor newsEditor = new Editor();
		//getRightLayout().addComponent(newsEditor);
	}

}



