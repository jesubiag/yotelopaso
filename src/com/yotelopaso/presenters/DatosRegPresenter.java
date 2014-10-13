package com.yotelopaso.presenters;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.yotelopaso.Vaadintest01UI;
import com.yotelopaso.domain.Career;
import com.yotelopaso.domain.PersonalInfo;
import com.yotelopaso.domain.User;
import com.yotelopaso.persistence.CareerManager;
import com.yotelopaso.persistence.UserManager;
import com.yotelopaso.views.DatosReg;


public class DatosRegPresenter extends AbstractHomePresenter<DatosReg> implements DatosReg.DatosRegListener  {
	DatosReg view;
	UserManager manager;
	CareerManager carrManager;
	User usuario;
	PersonalInfo persInfo;


	public DatosRegPresenter(DatosReg view, UserManager service, CareerManager carrManager) {
		super(view, service);
		
		this.view = view;
		this.manager = service;
		this.carrManager = carrManager;
		view.addListener(this);
	}

	@Override
	public void componentAttachedToContainer(String caption) {
		switch (caption){
		case "Complete sus datos por favor":
			view.getCarr().setContainerDataSource(carrManager.getContainer());
			view.getCarr().setItemCaptionPropertyId("name");
			break;
	}

}

	@Override
	public void buttonClick(String caption) {
		Navigator navigator = UI.getCurrent().getNavigator();
		switch (caption){
		case "Aceptar":
			usuario = new User();
			persInfo = new PersonalInfo();
			ComboBox carr = view.getCarr();
			ComboBox taño = view.getTaño();
			TextField ciu = view.getCiu();
			TextField tel = view.getTel();
			DateField fecha = view.getFecha();
			
			if (carr.isValid() & taño.isValid()) {
				usuario.setId(manager.getCurrentUser().getId());
				usuario.setEmail(manager.getCurrentUser().getEmail());
				persInfo.setCity(ciu.getValue());
				persInfo.setPhone((Long)tel.getConvertedValue());
				usuario.setPersonalinfo(persInfo);
				usuario.setCareer( carrManager.getById( (Integer) carr.getValue()));
				usuario.setYear((Integer)taño.getValue()); 
				usuario.setBirthday(fecha.getValue()); 
				manager.save(usuario);
				manager.setCurrentUser(usuario);
				navigator.navigateTo(Vaadintest01UI.HOME_VIEW);	
			}
			else {	
				Notification.show("Revise los campos obligatorios\n",
					"Los campos obligatorios son la carrera y el año que está cursando", 
					Notification.Type.WARNING_MESSAGE);	
			}
			break;
		case "Cancelar":
			VaadinSession.getCurrent().setAttribute("userId", null);
			VaadinSession.getCurrent().setAttribute("currentUser", null);
			navigator.navigateTo(Vaadintest01UI.MAIN_VIEW);
			break;
		}
		
	}
}