package com.example.views.implementations;

import java.util.ArrayList;
import java.util.List;

import com.example.views.HomeView;
import com.example.views.components.LastNews;
import com.example.views.templates.AbstractHomeViewImpl;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.HasComponents.ComponentAttachListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class HomeViewImpl extends AbstractHomeViewImpl implements HomeView, ComponentAttachListener {

	private static final long serialVersionUID = 1L;
	private Panel panel;
	private Panel windowNews;
	private VerticalLayout subContentNews;
	private LastNews lastNews;
	private Panel windowRecentFiles;
	private Panel windowRecentEvents;

	public HomeViewImpl() {
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
		windowNews.addComponentAttachListener(this);
		
		subContentNews = new VerticalLayout();
		subContentNews.setMargin(true);
		windowNews.setContent(subContentNews);
		//User cu = getCurrentUser();
		//Career c = cu.getCareer();
		//String cn = c.getName();
		//LastNews ln = new LastNews(cn);
		//windowNews.setContent(lastNews);
		windowNews.setHeight("100%");
		windowNews.setWidth("100%");
		// Content should be retrieved from database
		//LastNews contentNews = new LastNews(getCurrentUser().getCareer().getName());
		subContentNews.addComponent(lastNews);
		
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
	
	List<HomeViewListener> listeners = new ArrayList<HomeViewListener>();

	@Override
	public void addListener(HomeViewListener listener) {
		listeners.add(listener);
	}

	@Override
	public void setLastNews(LastNews lastNews) {
		this.lastNews = lastNews;
	}

	@Override
	public void componentAttachedToContainer(ComponentAttachEvent event) {
		for (HomeViewListener listener : listeners) {
			listener.componentAttachedToContainer(event.getComponent().getCaption());
		}
	}
	
}
