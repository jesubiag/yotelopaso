package com.example.vaadintest01;

import javax.servlet.annotation.WebServlet;

import org.vaadin.addon.oauthpopup.OAuthListener;
import org.vaadin.addon.oauthpopup.OAuthPopupButton;
import org.vaadin.addon.oauthpopup.buttons.TwitterButton;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("vaadintest01")
@Push
public class Vaadintest01UI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = Vaadintest01UI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		Button button = new Button("Click Me");
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				layout.addComponent(new Label("Thank you for clicking"));
			}
		});
		
		//OAuthPopupButton ob = new TwitterButton("iIugjyTguxdu2a5TCtCNZTC8F", "wdZqjEzCTioc81Z5I3GUFydGu4bpvzm9HNwNA3mq1KJiKZtEPY");
		OAuthPopupButton ob = new GoogleButton("398023219009.apps.googleusercontent.com", "WjD6Dq0S5_19dw1KlBreZakL");


		ob.addOAuthListener(new OAuthListener() {
		@Override
		public void authSuccessful(String accessToken, String accessTokenSecret) {
		Notification.show("Authorized");
		// TODO: do something with the access token
		}

		@Override
		public void authDenied(String reason) {
		Notification.show("Authorization denied");
		}
		});

		layout.addComponent(ob);
		
		layout.addComponent(button);
	}

}