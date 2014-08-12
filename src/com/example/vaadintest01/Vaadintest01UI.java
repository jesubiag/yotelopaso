package com.example.vaadintest01;

import javax.servlet.annotation.WebServlet;

import org.scribe.builder.api.GoogleApi;
import org.vaadin.addon.oauthpopup.OAuthListener;
import org.vaadin.addon.oauthpopup.OAuthPopupButton;
import org.vaadin.addon.oauthpopup.buttons.GitHubApi;
import org.vaadin.addon.oauthpopup.buttons.TwitterButton;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
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
	final VerticalLayout layout = new VerticalLayout();
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
	private static final ApiInfo GITHUB_API = new ApiInfo("GitHub",
			GitHubApi.class,
			"97a7e251c538106e7922",
			"6a36b0992e5e2b00a38c44c21a6e0dc8ae01d83b",
			"https://api.github.com/user");
	

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = Vaadintest01UI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		setContent(layout);
		layout.setMargin(true);
		layout.setSpacing(true);

		Button button = new Button("Click Me");
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				layout.addComponent(new Label("Thank you for clicking"));
			}
		});
		
		//OAuthPopupButton ob = new TwitterButton("iIugjyTguxdu2a5TCtCNZTC8F", "wdZqjEzCTioc81Z5I3GUFydGu4bpvzm9HNwNA3mq1KJiKZtEPY");
		
		// topHorizontalLayout
		topHorizontalLayout.addComponent(homeLink);
		addGoogleButton();
		//topHorizontalLayout.addComponent(ob);
		topHorizontalLayout.setMargin(true);
		
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
		layout.addComponent(topHorizontalLayout);
		layout.addComponent(appTitle);
		layout.addComponent(appDesc);
		layout.addComponent(midHorizontalLayout);
		layout.addComponent(button);
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
		button.setPopupWindowFeatures("resizable,width=400,height=300");

		HorizontalLayout hola = new HorizontalLayout();
		hola.setSpacing(true);
		hola.addComponent(button);

		layout.addComponent(hola);

		button.addOAuthListener(new Listener(service, hola));
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
			Button testButton = new Button("Test " + service.name + " API");
			testButton.addStyleName(BaseTheme.BUTTON_LINK);
			hola.addComponent(testButton);
			testButton.addClickListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					GetTestComponent get = new GetTestComponent(service,
							accessToken, accessTokenSecret);
					Window w = new Window(service.name, get);
					w.center();
					w.setWidth("75%");
					w.setHeight("75%");
					addWindow(w);
				}
			});
		}

		@Override
		public void authDenied(String reason) {
			hola.addComponent(new Label("Auth failed."));
		}
	}

}