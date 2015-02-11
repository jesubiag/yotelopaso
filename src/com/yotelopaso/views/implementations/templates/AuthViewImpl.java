package com.yotelopaso.views.implementations.templates;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.yotelopaso.YotelopasoUI;
import com.yotelopaso.utils.UserUtils;
import com.yotelopaso.views.AuthView;

/**Vista abstracta que comprueba automáticamente si el usuario esta autentificado.
 * Redirige al usuario a la página principal en caso de no estarlo.
 */

abstract class AuthViewImpl extends VerticalLayout implements AuthView {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void enter(ViewChangeEvent event) {
		if ( !UserUtils.isLogged((Double) VaadinSession.getCurrent().getAttribute("userId")) ) {
			// Lo mejor seria que lo haga loguearse en vez de llevarlo a la pagina principal.
			navigate(YotelopasoUI.MAIN_VIEW);
		}
	}

	
	@Override
	final public void navigate(String viewName) {
		UI.getCurrent().getNavigator().navigateTo( viewName );
	}
	
	@Override
	final public void addWindow(Window window) {
		try {
			UI.getCurrent().addWindow(window);
		// TODO: mejorar sistema de mensajes de error
		} catch (IllegalArgumentException e) {	
			System.out.println( e.getMessage() );
		} catch (NullPointerException e) {
			System.out.println( e.getMessage() );
		}
		
	}
	
	@Override
	final public void closeWindow(Window window) {
		UI ui = UI.getCurrent();
		if (ui != null) {
			ui.removeWindow(window);
		}
	}
	
	@Override
	final public <T extends AbstractComponentContainer> Label addLine(final T component) {
		Label line = new Label("<hr/>", ContentMode.HTML);
		component.addComponent(line);
		return line;
	}
	
	@Override
	final public void openLink(String url, String windowName) {
		UI ui = UI.getCurrent();
		if (ui != null) {
			ui.getPage().open(url, windowName);
		}
	}

}
