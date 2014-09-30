package com.example.presenters;

import com.example.persistence.UserManager;
import com.example.vaadintest01.Vaadintest01UI;
import com.example.views.AbstractHomeView;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.UI;

public class AbstractHomePresenter<T extends AbstractHomeView> implements AbstractHomeView.AbstractHomeViewListener {
	
	T view;
	UserManager service;
	
	public AbstractHomePresenter(T view, UserManager service) {
		this.view = view;
		this.service = service;
		
		view.addListener(this);
	}

	@Override
	public void buttonClick(String caption) {
		Navigator navigator = UI.getCurrent().getNavigator();
		switch (caption) {
		case "Inicio":
			navigator.navigateTo(Vaadintest01UI.HOME_VIEW);
			break;
		case "Materias":
			navigator.navigateTo(Vaadintest01UI.SUBJECTS_VIEW);
			break;
		case "Mi Calendario":
			//navigator.navigateTo(Vaadintest01UI.CALENDAR_VIEW);
			break;
		case "Mi Actividad":
			//navigator.navigateTo(Vaadintest01UI.ACTIVITY_VIEW);
			break;
		case "Logout":
			//logout
			break;
		}
	}

}
