package com.yotelopaso.components.implementations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.yotelopaso.components.NewsEditor;
import com.yotelopaso.persistence.SubjectManager;


public class NewsEditorImpl extends CustomComponent implements NewsEditor, ClickListener{

	private static final long serialVersionUID = 1L;
	
	private VerticalLayout mainLayout;
	private ComboBox mat;
	private DateField fecha;
	private TextField titulo;
	private TextField contenido;
	private Button aceptar;
	private Button cancelar;
	private Panel panel;
	
	public NewsEditorImpl() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}
	
	private void buildMainLayout() {
		mainLayout = new VerticalLayout();
		mainLayout.setSizeFull();
		
		FormLayout form = new FormLayout();
		form.setCaption("Form");
		form.addStyleName("light");
		form.setWidth("500");
		form.setSpacing(true);
		
		fecha = new DateField("Fecha de publicación");
		fecha.setValue(new Date());
		fecha.setEnabled(false);
		fecha.addStyleName("borderless");
		
		SubjectManager manager = new SubjectManager();
		mat = new ComboBox("Materia", manager.getContainer());
		mat.setItemCaptionPropertyId("name");
		mat.setNullSelectionAllowed(false);
		mat.setTextInputAllowed(false);
		mat.setRequired(true);
		mat.setRequiredError("Obligatorio");
		
		titulo = new TextField("Titulo");
		titulo.setImmediate(true);
		titulo.setMaxLength(50);
		titulo.setWidth("60%");
		titulo.setRequired(true);
		titulo.setRequiredError("Obligatorio");
		
		RichTextArea contenido = new RichTextArea();
       	contenido.setValue("Complete el contenido de la noticia");
       	contenido.setImmediate(true);
        contenido.setSizeFull();
        contenido.setCaption("Descripción de la Noticia");
		
		form.addComponent(titulo);
		form.addComponent(mat);
		form.addComponent(fecha);
		form.addComponent(contenido);
		
		aceptar = new Button();
		aceptar.setCaption("Aceptar");
		aceptar.addClickListener(this);
		cancelar = new Button();
		cancelar.setCaption("Cancelar");
		cancelar.addClickListener(this);
		
		HorizontalLayout botones= new HorizontalLayout();
		botones.addComponent(aceptar);
		botones.addComponent(cancelar);
		form.addComponent(botones);
		
		panel = new Panel("Complete los datos de la noticia");
		panel.setSizeFull();
		panel.setContent(form);
		
		mainLayout.addComponent(panel);
		
	}
	
	List<NewsEditorListener> listeners = new ArrayList<NewsEditorListener>();
	
	public VerticalLayout getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(VerticalLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		for (NewsEditorListener listener : listeners) {
			listener.buttonClick(event.getButton().getCaption());
		}
		
		
	}

	@Override
	public void addListener(NewsEditorListener listener) {
		// TODO Auto-generated method stub
		listeners.add(listener);
	}
	
	@Override
	public ComboBox getMat() {
		// TODO Auto-generated method stub
		return mat;
	}

	@Override
	public void setMat(ComboBox mat) {
		// TODO Auto-generated method stub
		this.mat=mat;
	}

	@Override
	public TextField getCont() {
		// TODO Auto-generated method stub
		return contenido;
	}

	@Override
	public void setCont(TextField contenido) {
		// TODO Auto-generated method stub
		this.contenido=contenido;
	}

	@Override
	public DateField getFecha() {
		// TODO Auto-generated method stub
		return fecha;
	}

	@Override
	public void setFecha(DateField fecha) {
		// TODO Auto-generated method stub
		this.fecha=fecha;
	}

	@Override
	public TextField getTit() {
		// TODO Auto-generated method stub
		return titulo;
	}

	@Override
	public void setTit(TextField titulo) {
		// TODO Auto-generated method stub
		this.titulo=titulo;
	}

	@Override
	public Button getAceptar() {
		// TODO Auto-generated method stub
		return aceptar;
	}

	@Override
	public void setAceptar(Button aceptar) {
		// TODO Auto-generated method stub
		this.aceptar = aceptar;
		
	}

	@Override
	public Button getCancelar() {
		// TODO Auto-generated method stub
		return cancelar;
	}

	@Override
	public void setCancelar(Button cancelar) {
		// TODO Auto-generated method stub
		this.cancelar=cancelar;
	}

	
}