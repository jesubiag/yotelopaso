package com.yotelopaso.views.templates;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.yotelopaso.Vaadintest01UI;
import com.yotelopaso.domain.User;
import com.yotelopaso.utils.UserUtils;

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
