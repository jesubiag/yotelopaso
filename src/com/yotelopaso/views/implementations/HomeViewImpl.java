package com.yotelopaso.views.implementations;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HasComponents.ComponentAttachListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.yotelopaso.persistence.UserManager;
import com.yotelopaso.views.HomeView;
import com.yotelopaso.views.components.Editor;
import com.yotelopaso.views.components.LastNews;
import com.yotelopaso.views.templates.AbstractHomeViewImpl;

public class HomeViewImpl extends AbstractHomeViewImpl implements HomeView, ComponentAttachListener {

	private static final long serialVersionUID = 1L;
	private Panel panel;
	private Panel windowNews;
	private VerticalLayout subContentNews;
	private HorizontalLayout horLayout;
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
		windowNews.setHeight("140%");
		windowNews.setWidth("100%");
		// Content should be retrieved from database
		//LastNews contentNews = new LastNews(getCurrentUser().getCareer().getName());
		subContentNews.addComponent(lastNews);
		subContentNews.setId("NewsH");
		
		windowRecentFiles = new Panel("Archivos más recientes");
		VerticalLayout subContentRecentFiles = new VerticalLayout();
		subContentRecentFiles.setMargin(true);
		windowRecentFiles.setContent(subContentRecentFiles);
		windowRecentFiles.setHeight("60%");
		windowRecentFiles.setWidth("100%");
		// Content should be retrieved from database
		subContentRecentFiles.addComponent(new LastFilesImpl(this));
		subContentRecentFiles.setId("FilesH");
		
		windowRecentEvents = new Panel("Eventos más recientes");
		VerticalLayout subContentRecentEvents = new VerticalLayout();
		subContentRecentEvents.setMargin(true);
		windowRecentEvents.setContent(subContentRecentEvents);
		windowRecentEvents.setHeight("60%");
		windowRecentEvents.setWidth("100%");
		// Content should be retrieved from database
		subContentRecentEvents.addComponent(new Label("Contenido"));
		subContentRecentEvents.setId("EventsH");
		
		horLayout = new HorizontalLayout();
		horLayout.setSizeFull();
		horLayout.setSpacing(true);
		horLayout.addComponent(windowRecentFiles);
		horLayout.addComponent(windowRecentEvents);
		horLayout.setComponentAlignment(windowRecentEvents, Alignment.BOTTOM_CENTER);
		horLayout.setComponentAlignment(windowRecentFiles, Alignment.BOTTOM_CENTER);
		
		panelLayout.addComponent(windowNews);
		panelLayout.addComponent(horLayout);
		//panelLayout.addComponent(windowRecentFiles);
		//panelLayout.addComponent(windowRecentEvents);
		
	}
	
	List<HomeViewListener> listeners = new ArrayList<HomeViewListener>();

	@Override
	public void addListener(HomeViewListener listener) {
		listeners.add(listener);
	}

	@Override
	public void setLastNews() {
		UserManager userService = new UserManager();
		String cn = userService.getCurrentUser().getCareer().getName();
		LastNews lastNews = new LastNews(cn,this);
		this.lastNews = lastNews;
	}

	@Override
	public void componentAttachedToContainer(ComponentAttachEvent event) {
		for (HomeViewListener listener : listeners) {
			listener.addWindowsNewsContent(event.getComponent().getCaption());
		}
	}

	@Override
	public void buttonClick(ClickEvent event) {
		//super.buttonClick(event);
		for (HomeViewListener listener : listeners) {
			listener.buttonClick( event.getButton().getCaption(),
					(Long) event.getButton().getData() );
		}
	}
	@Override
	public void showNewsEditorWindow(Long id) {
		Editor editor = new Editor(id);
		addWindow(editor);
	}
	
}
