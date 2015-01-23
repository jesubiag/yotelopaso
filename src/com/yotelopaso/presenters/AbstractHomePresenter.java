package com.yotelopaso.presenters;

import com.vaadin.server.VaadinSession;
import com.yotelopaso.Vaadintest01UI;
import com.yotelopaso.views.AbstractHomeView;

public class AbstractHomePresenter<T extends AbstractHomeView> implements AbstractHomeView.AbstractHomeViewListener {
	
	T view;
	
	public AbstractHomePresenter(T view) {
		this.view = view;
		
		view.addListener(this);
	}

	@Override
	public void panelButtonClick(String caption) {
		switch (caption) {
		case "Inicio":
			view.navigate(Vaadintest01UI.HOME_VIEW);
			break;
		case "Materias":
			view.navigate(Vaadintest01UI.SUBJECTS_VIEW);
			break;
		case "Mi Calendario":
			view.navigate(Vaadintest01UI.CALENDAR_VIEW);
			break;
		case "Mi Actividad":
			//navigator.navigateTo(Vaadintest01UI.ACTIVITY_VIEW);
			break;
		case "Logout":
			//logout
			VaadinSession.getCurrent().setAttribute("userId", null);
			VaadinSession.getCurrent().setAttribute("currentUser", null);
			view.navigate(Vaadintest01UI.MAIN_VIEW);
			break;
		}
	}

}
