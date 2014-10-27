package com.yotelopaso.views.components;


import java.util.Date;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupView.Content;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.yotelopaso.Vaadintest01UI;
import com.yotelopaso.domain.Career;
import com.yotelopaso.domain.News;
import com.yotelopaso.domain.Subject;
import com.yotelopaso.persistence.NewsManager;
import com.yotelopaso.persistence.SubjectManager;
import com.yotelopaso.persistence.UserManager;

public class Editor extends Window implements Content {

	NewsManager manNews = new NewsManager();
	UserManager manUser = new UserManager();
	SubjectManager manSubject = new SubjectManager();
	News news = new News();
	String subjectName;
	Career carrera;
	Subject materia;
	
	private Panel panel;
	private VerticalLayout mainLayout = new VerticalLayout();
	
	
	private static final long serialVersionUID = 1L;

	public Editor(String subjectName){
		this.subjectName = subjectName;
		buildMainLayout();
		//setCompositionRoot(mainLayout);
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
		cuerpo.setValue(null);
		cuerpo.setNullRepresentation("Complete el contenido de la noticia");
       	cuerpo.setImmediate(true);
        cuerpo.setSizeFull();
        cuerpo.setCaption("Descripción de la Noticia");
       
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
		
		carrera = manUser.getCurrentUser().getCareer();
		materia = manSubject.getByProperty("name", this.subjectName).get(0);
		
		TextField carr = new TextField("Carrera");
		carr.setValue(carrera.getName());
		carr.setWidth("60%");
		carr.setEnabled(false);
		carr.addStyleName("borderless");
		
		TextField mat = new TextField("Materia");
		mat.setValue(this.subjectName);
		mat.setWidth("60%");
		mat.setEnabled(false);
		mat.addStyleName("borderless");
		
		form.addComponent(titulo);
		form.addComponent(carr);
		form.addComponent(mat);
		form.addComponent(fecha);
		form.addComponent(cuerpo);
		
		Button aceptar = new Button("Aceptar",
				new Button.ClickListener() {
				
				private static final long serialVersionUID = 1L;

				//@Override
				public void buttonClick(ClickEvent event) {
					if (titulo.isValid()) {
						news.setCareer(carrera);
						news.setContent(cuerpo.getValue());
						news.setTitle(titulo.getValue());
						news.setDate(fecha.getValue());
						news.setSubject(materia);
						news.setAuthor(manUser.getCurrentUser());
						manNews.save(news);
						Notification.show("Noticia Creada", Notification.Type.HUMANIZED_MESSAGE);
						close();
						//getUI().getNavigator().navigateTo(Vaadintest01UI.SUBJECTS_VIEW);		
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
			close();
			//getUI().getNavigator().navigateTo(Vaadintest01UI.SUBJECTS_VIEW);
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
		this.setContent(mainLayout);
		this.setWidth("60%");
		this.setPositionX(180);
	}

	@Override
	public String getMinimizedValueAsHTML() {
		// TODO Auto-generated method stub
		return "Agregar Noticia";
	}

	@Override
	public Component getPopupComponent() {
		// TODO Auto-generated method stub
		return this;
	}

}



