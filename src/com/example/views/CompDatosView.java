package com.example.views;

import java.text.NumberFormat;
import java.util.Locale;
import com.vaadin.data.util.converter.StringToLongConverter;
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
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

public class CompDatosView extends AbstractHomeView implements View {

	
	private static final long serialVersionUID = 1L;

	public CompDatosView(){}
	
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
		DateField fecha = new DateField("Fecha de nacimiento");
		
		ComboBox carr = new ComboBox("Carrera");
		carr.addItems("Ingenieria en sistemas","Ingenieria química","Ingenieria eléctrica","Ingenieria mecánica");
		//El comboBox es el menu desplegable para elegir la carrera. En la documentacion esta la forma de como manejar
		//las seleccion para cuando necesitemos guardarlo en la base de datos
		ComboBox taño = new ComboBox("Año");
		taño.addItems("1","2","3","4","5");
		carr.setNullSelectionAllowed(false);
		taño.setNullSelectionAllowed(false);
		carr.setTextInputAllowed(false);
		taño.setTextInputAllowed(false);
		taño.setRequired(true);
		carr.setRequired(true);
		carr.setRequiredError("Obligatorio");
		taño.setRequiredError("Obligatorio");
		
		
	
		
		//Se crea el textfield para teléfono asegurándose que lo ingresado sea un numero y no un string
		TextField tel = new TextField("Teléfono");
		tel.setImmediate(true);
	
		StringToLongConverter convertidorPlano = new StringToLongConverter() {
		  
			private static final long serialVersionUID = 1L;

			protected java.text.NumberFormat getFormat(Locale locale) {
		        NumberFormat format = super.getFormat(locale);
		        format.setGroupingUsed(false);
		        return format;
		    };
		};
		tel.setConverter(convertidorPlano);
		tel.setConversionError("Por favor ingrese un número de teléfono válido");
		tel.setMaxLength(20);
		
		tel.setValue(null);
		tel.setNullRepresentation("");
		
		
		
		TextField ciu = new TextField("Ciudad");
		form.addComponent(carr);
		form.addComponent(taño);
		form.addComponent(ciu);
		form.addComponent(tel);
		form.addComponent(fecha);
		
		HorizontalLayout botones= new HorizontalLayout();
		botones.addComponent(aceptar);
		botones.addComponent(cancelar);
		
		form.addComponent(botones);
		
		Panel panel = new Panel("Complete sus datos por favor");
		panel.setSizeFull();
		panel.setContent(form);
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

