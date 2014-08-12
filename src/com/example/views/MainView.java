package com.example.views;

import org.scribe.builder.api.GoogleApi;
import org.vaadin.addon.oauthpopup.OAuthListener;
import org.vaadin.addon.oauthpopup.OAuthPopupButton;

import com.example.vaadintest01.ApiInfo;
import com.example.vaadintest01.GoogleButton;
import com.example.vaadintest01.Vaadintest01UI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.BaseTheme;

public class MainView extends VerticalLayout implements View {
	
	final HorizontalLayout topHorizontalLayout = new HorizontalLayout();
	Link homeLink = new Link("Home", new ExternalResource("http://localhost:8080/VaadinTest01/"));
	Label appTitle = new Label("<b>Nombre de la Aplicación</b>");
	Label appDesc = new Label("Descripción de la Aplicacón");
	final HorizontalLayout midHorizontalLayout = new HorizontalLayout();
	final VerticalLayout vl1 = new VerticalLayout();
	final VerticalLayout vl2 = new VerticalLayout();
	final VerticalLayout vl3 = new VerticalLayout();
	Image vl1Image = new Image();
	Image vl2Image = new Image();
	Image vl3Image = new Image();
	final ExternalResource vl1ExtRes = new ExternalResource("" +
			"http://i.imgur.com/VV64GUk.png");
	final ExternalResource vl2ExtRes = new ExternalResource("" +
			"http://i.imgur.com/8HHQxDA.png");
	final ExternalResource vl3ExtRes = new ExternalResource("" +
			"http://i.imgur.com/k5qFjSj.png");
	Label vl1Desc = new Label("Lorem ipsum dolor sit amet, consectetur adipiscing " +
			"elit. Integer in tortor ac velit cursus dictum id non libero. Quisque " +
			"condimentum iaculis est id varius. In hac habitasse platea dictumst");
	Label vl2Desc = new Label("Lorem ipsum dolor sit amet, consectetur adipiscing " +
			"elit. Integer in tortor ac velit cursus dictum id non libero. Quisque " +
			"condimentum iaculis est id varius. In hac habitasse platea dictumst");
	Label vl3Desc = new Label("Lorem ipsum dolor sit amet, consectetur adipiscing " +
			"elit. Integer in tortor ac velit cursus dictum id non libero. Quisque " +
			"condimentum iaculis est id varius. In hac habitasse platea dictumst");
	private static final ApiInfo GOOGLE_API = new ApiInfo("Google",
			GoogleApi.class,
			"398023219009.apps.googleusercontent.com",
			"WjD6Dq0S5_19dw1KlBreZakL",
			"https://www.googleapis.com/drive/v2/about?key=");
	
	public MainView() {
		
		setMargin(true);
		setSpacing(true);
		
		// topHorizontalLayout
		topHorizontalLayout.setMargin(true);
		topHorizontalLayout.setSizeFull();
		topHorizontalLayout.addComponent(homeLink);
		addGoogleButton();
		
		// appTitle
		appTitle.setContentMode(ContentMode.HTML);
		
		// Images
		vl1Image.setSource(vl1ExtRes);
		vl2Image.setSource(vl2ExtRes);
		vl3Image.setSource(vl3ExtRes);
		
		// mid vertical layouts
		vl1.setWidth("200px");
		vl1.addComponent(vl1Image);
		vl1.addComponent(vl1Desc);
		vl2.setWidth("200px");
		vl2.addComponent(vl2Image);
		vl2.addComponent(vl2Desc);
		vl3.setWidth("200px");
		vl3.addComponent(vl3Image);
		vl3.addComponent(vl3Desc);
		
		// midHorizontalLayout
		midHorizontalLayout.addComponent(vl1);
		midHorizontalLayout.addComponent(vl2);
		midHorizontalLayout.addComponent(vl3);
		midHorizontalLayout.setMargin(true);
		
		// layout
		addComponent(topHorizontalLayout);
		addComponent(appTitle);
		addComponent(appDesc);
		addComponent(midHorizontalLayout);
		
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// Hacer algo
	}
	
	private void addGoogleButton() {
		ApiInfo api = GOOGLE_API;
		OAuthPopupButton button = new GoogleButton(api.apiKey, api.apiSecret);
		button.setScope("https://www.googleapis.com/auth/drive");
		addButton(api, button);
	}
	
	private void addButton(final ApiInfo service, OAuthPopupButton button) {

		// In most browsers "resizable" makes the popup
		// open in a new window, not in a tab.
		// You can also set size with eg. "resizable,width=400,height=300"
		button.setPopupWindowFeatures("resizable,width=800,height=600");
		topHorizontalLayout.addComponent(button);
		topHorizontalLayout.setComponentAlignment(button, Alignment.MIDDLE_RIGHT);
		button.addOAuthListener(new Listener(service, topHorizontalLayout));
	}

	private class Listener implements OAuthListener {

		private final ApiInfo service;
		private final HorizontalLayout hola;

		private Listener(ApiInfo service, HorizontalLayout hola) {
			this.service = service;
			this.hola = hola;
		}

		@Override
		public void authSuccessful(final String accessToken,
				final String accessTokenSecret) {
			hola.addComponent(new Label("Authorized."));
			getUI().getNavigator().navigateTo(Vaadintest01UI.HOMEVIEW);
			//getUI().getNavigator().navigateTo(Vaadintest01UI.HOMEVIEW + "/");
			
		}

		@Override
		public void authDenied(String reason) {
			hola.addComponent(new Label("Auth failed."));
		}
	}

}
