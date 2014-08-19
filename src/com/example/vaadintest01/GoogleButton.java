package com.example.vaadintest01;

import org.scribe.builder.api.Google2Api;
import org.vaadin.addon.oauthpopup.OAuthPopupButton;

import com.vaadin.server.ClassResource;
import com.vaadin.ui.themes.BaseTheme;

@SuppressWarnings("serial")
public class GoogleButton extends OAuthPopupButton {

	public GoogleButton(String key, String secret) {
		super(Google2Api.class, key, secret);

		setCaption("Acceder");
		addStyleName(BaseTheme.BUTTON_LINK);
	}

}