package com.yotelopaso.presenters;

import com.vaadin.server.VaadinSession;
import com.yotelopaso.YotelopasoUI;
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
			view.navigate(YotelopasoUI.HOME_VIEW);
			break;
		case "Materias":
			view.navigate(YotelopasoUI.SUBJECTS_VIEW);
			break;
		case "Mi Calendario":
			view.navigate(YotelopasoUI.CALENDAR_VIEW);
			break;
		case "Suscripciones":
			view.navigate(YotelopasoUI.SUBSCRIPTIONS_VIEW);
			break;
		case "Logout":
			//logout
			VaadinSession.getCurrent().setAttribute("userId", null);
			VaadinSession.getCurrent().setAttribute("currentUser", null);
			view.navigate(YotelopasoUI.MAIN_VIEW);
			break;
		}
	}

}
