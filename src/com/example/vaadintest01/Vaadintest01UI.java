package com.example.vaadintest01;

import javax.servlet.annotation.WebServlet;

import com.example.views.HomeView;
import com.example.views.MainView;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
//@Theme("vaadintest01")
@Theme("dawn")
@Push
public class Vaadintest01UI extends UI {
	
	public static final String PERSISTENCE_UNIT = "vaadintest";
	
	// UI Components declaration
	
	//Navigator
	Navigator nav;
	public static final String MAINVIEW = "";
	public static final String HOMEVIEW = "home";
	
	final VerticalLayout layout = new VerticalLayout();

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = Vaadintest01UI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		
		nav = new Navigator(this, this);
		nav.addView(MAINVIEW, new MainView());
		nav.addView(HOMEVIEW, new HomeView());
		
	}

}