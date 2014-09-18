package com.example.vaadintest01;

import javax.servlet.annotation.WebServlet;

import com.example.domain.User;
import com.example.persistence.UserManager;
import com.example.views.CompDatosView;
import com.example.views.EditorNoticiasView;
import com.example.views.HomeView;
import com.example.views.MainView;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("vaadintest01")
//@Theme("dawn")
@Push
@PreserveOnRefresh
public class Vaadintest01UI extends UI {
	
	private static final long serialVersionUID = 1L;

	public static final String PERSISTENCE_UNIT = "vaadintest";
	
	// UI Components declaration
	
	//Navigator
	Navigator nav;
	public static final String MAINVIEW = "";
	public static final String HOMEVIEW = "home";
	public static final String REGISTERVIEW = "register";
	public static final String EDITORVIEW = "editornoticias";
	
	final VerticalLayout layout = new VerticalLayout();

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = Vaadintest01UI.class)
	public static class Servlet extends VaadinServlet {
		private static final long serialVersionUID = 1L;
	}

	@Override
	protected void init(VaadinRequest request) {
		
		// datos de prueba
		User user1 = new User();
		user1.setId(13122717217802891560D);
		user1.setName("Jesus");
		user1.setLastName("Biaggioni");
		user1.setEmail("jesu772@gmail.com");
		UserManager.save(user1);
		//System.out.println(UserManager.isRegistered(user1.getId() - 456000000D));
		
		//getSession().setAttribute("userId", 0D);
		
		nav = new Navigator(this, this);
		nav.addView(MAINVIEW, new MainView());
		nav.addView(HOMEVIEW, new HomeView());
		nav.addView(REGISTERVIEW, new CompDatosView());
		nav.addView(EDITORVIEW, new EditorNoticiasView());
		nav.navigateTo(MAINVIEW);
		
	}

}