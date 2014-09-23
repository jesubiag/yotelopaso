package com.example.views;

import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.GoogleApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import org.vaadin.addon.oauthpopup.OAuthListener;
import org.vaadin.addon.oauthpopup.OAuthPopupButton;

import com.example.domain.User;
import com.example.persistence.UserManager;
import com.example.utils.ApiInfo;
import com.example.utils.UserUtils;
import com.example.vaadintest01.GoogleButton;
import com.example.vaadintest01.Vaadintest01UI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

public class MainView extends VerticalLayout implements View {
	
	private static final long serialVersionUID = 1L;
	
	UserManager userManager = new UserManager();
	private User currentUser;
	ApiInfo api = GOOGLE_API;
	final HorizontalLayout topHorizontalLayout = new HorizontalLayout();
	Label bienvenida = new Label("<h1><b>Plataforma Estudiantil Integrada</b></h1>");
	Link homeLink = new Link("Home", new ExternalResource("/VaadinTest01/#!home"));
	/*Label appTitle = new Label("<h1><b></b></h1>");*/
	Label appDesc = new Label("Todo lo que necesitas en un solo lugar");
	final HorizontalLayout midHorizontalLayout = new HorizontalLayout();
	final VerticalLayout vl1 = new VerticalLayout();
	final VerticalLayout vl2 = new VerticalLayout();
	final VerticalLayout vl3 = new VerticalLayout();
	Image vl1Image = new Image();
	Image vl2Image = new Image();
	Image vl3Image = new Image();
	final ExternalResource vl1ExtRes = new ExternalResource("" +
			"https://photos-2.dropbox.com/t/0/AAC38UuCYEDpILSRtn32J7deoXxnohV3YetmOWL3wLnptQ/12/70181720/png/320x320/1/_/0/4/pensar.png/fczf4dxwglk62k9/AADIIgM_Wr6YdMQSKo6Im-U5a/pensar.png");
	final ExternalResource vl2ExtRes = new ExternalResource("" +
			"https://photos-2.dropbox.com/t/0/AABSf7Csxwx5WhpiIfcuYX955AL5oSTwtapxQY2wY0GySQ/12/70181720/png/1024x768/3/1410991200/0/2/Google_Calendar_Logo-282x300.png/JSFjdWzr9UN7emmL4qCS5K-fJV9wmPPOgu9uW3_F7J4");
	final ExternalResource vl3ExtRes = new ExternalResource("" +
			"https://photos-2.dropbox.com/t/0/AACG4QB8Hn3woZR6FwQfPUEeD6rS0gZs7cKMZptCPEO-7Q/12/70181720/jpeg/320x320/1/_/0/4/share.jpg/qsqj9w14vk0gzgx/AACoukyHvrIkDr4KrLLcqeK-a/share.jpg");
	Label vl1Desc = new Label("La aplicacion que te ayuda a estudiar" +
			"" +
			"");
	Label vl2Desc = new Label("Sincroniza tu Google Calendar con tus eventos de la Facultad" +
			"" +
			"");
	Label vl3Desc = new Label("Es tiempo de compartir! " +
			" " +
			"");
	
	private static String exampleGetRequest = "https://www.googleapis.com/drive/v2/about?key=";
	//private static String exampleGetRequest = "https://www.googleapis.com/plus/v1/people/me?key=";
	private static String SCOPE = "https://www.googleapis.com/auth/drive";
	//private static String SCOPE = "https://www.googleapis.com/auth/plus.login";
	private static String REQ_URL = "https://www.googleapis.com/drive/v2/about?fields=permissionId,name,user/emailAddress";
	//private static String REQ_URL = "https://www.googleapis.com/plus/v1/people/me?key=";
	
	private static final ApiInfo GOOGLE_API = new ApiInfo("Google",
			GoogleApi.class,
			"398023219009.apps.googleusercontent.com",
			"WjD6Dq0S5_19dw1KlBreZakL",
			exampleGetRequest);
	
