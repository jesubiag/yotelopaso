package com.example.views;

import com.example.vaadintest01.Vaadintest01UI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window;

public class HomeView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;
	Panel panel;
	Panel windowNews;
	Panel windowRecentFiles;
	Panel windowRecentEvents;

	public HomeView() {
		
		setSizeFull();
		
		// Layout with menu on left and view area on right
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setSizeFull();

		// Have a menu on the left side of the screen
		Panel menu = new Panel("PEI");
		menu.setHeight("100%");
		menu.setWidth(null);
		Button buttonHome = new Button("Inicio", new ButtonListener("inicio"));
		Button buttonSubjects = new Button("Materias", new ButtonListener("materias"));
		Button buttonCalendar = new Button("Mi Calendario", new ButtonListener("calendario"));
		Button buttonActivity = new Button("Mi Actividad", new ButtonListener("actividad"));
		buttonHome.setWidth("100%");
		buttonSubjects.setWidth("100%");
		buttonCalendar.setWidth(null);
		buttonActivity.setWidth("100%");
		VerticalLayout menuContent = new VerticalLayout();
		menuContent.addComponent(buttonHome);
		menuContent.addComponent(buttonSubjects);
		menuContent.addComponent(buttonCalendar);
		menuContent.addComponent(buttonActivity);
		menuContent.setWidth(null);
		menuContent.setMargin(true);
		menu.setContent(menuContent);
		hLayout.addComponent(menu);

		// A panel that contains a content area on right
		panel = new Panel("Inicio");
		panel.setSizeFull();
		hLayout.addComponent(panel);
		hLayout.setExpandRatio(panel, 1.0f);
		addComponent(hLayout);
		setExpandRatio(hLayout, 1.0f);
		
		VerticalLayout panelLayout = new VerticalLayout();
		panelLayout.setSpacing(true);
		panelLayout.setSizeFull();
		panelLayout.setMargin(true);
		panel.setContent(panelLayout);
		
		windowNews = new Panel("Novedades");
		VerticalLayout subContentNews = new VerticalLayout();
		subContentNews.setMargin(true);
		windowNews.setContent(subContentNews);
		windowNews.setHeight("100%");
		windowNews.setWidth("100%");
		// Content should be retrieved from database
		subContentNews.addComponent(new Label("Contenido"));
		
		windowRecentFiles = new Panel("Archivos más recientes");
		VerticalLayout subContentRecentFiles = new VerticalLayout();
		subContentRecentFiles.setMargin(true);
		windowRecentFiles.setContent(subContentRecentFiles);
		windowRecentFiles.setHeight("100%");
		windowRecentFiles.setWidth("100%");
		// Content should be retrieved from database
		subContentRecentFiles.addComponent(new Label("Contenido"));
		
		windowRecentEvents = new Panel("Eventos más recientes");
		VerticalLayout subContentRecentEvents = new VerticalLayout();
		subContentRecentEvents.setMargin(true);
		windowRecentEvents.setContent(subContentRecentEvents);
		windowRecentEvents.setHeight("100%");
		windowRecentEvents.setWidth("100%");
		// Content should be retrieved from database
		subContentRecentEvents.addComponent(new Label("Contenido"));
		
		panelLayout.addComponent(windowNews);
		panelLayout.addComponent(windowRecentFiles);
		panelLayout.addComponent(windowRecentEvents);
		
		// Allow going back to the start
		Button logout = new Button("Logout",
					new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(Vaadintest01UI.MAINVIEW);
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
			getUI().getNavigator().navigateTo(Vaadintest01UI.MAINVIEW + menuitem);
		}
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// Hacer algo
	}

}
