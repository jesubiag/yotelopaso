package com.yotelopaso.views.implementations.templates;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.ThemeResource;
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
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.yotelopaso.Vaadintest01UI;
import com.yotelopaso.components.RegWindow;
import com.yotelopaso.domain.User;
import com.yotelopaso.persistence.UserManager;
import com.yotelopaso.utils.UserUtils;
import com.yotelopaso.views.AbstractHomeView;


abstract public class AbstractHomeViewImpl extends AuthViewImpl implements AbstractHomeView, ClickListener {
	
	private static final long serialVersionUID = 1L;
	protected VerticalLayout rightLayout;
	private HorizontalLayout hLayout;
	private Button buttonHome;
	private Button buttonSubjects;
	private Button buttonCalendar;
	private Button buttonSubscriptions;
	private UserManager userManager = new UserManager();
	private MenuItem settingsItem;
	
	
	public AbstractHomeViewImpl() {
		
		setSizeFull();
		
		// Layout with menu on left and view area on right
		hLayout = new HorizontalLayout();
		hLayout.setSizeFull();
		
		// Empty layout on right
		rightLayout = new VerticalLayout();
		rightLayout.setSizeFull();

		// Have a menu on the left side of the screen
		Panel menu = new Panel("<b>Yo Te Lo Paso</b>");
		menu.setId("menu-panel");
		menu.setHeight("100%");
		menu.setWidth("182px");
		
		String[] buttonCaptions = new String[] {"Inicio", "Materias", "Mi Calendario", 
				"Suscripciones"};
		String[] buttonIds = new String[] {"buttonHome", "buttonSubjects", "buttonCalendar", 
				"buttonSubscriptions"};
		FontAwesome[] buttonIcons = new FontAwesome[] {FontAwesome.HOME, FontAwesome.BOOK, 
				FontAwesome.CALENDAR, FontAwesome.RSS};
		String buttonStyles = ValoTheme.BUTTON_LINK + " " + ValoTheme.BUTTON_ICON_ALIGN_TOP + " " + "panelLink";
		
		buttonHome = new Button();
		buttonSubjects = new Button();
		buttonCalendar = new Button();
		buttonSubscriptions = new Button();
		
		VerticalLayout menuContent = new VerticalLayout();
		menuContent.addComponent(buildUserMenu());
		
		Button[] panelButtons = new Button[] {buttonHome, buttonSubjects, buttonCalendar, buttonSubscriptions};
		
		for (int i = 0; i < 4; i++) {
			panelButtons[i].setCaption(buttonCaptions[i]);
			panelButtons[i].setId(buttonIds[i]);
			panelButtons[i].addStyleName(buttonStyles);
			panelButtons[i].setWidth("100%");
			panelButtons[i].setIcon(buttonIcons[i]);
			panelButtons[i].addClickListener(this);
			menuContent.addComponent(panelButtons[i]);
		}
		
		menuContent.setWidth("100%");
		menuContent.setMargin(false);
		
		menu.setContent(menuContent);
		hLayout.addComponent(menu);
		
		hLayout.addComponent(rightLayout);
		
		addComponent(hLayout);
		setExpandRatio(hLayout, 1.0f);
		hLayout.setExpandRatio(rightLayout, 1.0f);
		
		// Allow going back to the start
		Button logout = new Button("Logout", this);
		
		//addComponent(logout);

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
		settingsItem.setText(userManager.getCurrentUser().getName());
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
        return logoWrapper;
    }
	
	private Component buildUserMenu() {
        final MenuBar settings = new MenuBar();
        settings.setSizeFull();
        settings.addStyleName("user-menu");
        settings.addStyleName("user-test");
        settingsItem = settings.addItem("", new ExternalResource(
                "http://i.imgur.com/6I4EhvN.jpg"), null);
        
      	setCurrentUser( (User) VaadinSession.getCurrent().getAttribute("currentUser"));
		
        settingsItem.setText("Bienvenido");
      	settingsItem.addItem("Edit Profile", new Command() {
            @Override
            public void menuSelected(final MenuItem selectedItem) {
                //ProfilePreferencesWindow.open(user, false);
            	navigate(Vaadintest01UI.HOME_VIEW);
            	RegWindow regWindow = new RegWindow(userManager.getCurrentUser());
            	addWindow(regWindow);
            }
        });
        settingsItem.addItem("Preferences", new Command() {
            @Override
            public void menuSelected(final MenuItem selectedItem) {
                //ProfilePreferencesWindow.open(user, true);
            }
        });
        settingsItem.addSeparator();
        settingsItem.addItem("Sign Out", new Command() {
            @Override
            //LOGOUT
            public void menuSelected(final MenuItem selectedItem) {
            	VaadinSession.getCurrent().setAttribute("userId", null);
    			VaadinSession.getCurrent().setAttribute("currentUser", null);
    			navigate(Vaadintest01UI.MAIN_VIEW);
            	//DashboardEventBus.post(new UserLoggedOutEvent());
            }
        });
        return settings;
    }

}
