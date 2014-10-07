package com.example.views.components;

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
import com.example.views.templates.AbstractHomeViewImpl;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class Editor extends CustomComponent{

	NewsManager manNews = new NewsManager();
	News news = new News();
	
	private Panel panel;
	private VerticalLayout mainLayout = new VerticalLayout();
	
	
	private static final long serialVersionUID = 1L;

	public Editor(){
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}
	
	private void buildMainLayout(){
		
		//Primero vamos a crear un form que es donde el usuario va a cargar los datos y luego se va a crear el panel
		//en donde se va a colocar el form
		FormLayout form = new FormLayout();
		 form.addStyleName("Menu");
		 form.setSizeFull();
		 form.setSpacing(true);
		 
		 //getRightLayout().addComponent(form);
		 //getRightLayout().setExpandRatio(form, 1.0f);
		 
		//Creamos el RichTextArea para el cuerpo de la noticia
		final RichTextArea cuerpo = new RichTextArea();
       	cuerpo.setValue("Complete el contenido de la noticia");
       	cuerpo.setImmediate(true);
        cuerpo.setSizeFull();
        cuerpo.setCaption("Descripción de la Noticia");
       
        final SubjectManager manager = new SubjectManager();
		final ComboBox mat = new ComboBox("Materia", manager.getContainer());
		mat.setItemCaptionPropertyId("name");
		mat.setWidth("25%");
		//mat.addItems("Algebra","Análisis Matematico","Ingenieria y Sociedad","Matematica Discreta");
		mat.setNullSelectionAllowed(false);
		mat.setTextInputAllowed(false);
		mat.setRequired(true);
		mat.setRequiredError("Obligatorio");
		
		final DateField fecha = new DateField("Fecha de publicación");
		fecha.setValue(new Date());
		fecha.setEnabled(false);
		fecha.addStyleName("borderless");
		
		final TextField titulo = new TextField("Título");
		titulo.setMaxLength(50);
		titulo.setWidth("60%");
		titulo.setImmediate(true);
		titulo.setRequired(true);
		titulo.setRequiredError("Obligatorio");
		
		form.addComponent(titulo);
		form.addComponent(mat);
		form.addComponent(fecha);
		form.addComponent(cuerpo);
		
		final Career carrera = new Career();
		carrera.setName("Ingeniería en Sistemas");
		carrera.setId(1);
		
		
		Button aceptar = new Button("Aceptar",
				new Button.ClickListener() {
				
				private static final long serialVersionUID = 1L;

				//@Override
				public void buttonClick(ClickEvent event) {
					Subject materia = new Subject();
					SubjectManager subManager = new SubjectManager();
					materia = subManager.getById((Integer) mat.getValue());
					if (mat.isValid() & titulo.isValid()) {
						news.setCareer(carrera);
						news.setContent(cuerpo.getValue());
						news.setTitle(titulo.getValue());
						news.setDate(fecha.getValue());
						news.setSubject(materia);
						manNews.save(news);
						getUI().getNavigator().navigateTo(Vaadintest01UI.HOME_VIEW);		
					}
					else {
						Notification.show("Revise los campos obligatorios", Notification.Type.WARNING_MESSAGE);
					}
					
				}
	}); //Aca se crea el boton aceptar que persiste los datos y te envia a la home
		Button cancelar = new Button("Cancelar",
				new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

		//@Override
		public void buttonClick(ClickEvent event) {
			getUI().getNavigator().navigateTo(Vaadintest01UI.HOME_VIEW);
		}
	}); //Boton Cancelar, te envia a la home
		
		HorizontalLayout botones= new HorizontalLayout();
		botones.addComponent(aceptar);
		botones.addComponent(cancelar);
		
		form.addComponent(botones);
		
		panel = new Panel("Editor de Noticias");
		panel.setContent(form);
		panel.setSizeFull();
		mainLayout.addComponent(panel);
		mainLayout.setExpandRatio(panel, 1.0f);

	}

}



