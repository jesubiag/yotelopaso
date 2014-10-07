package com.example.presenters;

import com.example.domain.Career;
import com.example.domain.PersonalInfo;
import com.example.domain.User;
import com.example.persistence.CareerManager;
import com.example.persistence.UserManager;
import com.example.vaadintest01.Vaadintest01UI;
import com.example.views.DatosReg;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;


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
				usuario.setCareer( (Career) carr.getData());  
				usuario.setYear((Integer)taño.getValue()); 
				usuario.setBirthday(fecha.getValue()); 
				manager.save(usuario);
				navigator.navigateTo(Vaadintest01UI.HOME_VIEW);	
			}
			else {	
				Notification.show("Revise los campos obligatorios\n",
					"Los campos obligatorios son la carrera y el año que está cursando",
		                Notification.Type.WARNING_MESSAGE);	
			}
			break;
		
		case "Cancelar":
			navigator.navigateTo(Vaadintest01UI.MAIN_VIEW);
			break;
		}
		
	}
}