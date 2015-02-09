package com.yotelopaso.views.implementations.templates;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.yotelopaso.Vaadintest01UI;
import com.yotelopaso.components.RegWindow;
import com.yotelopaso.persistence.UserManager;
import com.yotelopaso.views.AbstractHomeView;


abstract public class AbstractHomeViewImpl extends AuthViewImpl implements AbstractHomeView, ClickListener {
	
	private static final long serialVersionUID = 1L;
	protected VerticalLayout rightLayout;
	private HorizontalLayout hLayout;
	private Button buttonHome;
	private Button buttonSubjects;
	private Button buttonCalendar;
	private UserManager userManager = new UserManager();
	private VerticalLayout menuContent = new VerticalLayout();
	
	
	public AbstractHomeViewImpl() {
		
		setSizeFull();
		
		// Layout with menu on left and view area on right
		hLayout = new HorizontalLayout();
		hLayout.setSizeFull();
		
		// Empty layout on right
		rightLayout = new VerticalLayout();
		rightLayout.setSizeFull();

		// Have a menu on the left side of the screen <b>Yo Te Lo Paso</b>
		Panel menu = new Panel("");
		menu.setId("menu-panel");
		menu.setHeight("100%");
		menu.setWidth("182px");
			
		menu.setContent(menuContent);
		
		menuContent.addComponent(buildMenuButtons());
		
		hLayout.addComponent(menu);		
		hLayout.addComponent(rightLayout);
		
		addComponent(hLayout);
		setExpandRatio(hLayout, 1.0f);
		hLayout.setExpandRatio(rightLayout, 1.0f);
		
	}
	
	private Component buildMenuButtons() {
		String[] buttonCaptions = new String[] {"Inicio", "Materias", "Mi Calendario", 
		"Suscripciones"};
		String[] buttonIds = new String[] {"buttonHome", "buttonSubjects", "buttonCalendar", 
				"buttonSubscriptions"};
		FontAwesome[] buttonIcons = new FontAwesome[] {FontAwesome.HOME, FontAwesome.BOOK, 
				FontAwesome.CALENDAR, FontAwesome.RSS};
		String buttonStyles = ValoTheme.BUTTON_LINK + " " + ValoTheme.BUTTON_ICON_ALIGN_TOP + " " + "panelLink";
		
		VerticalLayout menuButtons = new VerticalLayout();
		
		buttonHome = new Button();
		buttonSubjects = new Button();
		buttonCalendar = new Button();
		
		Button[] panelButtons = new Button[] {buttonHome, buttonSubjects, buttonCalendar};
		
		for (int i = 0; i < 3; i++) {
			panelButtons[i].setCaption(buttonCaptions[i]);
			panelButtons[i].setId(buttonIds[i]);
			panelButtons[i].addStyleName(buttonStyles);
			panelButtons[i].setWidth("100%");
			panelButtons[i].setIcon(buttonIcons[i]);
			panelButtons[i].addClickListener(this);
			menuButtons.addComponent(panelButtons[i]);
		}
		
		menuButtons.setWidth("100%");
		menuButtons.setMargin(false);
		menuButtons.setSpacing(false);
		//menuContent.setStyleName(ValoTheme.lay)
		return menuButtons;
	}

	List<AbstractHomeViewListener> listeners = new ArrayList<AbstractHomeViewListener>();
	
	public void addListener(AbstractHomeViewListener listener) {
		listeners.add(listener);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		for (AbstractHomeViewListener listener : listeners) {
			listener.panelButtonClick(event.getButton().getCaption());
		}
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
		menuContent.removeAllComponents();	
		menuContent.addComponent(buildUserMenu());
		menuContent.addComponent(buildMenuButtons());
		
		rightLayout.removeAllComponents();
	}

	@Override
	public Component buildTitle(String frase) {
        Label logo = new Label(frase,
                ContentMode.HTML);
        logo.setSizeUndefined();
        HorizontalLayout logoWrapper = new HorizontalLayout(logo);
        logoWrapper.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
        logoWrapper.addStyleName("valo-menu-title");
        setStyleName("user-test");
        return logoWrapper;
    }
	
	private Component buildUserMenu() {
		VerticalLayout userLayout = new VerticalLayout();
		userLayout.setSpacing(false);
		userLayout.addComponent(buildTitle("Yo te lo <b>paso</b>"));
		
		MenuItem settingsItem;
		
		MenuBar settings = new MenuBar();
        settings.setHeight("70%");
        settings.addStyleName("user-menu");
        settings.addStyleName("dashboard");
        settings.addStyleName("user-test");
        if (userManager.getCurrentUser().getPersonalinfo().getAvatar() == null)
        	settingsItem = settings.addItem("", new ExternalResource("http://i.imgur.com/6I4EhvN.jpg"), null);
        else
        	settingsItem = settings.addItem("", new ExternalResource(userManager.getCurrentUser().getPersonalinfo().getAvatar()), null);
        settingsItem.addItem("Editar Perfil", new Command() {
			private static final long serialVersionUID = 8978144534249397207L;

			@Override
            public void menuSelected(final MenuItem selectedItem) {
                //ProfilePreferencesWindow.open(user, false);
            	//navigate(Vaadintest01UI.HOME_VIEW);
            	RegWindow regWindow = new RegWindow();
            	addWindow(regWindow);
            }
        });
        settingsItem.setText(userManager.getCurrentUser().getName() + " " + userManager.getCurrentUser().getLastName());
        settingsItem.addItem("Mis Suscripciones", new Command() {
			private static final long serialVersionUID = -4042646371962008523L;

			@Override
            public void menuSelected(final MenuItem selectedItem) {
                navigate(Vaadintest01UI.SUBSCRIPTIONS_VIEW);
            	//ProfilePreferencesWindow.open(user, true);
            }
        });
        settingsItem.addSeparator();
        settingsItem.addItem("Cerrar Sesión", new Command() {
			private static final long serialVersionUID = -1625825067103196986L;

			@Override
            //LOGOUT
            public void menuSelected(final MenuItem selectedItem) {
            	VaadinSession.getCurrent().setAttribute("userId", null);
    			VaadinSession.getCurrent().setAttribute("currentUser", null);
    			navigate(Vaadintest01UI.MAIN_VIEW);
            	//DashboardEventBus.post(new UserLoggedOutEvent());
            }
        });
        
        userLayout.addComponent(settings);
        return userLayout;
    }

}
