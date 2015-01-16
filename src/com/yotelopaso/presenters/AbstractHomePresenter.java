package com.yotelopaso.presenters;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import com.yotelopaso.Vaadintest01UI;
import com.yotelopaso.persistence.UserManager;
import com.yotelopaso.views.AbstractHomeView;

public class AbstractHomePresenter<T extends AbstractHomeView> implements AbstractHomeView.AbstractHomeViewListener {
	
	T view;
	UserManager service;
	
	public AbstractHomePresenter(T view, UserManager service) {
		this.view = view;
		this.service = service;
		
		view.addListener(this);
	}

	@Override
	public void panelButtonClick(String caption) {
		Navigator navigator = UI.getCurrent().getNavigator();
		switch (caption) {
		case "Inicio":
			navigator.navigateTo(Vaadintest01UI.HOME_VIEW);
			break;
		case "Materias":
			navigator.navigateTo(Vaadintest01UI.SUBJECTS_VIEW);
			break;
		case "Mi Calendario":
			navigator.navigateTo(Vaadintest01UI.CALENDAR_VIEW);
			break;
		case "Mi Actividad":
			//navigator.navigateTo(Vaadintest01UI.ACTIVITY_VIEW);
			break;
		case "Logout":
			//logout
			VaadinSession.getCurrent().setAttribute("userId", null);
			VaadinSession.getCurrent().setAttribute("currentUser", null);
			navigator.navigateTo(Vaadintest01UI.MAIN_VIEW);
			break;
		}
	}

}
