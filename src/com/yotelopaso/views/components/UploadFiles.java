package com.yotelopaso.views.components;

import java.util.Date;

import org.vaadin.addon.googlepicker.GooglePicker;


import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.PopupView.Content;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.yotelopaso.Vaadintest01UI;
import com.yotelopaso.domain.Career;
import com.yotelopaso.domain.File;
import com.yotelopaso.domain.File.Type;
import com.yotelopaso.domain.Subject;
import com.yotelopaso.persistence.FileManager;
import com.yotelopaso.persistence.SubjectManager;
import com.yotelopaso.persistence.UserManager;

public class UploadFiles extends Window implements Content {

	private static final long serialVersionUID = 1L;
	private File archivo = new File();
	private Career carrera = new Career();
	private Subject materia = new Subject();
	private UserManager manUser = new UserManager();
	private SubjectManager manSubject = new SubjectManager();
	private FileManager manFile = new FileManager();
	private String subjectName;
	private TextField autor;
	private DateField fecha;
	private TextArea descripcion;
	private TextField dirArchivo;
	private TextField nomArchivo;
	private Button aceptar;
	private TextField carr;
	private TextField mat;
	private Type fileType;
	private Button cancelar;
	private Button cargarArchivo;
	private VerticalLayout vertLayout;
	private Panel panel;
	private VerticalLayout mainLayout;
	private Window ventana;
	
	public UploadFiles(String subjectName, File.Type fileType){
		this.subjectName = subjectName;
		this.fileType = fileType;
		ventana = this;
		carrera = manUser.getCurrentUser().getCareer();
		materia = manSubject.getByProperty("name", this.subjectName).get(0);
		buildMainLayout();
		autor.setValue(manUser.getCurrentUser().getEmail());
			
	}
	
	
	
	private void buildMainLayout() {
		// TODO Auto-generated method stub
		FormLayout form = new FormLayout();
		 form.addStyleName("light");
		 form.setSizeFull();
		 form.setSpacing(true);
		 
		 
		 fecha = new DateField("Fecha de publicación");
		 fecha.setValue(new Date());
		 fecha.setEnabled(false);
		 fecha.addStyleName("borderless");
		 
		 descripcion = new TextArea();
		 descripcion.setSizeFull();
	     descripcion.setCaption("Descripción del archivo");
	     descripcion.addStyleName("borderless");
	     
	     dirArchivo = new TextField("Cargue el archivo");
	     dirArchivo.setWidth("60%");
	     dirArchivo.setEnabled(false);
	     dirArchivo.addStyleName("borderless");
	     dirArchivo.setRequired(true);
	     dirArchivo.setRequiredError("Por favor cargue un archivo");
	     
	     nomArchivo = new TextField("Nombre del archivo");
	     nomArchivo.setWidth("60%");
	     nomArchivo.setEnabled(false);
	     nomArchivo.addStyleName("borderless");
	     
	     cargarArchivo = new Button("Cargar archivo", new Button.ClickListener() {
	    	 static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				 ventana.setHeight("0px");
				 ventana.setWidth("0px");
			     GooglePicker picker = new GooglePicker("AIzaSyAQ9PvsZuf6rGzRD71k9jWGES8ti6vjDTE", "398023219009-gsn7asjfgpvspfrmd8oh5shkdidpmi4a.apps.googleusercontent.com");
			     picker.addSelectionListener(new GooglePicker.SelectionListener() {
			    
					private static final long serialVersionUID = 1L;

						@Override
			            public void documentSelected(GooglePicker.Document document) {
			                String name = document.getName();
			                nomArchivo.setValue(name);
			                String url = document.getUrlString();
			                dirArchivo.setValue(url);
			                ventana.setHeight("520px");
			                ventana.setWidth("700px");
			            }//Aca elegimos que hacer con el archivo que selecciona el usuario
						//En nuestro caso obtenemos el nombre y la URL

			        });
			        addExtension(picker);

			        // Abre el picker de Google que muestra las carpetas de drive del usuario
			        picker.pickDocument(GooglePicker.Type.FOLDERS);
				
			}
		}
	    		 
	    		);
	    cargarArchivo.addStyleName("primary");
	    
	    vertLayout = new VerticalLayout();
	    vertLayout.addComponent(dirArchivo);
	    vertLayout.addComponent(cargarArchivo);
	    
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
		
		form.addComponent(carr);
		form.addComponent(mat);
		form.addComponent(autor);
		form.addComponent(fecha);
		form.addComponent(nomArchivo);
		form.addComponent(vertLayout);
		form.addComponent(descripcion);
		
		
	     
		aceptar = new Button("Aceptar",
				new Button.ClickListener() {
				
				private static final long serialVersionUID = 1L;

				//@Override
				public void buttonClick(ClickEvent event) {
					if (dirArchivo.isValid()) {
						archivo.setCareer(carrera);
						archivo.setSubject(materia);
						archivo.setDescription(descripcion.getValue());
						archivo.setCreationDate(fecha.getValue());
						archivo.setType(fileType);
						archivo.setAuthor(manUser.getCurrentUser());
						archivo.setUrl(dirArchivo.getValue());
						archivo.setName(nomArchivo.getValue());
						manFile.save(archivo);
						getUI().getNavigator().navigateTo(Vaadintest01UI.SUBJECTS_VIEW + "/" + materia.getName());		
						close();
					}
					else {
						Notification.show("Por favor cargue un archivo", Notification.Type.WARNING_MESSAGE);
					}
					
				}
	});
	 
		
		cancelar = new Button("Cancelar",
				new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

		//@Override
		public void buttonClick(ClickEvent event) {
			close();
			//getUI().getNavigator().navigateTo(Vaadintest01UI.SUBJECTS_VIEW);
		}
		});
		
		aceptar.addStyleName("primary");
		cancelar.addStyleName("primary");
		HorizontalLayout botones= new HorizontalLayout();
		botones.addComponent(aceptar);
		botones.addComponent(cancelar);
		
		form.addComponent(botones);
		
		panel = new Panel("Cargar archivo");
		panel.setContent(form);
		panel.setSizeFull();
		mainLayout = new VerticalLayout();
		mainLayout.addComponent(panel);
		mainLayout.setExpandRatio(panel, 1.0f);
		this.setContent(mainLayout);
		this.setWidth("60%");
		this.setPositionX(180);
		
		
	}



	@Override
	public String getMinimizedValueAsHTML() {
		// TODO Auto-generated method stub
		return "Agregar archivo";
	}

	@Override
	public Component getPopupComponent() {
		// TODO Auto-generated method stub
		return this;
	}

}
