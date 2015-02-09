package com.yotelopaso.views.implementations;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.yotelopaso.Vaadintest01UI;
import com.yotelopaso.components.Google2Button;
import com.yotelopaso.utils.Google2ApiInfo;
import com.yotelopaso.utils.UserUtils;
import com.yotelopaso.views.MainView;

public class MainViewImpl extends VerticalLayout implements MainView {
	
	private static final long serialVersionUID = 1L;
	
	private final Google2ApiInfo api = new Google2ApiInfo();
	
	final HorizontalLayout topHorizontalLayout = new HorizontalLayout();
	Label bienvenida = new Label("<h1><b>Yo te lo paso!</b></h1>");
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
			"http://i.imgur.com/nL6W6Na.png");
	final ExternalResource vl3ExtRes = new ExternalResource("" +
			"https://photos-2.dropbox.com/t/0/AACG4QB8Hn3woZR6FwQfPUEeD6rS0gZs7cKMZptCPEO-7Q/12/70181720/jpeg/320x320/1/_/0/4/share.jpg/qsqj9w14vk0gzgx/AACoukyHvrIkDr4KrLLcqeK-a/share.jpg");
	Label vl1Desc = new Label("La aplicacion que te ayuda a estudiar");
	Label vl2Desc = new Label("Sincroniza tu Google Calendar con tus eventos de la Facultad");
	Label vl3Desc = new Label("Es tiempo de compartir! ");
	
	public MainViewImpl() {
		
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
		Google2Button gButton = new Google2Button(api.apiKey, api.apiSecret);
		gButton.setPopupWindowFeatures("resizable,width=800,height=600");
		gButton.setId("acceder");
		gButton.setIcon(FontAwesome.GOOGLE);
		topHorizontalLayout.addComponent(gButton);
		topHorizontalLayout.setComponentAlignment(gButton, Alignment.MIDDLE_RIGHT);
		
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
		vl1.setId("vl1");
		midHorizontalLayout.addComponent(vl2);
		vl2.setId("vl2");
		midHorizontalLayout.addComponent(vl3);
		vl3.setId("vl3");
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
		if ( UserUtils.isLogged( (Double) VaadinSession.getCurrent().getAttribute("userId")) ) {
			UI.getCurrent().getNavigator().navigateTo(Vaadintest01UI.HOME_VIEW);
		}
	}

}
