package com.example.views;

import com.example.views.templates.AbstractHomeView;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class HomeView extends AbstractHomeView {

	private static final long serialVersionUID = 1L;
	private Panel panel;
	private Panel windowNews;
	private Panel windowRecentFiles;
	private Panel windowRecentEvents;

	public HomeView() {
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
		
		panel = new Panel("Inicio");
		panel.setSizeFull();
		getRightLayout().addComponent(panel);
		getRightLayout().setExpandRatio(panel, 1.0f);
		
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
		
	}
	
}
