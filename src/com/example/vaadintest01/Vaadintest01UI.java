package com.example.vaadintest01;

import javax.servlet.annotation.WebServlet;

import org.scribe.builder.api.GoogleApi;
import org.vaadin.addon.oauthpopup.OAuthListener;
import org.vaadin.addon.oauthpopup.OAuthPopupButton;
import org.vaadin.addon.oauthpopup.buttons.GitHubApi;
import org.vaadin.addon.oauthpopup.buttons.TwitterButton;

import com.example.views.HomeView;
import com.example.views.MainView;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
//@Theme("vaadintest01")
@Theme("dawn")
@Push
public class Vaadintest01UI extends UI {
	
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