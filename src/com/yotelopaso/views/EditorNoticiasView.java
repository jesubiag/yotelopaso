package com.yotelopaso.views;

import java.util.Date;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.yotelopaso.Vaadintest01UI;
import com.yotelopaso.domain.Career;
import com.yotelopaso.domain.News;
import com.yotelopaso.domain.Subject;
import com.yotelopaso.domain.User;
import com.yotelopaso.persistence.CareerManager;
import com.yotelopaso.persistence.NewsManager;
import com.yotelopaso.persistence.SubjectManager;
import com.yotelopaso.persistence.UserManager;
import com.yotelopaso.views.components.Editor;
import com.yotelopaso.views.implementations.NewsEditorImpl;
import com.yotelopaso.views.templates.AbstractHomeViewImpl;

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



