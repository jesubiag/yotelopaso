package com.example.views;

import java.util.Date;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.example.vaadintest01.Vaadintest01UI;
import com.example.views.templates.AbstractHomeView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

public class EditorNoticiasView extends AbstractHomeView implements View {

	Panel panel;
	
	private static final long serialVersionUID = 1L;

	public EditorNoticiasView(){}
	
	public void enter(ViewChangeEvent event){
		super.enter(event);
		
		//Primero vamos a crear un form que es donde el usuario va a cargar los datos y luego se va a crear el panel
		//en donde se va a colocar el form
		FormLayout form = new FormLayout();
		 form.addStyleName("Menu");
		 form.setSizeFull();
		 form.setSpacing(true);
		 
		 getRightLayout().addComponent(form);
		 getRightLayout().setExpandRatio(form, 1.0f);
		 
		//Creamos el RichTextArea para el cuerpo de la noticia
		RichTextArea cuerpo = new RichTextArea();
       	cuerpo.setValue("Complete el contenido de la noticia");
       	cuerpo.setImmediate(true);
        cuerpo.setSizeFull();
        cuerpo.setCaption("Descripción de la Noticia");
        
        
		Button aceptar = new Button("Aceptar",
				new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

		//@Override
		public void buttonClick(ClickEvent event) {
			getUI().getNavigator().navigateTo(Vaadintest01UI.HOMEVIEW);
		}
	}); //Aca se crea el boton aceptar que te envia a la Home
		Button cancelar = new Button("Cancelar",
				new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

		//@Override
		public void buttonClick(ClickEvent event) {
			getUI().getNavigator().navigateTo(Vaadintest01UI.MAINVIEW);
		}
	}); //Lo mismo que el anterior pero para volver al Main
		
		ComboBox mat = new ComboBox("Materia");
		mat.addItems("Algebra","Análisis Matematico","Ingenieria y Sociedad","Matematica Discreta");
		mat.setNullSelectionAllowed(false);
		mat.setTextInputAllowed(false);
		mat.setRequired(true);
		mat.setRequiredError("Obligatorio");
		
		DateField fecha = new DateField("Fecha de publicacion");
		fecha.setValue(new Date());
		fecha.setEnabled(false);
		
		TextField titulo = new TextField("Título");
		titulo.setRequired(true);
		titulo.setRequiredError("Obligatorio");
		
		form.addComponent(titulo);
		form.addComponent(mat);
		form.addComponent(cuerpo);
		form.addComponent(fecha);
		
		HorizontalLayout botones= new HorizontalLayout();
		botones.addComponent(aceptar);
		botones.addComponent(cancelar);
		
		form.addComponent(botones);
		
		panel = new Panel("Editor de Noticias");
		panel.setContent(form);
		panel.setSizeFull();
		getRightLayout().addComponent(panel);
		getRightLayout().setExpandRatio(panel, 1.0f);

	}
	
	class ButtonListener implements Button.ClickListener {

		private static final long serialVersionUID = 1L;
		String menuitem;
		
		public ButtonListener(String menuitem) {
			//this.menuitem = menuitem;
			this.menuitem = Vaadintest01UI.HOMEVIEW;
		}

		@Override
		public void buttonClick(ClickEvent event) {
			// Navigate to a specific state
			UI.getCurrent().getNavigator().navigateTo(Vaadintest01UI.MAINVIEW + menuitem);
		}
	}
		
	
	

}



