package com.yotelopaso.components;

import java.text.NumberFormat;
import java.util.Locale;

import com.vaadin.data.util.converter.StringToLongConverter;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;
import com.yotelopaso.Vaadintest01UI;
import com.yotelopaso.domain.Career;
import com.yotelopaso.domain.PersonalInfo;
import com.yotelopaso.domain.User;
import com.yotelopaso.persistence.CareerManager;
import com.yotelopaso.persistence.UserManager;


public class RegWindow extends Window{
	
	private static final long serialVersionUID = 1L;
	
	private ComboBox carr;
	private ComboBox taño;
	private DateField fecha;
	private TextField tel;
	private TextField ciu;
	private TextField userName;
	private TextField userLastName;
	private Button aceptar;
	private Button cancelar;
	private Navigator navigator;
	private User usuario;
	private PersonalInfo persInfo;
	private StringToLongConverter convertidorLong= new StringToLongConverter();
	final private CareerManager carrManager = new CareerManager();
	final private UserManager userManager = new UserManager();
	boolean firstLogin;
	
	//Constructor para primer Login
	public RegWindow(){
		usuario = new User();
		persInfo = new PersonalInfo();
		firstLogin = true;
		buildMainLayout();	
	}
	
	//Constructor para editar datos personales
	public RegWindow(User user){
		this.usuario = user;
		firstLogin = false;
		
		buildMainLayout();			
		
		userName.setValue(usuario.getName());
		userLastName.setValue(usuario.getLastName());
		taño.setValue(usuario.getYear());		
		carr.setValue(usuario.getCareer().getId());
		persInfo=usuario.getPersonalinfo();
		
		if (usuario.getBirthday() != null)
			fecha.setValue(usuario.getBirthday());
		if (persInfo.getPhone() != null)
			tel.setValue(String.valueOf(persInfo.getPhone()));
		if (persInfo.getCity() != null)
			ciu.setValue(persInfo.getCity());
	}

	private void buildMainLayout(){
		FormLayout form = new FormLayout();
		//form.addStyleName("light");
		form.setSizeFull();
		form.setSpacing(true);
		
		fecha = new DateField("Fecha de nacimiento");
		fecha.setSizeFull();
		
		taño = new ComboBox("Año");
		taño.addItems(1,2,3,4,5);
		taño.setNullSelectionAllowed(false);		
		taño.setTextInputAllowed(false);
		taño.setRequired(true);
		taño.setRequiredError("Obligatorio");
		taño.setSizeFull();
		
		carr = new ComboBox("Carrera");
		carr.setNullSelectionAllowed(false);
		carr.setTextInputAllowed(false);
		carr.setRequired(true);
		carr.setRequiredError("Obligatorio");
		carr.setSizeFull();
		carr.setContainerDataSource(carrManager.getContainer());
		carr.setItemCaptionPropertyId("name");
		
		tel = new TextField("Teléfono");
		tel.setImmediate(true);
		tel.setSizeFull();
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
		
		ciu = new TextField("Ciudad");
		ciu.setSizeFull();
		
		
		userName = new TextField("Nombre");
		userName.setMaxLength(35);
		userName.setWidth("60%");
		userName.setImmediate(true);
		userName.setRequired(true);
		userName.setRequiredError("Obligatorio");
		userName.setSizeFull();
		
		userLastName = new TextField("Apellido");
		userLastName.setMaxLength(35);
		userLastName.setWidth("60%");
		userLastName.setImmediate(true);
		userLastName.setRequired(true);
		userLastName.setRequiredError("Obligatorio");
		userLastName.setSizeFull();
		
		form.addComponent(userName);
		form.addComponent(userLastName);
		form.addComponent(carr);
		form.addComponent(taño);
		form.addComponent(ciu);
		form.addComponent(tel);
		form.addComponent(fecha);
		
		navigator = UI.getCurrent().getNavigator();
		
		
		aceptar = new Button("Guardar", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			public void buttonClick(ClickEvent event) {
				if (carr.isValid() & taño.isValid()) {
					usuario.setName(userName.getValue());
					usuario.setLastName(userLastName.getValue());
					persInfo.setCity(ciu.getValue());
					persInfo.setPhone((Long)tel.getConvertedValue());
					Career c = carrManager.getById( (Integer) carr.getValue());
					usuario.setCareer(c);
					Integer y = (Integer)taño.getValue();
					usuario.setYear(y);
					usuario.setPersonalinfo(persInfo);
					usuario.setBirthday(fecha.getValue());
					userManager.setNewUserDefaultSubjects(usuario, c.getName(), y);
					
					usuario.setId(userManager.getCurrentUser().getId());
					usuario.setEmail(userManager.getCurrentUser().getEmail());
					userManager.save(usuario);
					userManager.setCurrentUser(usuario);
					navigator.navigateTo(Vaadintest01UI.HOME_VIEW);	
					close();
				}
				else {	
					Notification.show("Revise los campos obligatorios\n",
						"Los campos obligatorios son el nombre, apellido, la carrera y el año que está cursando", 
						Notification.Type.WARNING_MESSAGE);	
				}
			}
		});
		cancelar = new Button("Cancelar", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			public void buttonClick(ClickEvent event) {
				if (firstLogin) {
					VaadinSession.getCurrent().setAttribute("userId", null);
					VaadinSession.getCurrent().setAttribute("currentUser", null);
					navigator.navigateTo(Vaadintest01UI.MAIN_VIEW);
				} else
					navigator.navigateTo(Vaadintest01UI.HOME_VIEW);
				close();
			}
		});
		
		aceptar.setStyleName(ValoTheme.BUTTON_SMALL);
		cancelar.setStyleName(ValoTheme.BUTTON_SMALL);
		HorizontalLayout botones= new HorizontalLayout();
		botones.addComponent(aceptar);
		botones.addComponent(cancelar);
		form.addComponent(botones);
		
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.addComponent(form);
		vLayout.setMargin(true);
		
		setCaption("Complete sus datos por favor");
		setModal(true);
		setWidth("650px");
		setResizable(false);
		setContent(vLayout);
	}

}