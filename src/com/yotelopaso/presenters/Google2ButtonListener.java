package com.yotelopaso.presenters;

import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import org.vaadin.addon.oauthpopup.OAuthListener;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import com.yotelopaso.Vaadintest01UI;
import com.yotelopaso.components.RegWindow;
import com.yotelopaso.domain.User;
import com.yotelopaso.persistence.UserManager;
import com.yotelopaso.utils.Google2ApiInfo;

public class Google2ButtonListener implements OAuthListener {
	
	private final Google2ApiInfo service;
	
	UserManager userManager = new UserManager();
	
	Navigator navigator = UI.getCurrent().getNavigator();
	
	private static final String REQ_URL = "https://www.googleapis.com/drive/v2/about?fields=permissionId,name,user/emailAddress";
	//private static String REQ_URL = "https://www.googleapis.com/plus/v1/people/me?key=";
	
	//private static final String exampleGetRequest = "https://www.googleapis.com/drive/v2/about?key=";
	//private static String exampleGetRequest = "https://www.googleapis.com/plus/v1/people/me?key=";
	

	public Google2ButtonListener(Google2ApiInfo service) {
		this.service = service;
	}

	@Override
	public void authSuccessful(final String accessToken,
			final String accessTokenSecret) {
		
		/* la url de consulta debe incluir el token y debe tener el siguiente formato
		 * ej: https://www.googleapis.com/drive/v2/about/?access_token=<accessToken>
		 */
		
		OAuthRequest request = new OAuthRequest(Verb.GET, REQ_URL + "&access_token=" + accessToken + "&state=1");
		createOAuthService().signRequest(new Token(accessToken, accessTokenSecret), request);
		Response resp = request.send();
		
		// Extraigo los datos del JSON enviado por Google
		JSONObject jsonGoogle = null;
		Double userId = null;
		try {
			jsonGoogle = new JSONObject(resp.getBody());
			userId = jsonGoogle.getDouble("permissionId");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (userManager.isRegistered(userId)) {
			VaadinSession.getCurrent().setAttribute("userId", userId);
			User currentUser = userManager.getById(userId);
			//userManager.setCurrentUser(currentUser);
			VaadinSession.getCurrent().setAttribute("currentUser", currentUser);
			navigator.navigateTo(Vaadintest01UI.HOME_VIEW);
		} else {
			String name = null;
			String email = null;
			try {
				name = jsonGoogle.getString("name");
				email = jsonGoogle.getJSONObject("user").getString("emailAddress");
				// persistir los datos nuevos en la base de datos
				User newUser = new User();
				newUser.setId(userId);
				newUser.setName(name);
				newUser.setEmail(email);
				VaadinSession.getCurrent().setAttribute("currentUser", newUser);
				RegWindow regWindow = new RegWindow(newUser);
				UI.getCurrent().addWindow(regWindow);
				//navigator.navigateTo(Vaadintest01UI.REGISTER_VIEW);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private OAuthService createOAuthService() {
		ServiceBuilder sb = new ServiceBuilder();
		sb.provider(service.scribeApi);
		sb.apiKey(service.apiKey);
		sb.apiSecret(service.apiSecret);
		sb.callback("urn:ietf:wg:oauth:2.0:oob");
		return sb.build();
	}

	@Override
	public void authDenied(String reason) {
		//hola.addComponent(new Label("Auth failed."));
	}

}
