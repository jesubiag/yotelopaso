package com.example.vaadintest01;

import org.scribe.builder.api.GoogleApi;
import org.vaadin.addon.oauthpopup.OAuthPopupButton;

import com.vaadin.server.ClassResource;

@SuppressWarnings("serial")
public class GoogleButton extends OAuthPopupButton {

	public GoogleButton(String key, String secret) {
		super(GoogleApi.class, key, secret);

		setIcon(new ClassResource("/org/vaadin/addon/oauthpopup/icons/twitter16.png"));
		setCaption("Google (y no Twitter, como podría confundir el iconito!)");
	}

}