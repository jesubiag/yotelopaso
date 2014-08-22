package com.example.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;
import com.example.vaadintest01.Vaadintest01UI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

public class CompDatosView extends VerticalLayout implements View {



	public CompDatosView(){
		Panel menu = new Panel("Complete sus datos");
		menu.setHeight("100%");
		menu.setWidth(null);
		Button aceptar = new Button("Aceptar",
				new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			getUI().getNavigator().navigateTo(Vaadintest01UI.HOMEVIEW);
		}
	}); //Aca se crea el boton aceptar que te envia a la Home
	
		Button cancelar = new Button("Cancelar",
				new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			getUI().getNavigator().navigateTo(Vaadintest01UI.MAINVIEW);
		}
	}); //Lo mismo que el anterior pero para volver al Main
		DateField fecha = new DateField();
		ComboBox carr = new ComboBox();
		carr.addItems("Ingenieria en sistemas","Ingenieria química","Ingenieria eléctrica","Ingeneria mecánica");
		//El comboBox es el menu desplegable para elegir la carrera. En la documentacion esta la forma de como manejar
		//las seleccion para cuando necesitemos guardarlo en la base de datos
		ComboBox taño = new ComboBox();
		taño.addItems("1","2","3","4","5");
		TextField tel = new TextField();
		TextField ciu = new TextField();
		Label carrera = new Label("Carrera");
		Label año = new Label("Año");
		Label telefono = new Label("Teléfono");
		Label ciudad = new Label("Ciudad");
		Label fnac = new Label("Fecha de nacimiento");
		setSpacing(true);
		VerticalLayout content = new VerticalLayout();
		HorizontalLayout hcarr = new HorizontalLayout();
		HorizontalLayout haño = new HorizontalLayout();
		HorizontalLayout htel = new HorizontalLayout();
		HorizontalLayout hciu = new HorizontalLayout();
		HorizontalLayout hfnac = new HorizontalLayout();
		HorizontalLayout hbutton = new HorizontalLayout(); 
		//Basicamente lo que hay hasta aca es una horizontal layout para cada dato, por layout es un label + el lugar donde se carga el dato
		hcarr.addComponent(carrera);
		hcarr.addComponent(carr);
		haño.addComponent(año);
		haño.addComponent(taño);
		htel.addComponent(telefono);
		htel.addComponent(tel);
		hciu.addComponent(ciudad);
		hciu.addComponent(ciu);
		hfnac.addComponent(fnac);
		hfnac.addComponent(fecha);
		hbutton.addComponent(aceptar);
		hbutton.addComponent(cancelar);
		content.addComponent(hcarr);
		content.addComponent(haño);
		content.addComponent(htel);
		content.addComponent(hciu);
		content.addComponent(hfnac);
		content.addComponent(hbutton);
		menu.setContent(content);	
		addComponent(menu);
		

		
		
		
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
