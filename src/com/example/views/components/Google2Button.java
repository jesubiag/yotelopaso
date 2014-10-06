package com.example.views.components;

import org.scribe.builder.api.Google2Api;
import org.vaadin.addon.oauthpopup.OAuthPopupButton;

import com.example.presenters.Google2ButtonListener;
import com.example.utils.Google2ApiInfo;


@SuppressWarnings("serial")
public class Google2Button extends OAuthPopupButton {

	private static String SCOPE = "https://www.googleapis.com/auth/drive";
	//private static String SCOPE = "https://www.googleapis.com/auth/plus.login";
	
	private final Google2ApiInfo api = new Google2ApiInfo();
	

	public Google2Button(String key, String secret) {
		super(Google2Api.class, key, secret);

		setScope(SCOPE);
		setCaption("Acceder");
		addOAuthListener(new Google2ButtonListener(api));
		
	}

}