package com.example.views.templates;

import com.example.domain.User;
import com.example.utils.UserUtils;
import com.example.vaadintest01.Vaadintest01UI;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class AbstractHomeView extends AuthView {
	
	private static final long serialVersionUID = 1L;
	private VerticalLayout rightLayout;
	private HorizontalLayout hLayout;
	private Button buttonHome;
	private Button buttonSubjects;
	private Button buttonCalendar;
	private Button buttonActivity;

	public AbstractHomeView() {
		
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
		buttonHome = new Button("Inicio");
		buttonSubjects = new Button("Materias");
		buttonCalendar = new Button("Mi Calendario");
		buttonActivity = new Button("Mi Actividad");
		String buttonStyles = ValoTheme.BUTTON_LINK + " " + ValoTheme.BUTTON_ICON_ALIGN_TOP + " " + "panelLink";
		buttonHome.setId("buttonHome");
		buttonHome.setWidth("100%");
		buttonHome.setIcon(FontAwesome.HOME);
		buttonHome.addStyleName(buttonStyles);
		buttonSubjects.setId("buttonSubjects");
		buttonSubjects.setWidth("100%");
		buttonSubjects.setIcon(FontAwesome.BOOK);
		buttonSubjects.addStyleName(buttonStyles);
		buttonSubjects.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().getNavigator().navigateTo(Vaadintest01UI.SUBJECTSVIEW);
			}
		});
		buttonCalendar.setId("buttonCalendar");
		buttonCalendar.setWidth("100%");
		buttonCalendar.setIcon(FontAwesome.CALENDAR);
		buttonCalendar.addStyleName(buttonStyles);
		buttonActivity.setId("buttonActivity");
		buttonActivity.setWidth("100%");
		buttonActivity.setIcon(FontAwesome.CLOCK_O);
		buttonActivity.addStyleName(buttonStyles);
		VerticalLayout menuContent = new VerticalLayout();
		menuContent.addComponent(buttonHome);
		menuContent.addComponent(buttonSubjects);
		menuContent.addComponent(buttonCalendar);
		menuContent.addComponent(buttonActivity);
		menuContent.setWidth("100%");
		menuContent.setMargin(false);
		menu.setContent(menuContent);
		hLayout.addComponent(menu);
		
		hLayout.addComponent(rightLayout);
		
		addComponent(hLayout);
		setExpandRatio(hLayout, 1.0f);
		hLayout.setExpandRatio(rightLayout, 1.0f);
		
		// Allow going back to the start
		Button logout = new Button("Logout",
					new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO: el cierre de sesión no es limpio, chequearlo
				UI.getCurrent().getNavigator().navigateTo(Vaadintest01UI.MAINVIEW);
				UserUtils.logOff(VaadinSession.getCurrent());
			}
		});
		
		addComponent(logout);

	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
		
		getRightLayout().removeAllComponents();
	}
	
	class ButtonListener implements Button.ClickListener {

		private static final long serialVersionUID = 1L;
		String menuitem;
		
		public ButtonListener(String menuitem) {
			//this.menuitem = menuitem;
			this.menuitem = Vaadintest01UI.HOMEVIEW;
		}

		@Override
		public void buttonClick(ClickEvent event) {
			// Navigate to a specific state
			UI.getCurrent().getNavigator().navigateTo(Vaadintest01UI.MAINVIEW + menuitem);
		}
	}

	public VerticalLayout getRightLayout() {
		return rightLayout;
	}

	public void setRightLayout(VerticalLayout rightLayout) {
		this.rightLayout = rightLayout;
	}

	public Button getButtonHome() {
		return buttonHome;
	}

	public void setButtonHome(Button buttonHome) {
		this.buttonHome = buttonHome;
	}

	public Button getButtonSubjects() {
		return buttonSubjects;
	}

	public void setButtonSubjects(Button buttonSubjects) {
		this.buttonSubjects = buttonSubjects;
	}

	public Button getButtonCalendar() {
		return buttonCalendar;
	}

	public void setButtonCalendar(Button buttonCalendar) {
		this.buttonCalendar = buttonCalendar;
	}

	public Button getButtonActivity() {
		return buttonActivity;
	}

	public void setButtonActivity(Button buttonActivity) {
		this.buttonActivity = buttonActivity;
	}

	
}
