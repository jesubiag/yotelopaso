package com.example.views.templates;

import com.example.domain.User;
import com.example.utils.UserUtils;
import com.example.vaadintest01.Vaadintest01UI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**Vista abstracta que comprueba automáticamente si el usuario esta autentificado.
 * Redirige al usuario a la página principal en caso de no estarlo.
 */

public class AuthView extends VerticalLayout implements View {
	private static final long serialVersionUID = 1L;
	
	private User currentUser;

	@Override
	public void enter(ViewChangeEvent event) {
		if (!UserUtils.isLogged((Double) VaadinSession.getCurrent().getAttribute("userId"))) {
			// Lo mejor seria que lo haga loguearse en vez de llevarlo a la pagina principal.
			UI.getCurrent().getNavigator().navigateTo(Vaadintest01UI.MAIN_VIEW);
		} else {
			setCurrentUser( (User) VaadinSession.getCurrent().getAttribute("currentUser"));
		}
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

}