	public MainView() {
		
		setSpacing(true);
		//setSizeFull();
		// topHorizontalLayout
		topHorizontalLayout.setMargin(true);
		topHorizontalLayout.setWidth("100%");
		bienvenida.setContentMode(ContentMode.HTML);
		addComponent(bienvenida);
		bienvenida.setId("bien");
		//setComponentAlignment(bienvenida, Alignment.TOP_CENTER);
	
		
		// Google Button
		OAuthPopupButton button = new GoogleButton(api.apiKey, api.apiSecret);
		button.setScope(SCOPE);
		button.setPopupWindowFeatures("resizable,width=800,height=600");
		button.setId("acceder");
		topHorizontalLayout.addComponent(button);
		topHorizontalLayout.setComponentAlignment(button, Alignment.MIDDLE_RIGHT);
		button.addStyleName(BaseTheme.BUTTON_LINK);
		button.addOAuthListener(new Listener(api));
		
		// appTitle
		//appTitle.setContentMode(ContentMode.HTML);
		
		// Images
		vl1Image.setSource(vl1ExtRes);
		vl2Image.setSource(vl2ExtRes);
		vl3Image.setSource(vl3ExtRes);
		
		// mid vertical layouts
		vl1.setWidth("200px");
		vl1.addComponent(vl1Image);
		vl1Image.setWidth("200px");
		vl1Image.setHeight("200px");
		vl1.addComponent(vl1Desc);
		vl2.addComponent(vl2Image);
		vl2.setWidth("200px");
		vl2Image.setHeight("200px");
		vl2Image.setWidth("200px");
		vl2.addComponent(vl2Desc);
		vl3.setWidth("200px");
		vl3.addComponent(vl3Image);
		vl3.addComponent(vl3Desc);
		vl3Image.setWidth("200px");
		vl3Image.setHeight("200px");
		
		// midHorizontalLayout
		midHorizontalLayout.addComponent(vl1);
		midHorizontalLayout.addComponent(vl2);
		midHorizontalLayout.addComponent(vl3);
		midHorizontalLayout.setMargin(true);
		midHorizontalLayout.setWidth(null);
		midHorizontalLayout.setSpacing(true);
		
		// layout
		addComponent(topHorizontalLayout);
		topHorizontalLayout.setId("layoutAcceder");
		//setComponentAlignment(topHorizontalLayout, Alignment.TOP_CENTER);
		//appTitle.setWidth(null);
		//addComponent(appTitle);
		//setComponentAlignment(appTitle, Alignment.MIDDLE_CENTER);
		addComponent(appDesc);
		appDesc.setWidth(null);
		setComponentAlignment(appDesc, Alignment.MIDDLE_CENTER);
		addComponent(midHorizontalLayout);
		setComponentAlignment(midHorizontalLayout, Alignment.TOP_CENTER);
		
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// Hacer algo
		if (UserUtils.isLogged(VaadinSession.getCurrent())) {
			UI.getCurrent().getNavigator().navigateTo(Vaadintest01UI.HOME_VIEW);
		}
	}

	private class Listener implements OAuthListener {

		private final ApiInfo service;

		private Listener(ApiInfo service) {
			this.service = service;
		}

		@Override
		public void authSuccessful(final String accessToken,
				final String accessTokenSecret) {
			
			/* la url de consulta debe incluir el token y debe tener el siguiente formato
			 * ej: https://www.googleapis.com/drive/v2/about/?access_token=<accessToken>
			 */
			
			OAuthRequest request = new OAuthRequest(Verb.GET, REQ_URL + "&access_token=" + accessToken);
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
			
			// Chequeo si el usuario está registrado, y dependiendo el caso tomo diferentes acciones
			if (userManager.isRegistered(userId)) {
				VaadinSession.getCurrent().setAttribute("userId", userId);
				setCurrentUser(userManager.getById(userId));
				VaadinSession.getCurrent().setAttribute("currentUser", getCurrentUser());
				UI.getCurrent().getNavigator().navigateTo(Vaadintest01UI.HOME_VIEW);
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
					setCurrentUser(newUser);
					userManager.save(newUser);
					VaadinSession.getCurrent().setAttribute("currentUser", getCurrentUser());
					getUI().getNavigator().navigateTo(Vaadintest01UI.REGISTER_VIEW);
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
			sb.callback("http://www.google.com");
			return sb.build();
		}

		@Override
		public void authDenied(String reason) {
			//hola.addComponent(new Label("Auth failed."));
		}
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

}
