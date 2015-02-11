package com.yotelopaso.views;

import java.text.NumberFormat;
import java.util.Locale;

import com.vaadin.data.util.converter.StringToLongConverter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.yotelopaso.YotelopasoUI;
import com.yotelopaso.domain.Career;
import com.yotelopaso.domain.PersonalInfo;
import com.yotelopaso.domain.User;
import com.yotelopaso.persistence.CareerManager;
import com.yotelopaso.persistence.UserManager;
import com.yotelopaso.views.implementations.templates.AbstractHomeViewImpl;

public class CompDatosView extends AbstractHomeViewImpl implements View {
	
	UserManager manUsuario = new UserManager();
	User usuario = new User();
	PersonalInfo persInfo = new PersonalInfo();
	private static final long serialVersionUID = 1L;

	public CompDatosView(){}
	
	public void enter(ViewChangeEvent event){
		super.enter(event);
		
		//
		//Primero vamos a crear un form que es donde el usuario va a cargar los datos y luego se va a crear el panel
		//en donde se va a colocar el form
		FormLayout form = new FormLayout();
		form.addStyleName("light");
		form.setWidth("500");
		form.setSpacing(true);
		
		rightLayout.addComponent(form);
		rightLayout.setExpandRatio(form, 1.0f);

		
				final DateField fecha = new DateField("Fecha de nacimiento");
		final CareerManager manager = new CareerManager();
		final ComboBox carr = new ComboBox("Carrera", manager.getContainer());
		carr.setItemCaptionPropertyId("name");
		//carr.addItems("Ingenieria en sistemas","Ingenieria química","Ingenieria eléctrica","Ingenieria mecánica");
		//El comboBox es el menu desplegable para elegir la carrera. En la documentacion esta la forma de como manejar
		//las seleccion para cuando necesitemos guardarlo en la base de datos
		final ComboBox taño = new ComboBox("Año");
		taño.addItems(1,2,3,4,5);
		carr.setNullSelectionAllowed(false);
		taño.setNullSelectionAllowed(false);
		carr.setTextInputAllowed(false);
		taño.setTextInputAllowed(false);
		taño.setRequired(true);
		carr.setRequired(true);
		carr.setRequiredError("Obligatorio");
		taño.setRequiredError("Obligatorio");
		
		
		
	
		
		//Se crea el textfield para teléfono asegurándose que lo ingresado sea un numero y no un string
		final TextField tel = new TextField("Teléfono");
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
		
		
		
		final TextField ciu = new TextField("Ciudad");
		form.addComponent(carr);
		form.addComponent(taño);
		form.addComponent(ciu);
		form.addComponent(tel);
		form.addComponent(fecha);
		
		
		Button aceptar = new Button("Aceptar",
				new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

		//@Override
		public void buttonClick(ClickEvent event) {
			if (carr.isValid() & taño.isValid()) {
				persInfo.setCity(ciu.getValue());
				persInfo.setPhone((Long)tel.getConvertedValue());
				usuario.setPersonalinfo(persInfo);
				usuario.setCareer( (Career) carr.getData());  
				usuario.setYear((Integer)taño.getValue()); 
				usuario.setBirthday(fecha.getValue()); 
				usuario.setEmail("nicohuvi@gmail.com");
				manUsuario.save(usuario);
				getUI().getNavigator().navigateTo(YotelopasoUI.HOME_VIEW);
				
				
				
			}
			else {
				
				Notification.show("Revise los campos obligatorios\n",
						"Los campos obligatorios son la carrera y el año que está cursando",
		                Notification.Type.WARNING_MESSAGE);
				
			}
			
		}
	}); //Aca se crea el boton aceptar que te envia a la Home
		Button cancelar = new Button("Cancelar",
				new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

		//@Override
		public void buttonClick(ClickEvent event) {

			getUI().getNavigator().navigateTo(YotelopasoUI.MAIN_VIEW);
		}
	}); //Lo mismo que el anterior pero para volver al Main

		
		
		HorizontalLayout botones= new HorizontalLayout();
		botones.addComponent(aceptar);
		botones.addComponent(cancelar);
		
		form.addComponent(botones);
		
		Panel panel = new Panel("Complete sus datos por favor");
		panel.setSizeFull();
		panel.setContent(form);
		rightLayout.addComponent(panel);
		rightLayout.setExpandRatio(panel, 1.0f);
		
		
		

	}
	


	class ButtonListener implements Button.ClickListener {

		private static final long serialVersionUID = 1L;
		String menuitem;
		
		public ButtonListener(String menuitem) {
			//this.menuitem = menuitem;
			this.menuitem = YotelopasoUI.HOME_VIEW;
		}

		@Override
		public void buttonClick(ClickEvent event) {
			// Navigate to a specific state
			UI.getCurrent().getNavigator().navigateTo(YotelopasoUI.MAIN_VIEW + menuitem);
		}
	}
		
	
	

}
