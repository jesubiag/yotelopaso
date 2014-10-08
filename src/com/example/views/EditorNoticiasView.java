package com.example.views;

import java.util.Date;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.example.domain.Career;
import com.example.domain.News;
import com.example.domain.Subject;
import com.example.domain.User;
import com.example.persistence.CareerManager;
import com.example.persistence.NewsManager;
import com.example.persistence.SubjectManager;
import com.example.persistence.UserManager;
import com.example.vaadintest01.Vaadintest01UI;
import com.example.views.components.Editor;
import com.example.views.implementations.NewsEditorImpl;
import com.example.views.templates.AbstractHomeViewImpl;
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



