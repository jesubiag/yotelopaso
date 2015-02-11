package com.yotelopaso.components;


import java.util.Date;

import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.yotelopaso.YotelopasoUI;
import com.yotelopaso.domain.Career;
import com.yotelopaso.domain.News;
import com.yotelopaso.domain.Subject;
import com.yotelopaso.persistence.NewsManager;
import com.yotelopaso.persistence.SubjectManager;
import com.yotelopaso.persistence.UserManager;

public class Editor extends Window{

	private NewsManager manNews = new NewsManager();
	private UserManager manUser = new UserManager();
	private SubjectManager manSubject = new SubjectManager();
	private News news = new News();
	private String subjectName;
	private Career carrera;
	private Subject materia;
	private Long id;
	private RichTextArea cuerpo;
	private DateField fecha;
	private TextField titulo;
	private TextField carr;
	private TextField mat;
	private TextField autor;
	private Button aceptar;
	private String labelNotif;
	private FormLayout form;
	private HorizontalLayout botones;
	
	private VerticalLayout mainLayout = new VerticalLayout();
	
	
	private static final long serialVersionUID = 1L;

	public Editor(String subjectName, Long id){
		this.id = id;
		this.subjectName = subjectName;
		carrera = manUser.getCurrentUser().getCareer();
		materia = manSubject.getByProperty("name", this.subjectName).get(0);
		buildMainLayout();
		form.addComponent(titulo);
		form.addComponent(cuerpo);
		form.addComponent(botones);
		autor.setValue(manUser.getCurrentUser().getEmail());
	}
	
	
	private void buildMainLayout(){
		
		//Primero vamos a crear un form que es donde el usuario va a cargar los datos y luego se va a crear el panel
		//en donde se va a colocar el form
		form = new FormLayout();
		 form.addStyleName("Menu");
		 form.setSizeFull();
		 //form.setSpacing(true);
		 
		 //getRightLayout().addComponent(form);
		 //getRightLayout().setExpandRatio(form, 1.0f);
		 
		//Creamos el RichTextArea para el cuerpo de la noticia
		cuerpo = new RichTextArea();
		cuerpo.setValue(null);
		cuerpo.setNullRepresentation("Complete el contenido de la noticia");
       	cuerpo.setImmediate(true);
        cuerpo.setSizeFull();
        cuerpo.setCaption("Contenido");
        cuerpo.addStyleName("borderless");
       
		fecha = new DateField("Fecha de publicación");
		fecha.setValue(new Date());
		fecha.setEnabled(false);
		fecha.addStyleName("borderless");
		
		titulo = new TextField("Título");
		titulo.setMaxLength(35);
		titulo.setWidth("60%");
		titulo.setImmediate(true);
		titulo.setRequired(true);
		titulo.setRequiredError("Obligatorio");
		
		autor = new TextField("Autor");
		autor.setWidth("60%");
		autor.setEnabled(false);
		autor.addStyleName("borderless");		
		
		carr = new TextField("Carrera");
		carr.setValue(carrera.getName());
		carr.setWidth("60%");
		carr.setEnabled(false);
		carr.addStyleName("borderless");
		
		mat = new TextField("Materia");
		mat.setValue(materia.getName());
		mat.setWidth("60%");
		mat.setEnabled(false);
		mat.addStyleName("borderless");
		
		if (id!=null){
			news = manNews.getById(id);
			cuerpo.setValue(news.getContent());
			titulo.setValue(news.getTitle());
			labelNotif="Noticia editada con éxito";
			this.setCaption("Editar Noticia - " + materia.getName());
		}
		else {
			labelNotif="Noticia Creada";
			this.setCaption("Crear Noticia - " + materia.getName());
		}
		
		aceptar = new Button("Guardar",
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
						Notification notif = new Notification(labelNotif, Notification.Type.HUMANIZED_MESSAGE);
						notif.setDelayMsec(2500);
						notif.show(Page.getCurrent());
						getUI().getNavigator().navigateTo(YotelopasoUI.SUBJECTS_VIEW + "/" + materia.getName());		
						close();
					}
					else {
						Notification.show("Revise los campos obligatorios", Notification.Type.WARNING_MESSAGE);
					}
					
				}
	}); //Aca se crea el boton aceptar que persiste los datos
		
		Button cancelar = new Button("Cancelar",
				new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

		//@Override
		public void buttonClick(ClickEvent event) {
			close();
		}
	}); //Boton Cancelar, te envia a la home
		
		aceptar.setStyleName(ValoTheme.BUTTON_SMALL);
		cancelar.setStyleName(ValoTheme.BUTTON_SMALL);
		botones= new HorizontalLayout();
		botones.addComponent(aceptar);
		botones.addComponent(cancelar);
		
		mainLayout.addComponent(form);
		mainLayout.setExpandRatio(form, 1.0f);
		this.setContent(mainLayout);
		this.setWidth("70%");
	}
}


