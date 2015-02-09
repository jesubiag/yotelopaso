package com.yotelopaso.components;

import java.util.Date;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
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
import com.yotelopaso.views.SubjectsView;

public class UploadFiles extends Window {

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
	private Label permisos;
	private Button aceptar;
	private Type fileType;
	private Button cancelar;
	private Button cargarArchivo;
	private VerticalLayout mainLayout;
	private SubjectsView parentView;
	
	public UploadFiles (String subjectName, File.Type fileType, SubjectsView parentView){
		this.parentView = parentView;
		this.subjectName = subjectName;
		this.fileType = fileType;
		carrera = manUser.getCurrentUser().getCareer();
		materia = manSubject.getByProperty("name", this.subjectName).get(0);
		
		buildMainLayout();
		
		autor.setValue(manUser.getCurrentUser().getEmail());
		setWidth("650px");	
	}
	
	public UploadFiles(Long fileId, SubjectsView parentView) {
		archivo = manFile.getById(fileId);
		this.parentView = parentView;
		subjectName = archivo.getSubject().getName();
		fileType = archivo.getType();
		carrera = archivo.getCareer();
		materia = archivo.getSubject();
		
		buildMainLayout();
		
		nomArchivo.setValue(archivo.getName());
		dirArchivo.setValue(archivo.getUrl());
		if (archivo.getDescription() != null) {
			descripcion.setValue(archivo.getDescription());
		}
		
		autor.setValue(manUser.getCurrentUser().getEmail());
		setWidth("650px");	
	}
	
	public UploadFiles(String subjectName, File.Type fileType,	SubjectsView parentView, String pickerName, String pickerDir) {
		this.parentView = parentView;
		this.subjectName = subjectName;
		this.fileType = fileType;
		carrera = manUser.getCurrentUser().getCareer();
		materia = manSubject.getByProperty("name", this.subjectName).get(0);
		
		buildMainLayout();
		nomArchivo.setValue(pickerName);
		dirArchivo.setValue(pickerDir);
		
		autor.setValue(manUser.getCurrentUser().getEmail());
		setWidth("650px");	
	}



	private void buildMainLayout() {
		// TODO Auto-generated method stub
		center();
		setWidth("650px");	
		setResizable(false);
		setModal(true);
		setCaption("Subir " + fileType + " - "+ subjectName);
		
		 FormLayout form = new FormLayout();
		 form.setSizeFull();
		 form.setSpacing(true);		 
		 
		 fecha = new DateField("Fecha de publicación");
		 fecha.setValue(new Date());
		 fecha.setEnabled(false);
		 
		 descripcion = new TextArea();
		 descripcion.setSizeFull();
	     descripcion.setCaption("Descripción del archivo");
	     //descripcion.addStyleName("borderless");
	     
	     dirArchivo = new TextField("Cargue el archivo");
	     dirArchivo.setWidth("60%");
	     dirArchivo.setEnabled(false);
	     dirArchivo.addStyleName("borderless");
	     dirArchivo.setRequired(true);
	     dirArchivo.setRequiredError("Por favor cargue un archivo");
	     
	     nomArchivo = new TextField("Nombre del archivo");
	     nomArchivo.setWidth("100%");
	     nomArchivo.setEnabled(false);
	     nomArchivo.setNullSettingAllowed(false);
	     nomArchivo.setRequired(true);
	     nomArchivo.setRequiredError("Por favor cargue un archivo");
	     //nomArchivo.addStyleName("borderless");
	     nomArchivo.setValue("El nombre será completado automaticamente");
	     nomArchivo.setNullRepresentation("El nombre será completado automaticamente");
	     
	     cargarArchivo = new Button("Seleccionar archivo", new Button.ClickListener() {
	    	 static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				parentView.addPicker(fileType);
				close();
			}
	     }		 
	    );
	    cargarArchivo.addStyleName("friendly");
	    cargarArchivo.addStyleName("small");
	    
	 	autor = new TextField("Autor");
		autor.setWidth("60%");
		autor.setEnabled(false);
		autor.addStyleName("borderless");		
		
		permisos =new Label("* Por favor verifique que el archivo tenga permisos públicos. Para ir a Google Drive haga click  " + "<a href='https://drive.google.com/drive/#my-drive' target='_blank'>" + "aquí" + "</a>", ContentMode.HTML);
		
		form.addComponent(nomArchivo);
		form.addComponent(cargarArchivo);
		form.addComponent(descripcion);
		form.addComponent(permisos);
		 
		aceptar = new Button("Guardar",
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
						parentView.selectTab(fileType);
						//Page.getCurrent().reload();
						Notification.show("Archivo guardado");
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
		}
		});
		
		aceptar.addStyleName("small");
		cancelar.addStyleName("small");
		HorizontalLayout botones= new HorizontalLayout();
		botones.addComponent(aceptar);
		botones.addComponent(cancelar);
		
		form.addComponent(botones);
		
		mainLayout = new VerticalLayout();
		mainLayout.setMargin(true);
		mainLayout.addComponent(form);
		
		this.setContent(mainLayout);
		this.setWidth("60%");	
		
	}

}
