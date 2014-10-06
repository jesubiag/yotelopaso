package com.example.views.templates;

import java.util.ArrayList;
import java.util.List;

import com.example.views.AbstractHomeView;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class AbstractHomeViewImpl extends AuthView implements AbstractHomeView, ClickListener {
	
	private static final long serialVersionUID = 1L;
	private VerticalLayout rightLayout;
	private HorizontalLayout hLayout;
	private Button buttonHome;
	private Button buttonSubjects;
	private Button buttonCalendar;
	private Button buttonActivity;

	public AbstractHomeViewImpl() {
		
		setSizeFull();
		
		// Layout with menu on left and view area on right
		hLayout = new HorizontalLayout();
		hLayout.setSizeFull();
		
		// Empty layout on right
		rightLayout = new VerticalLayout();
		rightLayout.setSizeFull();

		// Have a menu on the left side of the screen
		Panel menu = new Panel("PEI");
		menu.setId("menu-panel");
		menu.setHeight("100%");
		menu.setWidth("182px");
		
		String[] buttonCaptions = new String[] {"Inicio", "Materias", "Mi Calendario", 
				"Mi Actividad"};
		String[] buttonIds = new String[] {"buttonHome", "buttonSubjects", "buttonCalendar", 
				"buttonActivity"};
		FontAwesome[] buttonIcons = new FontAwesome[] {FontAwesome.HOME, FontAwesome.BOOK, 
				FontAwesome.CALENDAR, FontAwesome.CLOCK_O};
		String buttonStyles = ValoTheme.BUTTON_LINK + " " + ValoTheme.BUTTON_ICON_ALIGN_TOP + " " + "panelLink";
		
		buttonHome = new Button();
		buttonSubjects = new Button();
		buttonCalendar = new Button();
		buttonActivity = new Button();
		
		VerticalLayout menuContent = new VerticalLayout();
		
		Button[] panelButtons = new Button[] {buttonHome, buttonSubjects, buttonCalendar, buttonActivity};
		
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
		
		addComponent(logout);

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
		getRightLayout().removeAllComponents();
	}
	
	public VerticalLayout getRightLayout() {
		return rightLayout;
	}

	public void setRightLayout(VerticalLayout rightLayout) {
		this.rightLayout = rightLayout;
	}

}
