package com.example.views.templates;

import com.example.utils.UserUtils;
import com.example.vaadintest01.Vaadintest01UI;
import com.example.views.templates.AuthView;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

public class AbstractHomeView extends AuthView {

	private static final long serialVersionUID = 1L;
	private HorizontalLayout hLayout;

	public AbstractHomeView() {
		
		setSizeFull();
		
		// Layout with menu on left and view area on right
		hLayout = new HorizontalLayout();
		hLayout.setSizeFull();

		// Have a menu on the left side of the screen
		Panel menu = new Panel("PEI");
		menu.setId("menu-panel");
		menu.setHeight("100%");
		menu.setWidth("182px");
		Button buttonHome = new Button("Inicio");
		Button buttonSubjects = new Button("Materias");
		Button buttonCalendar = new Button("Mi Calendario");
		Button buttonActivity = new Button("Mi Actividad");
		buttonHome.setId("buttonHome");
		buttonHome.setWidth("100%");
		buttonHome.setIcon(FontAwesome.HOME);
		buttonHome.addStyleName(ValoTheme.BUTTON_LINK + " " + ValoTheme.BUTTON_ICON_ALIGN_TOP + " " + "panelLink");
		buttonSubjects.setId("buttonSubjects");
		buttonSubjects.setWidth("100%");
		buttonSubjects.setIcon(FontAwesome.BOOK);
		buttonSubjects.addStyleName(ValoTheme.BUTTON_LINK + " " + ValoTheme.BUTTON_ICON_ALIGN_TOP + " " + "panelLink");
		buttonCalendar.setId("buttonCalendar");
		buttonCalendar.setWidth("100%");
		buttonCalendar.setIcon(FontAwesome.CALENDAR);
		buttonCalendar.addStyleName(ValoTheme.BUTTON_LINK + " " + ValoTheme.BUTTON_ICON_ALIGN_TOP + " " + "panelLink");
		buttonActivity.setId("buttonActivity");
		buttonActivity.setWidth("100%");
		buttonActivity.setIcon(FontAwesome.CLOCK_O);
		buttonActivity.addStyleName(ValoTheme.BUTTON_LINK + " " + ValoTheme.BUTTON_ICON_ALIGN_TOP + " " + "panelLink");
		VerticalLayout menuContent = new VerticalLayout();
		menuContent.addComponent(buttonHome);
		menuContent.addComponent(buttonSubjects);
		menuContent.addComponent(buttonCalendar);
		menuContent.addComponent(buttonActivity);
		menuContent.setWidth("100%");
		menuContent.setMargin(false);
		menu.setContent(menuContent);
		hLayout.addComponent(menu);
		
		
		addComponent(hLayout);
		setExpandRatio(hLayout, 1.0f);
		
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

	public HorizontalLayout gethLayout() {
		return hLayout;
	}

	public void sethLayout(HorizontalLayout hLayout) {
		this.hLayout = hLayout;
	}
	
}
