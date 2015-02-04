package com.yotelopaso.views.implementations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.Property;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import com.yotelopaso.domain.File;
import com.yotelopaso.domain.News;
import com.yotelopaso.domain.UserCalendarEvent;
import com.yotelopaso.persistence.SubjectManager;
import com.yotelopaso.presenters.HomePresenter;
import com.yotelopaso.utils.DateUtils;
import com.yotelopaso.views.HomeView;
import com.yotelopaso.views.components.Editor;
import com.yotelopaso.views.components.EventWindowRO;
import com.yotelopaso.views.templates.AbstractHomeViewImpl;

public class HomeViewImpl extends AbstractHomeViewImpl implements HomeView, ItemClickListener {

	private static final long serialVersionUID = 1L;
	private HomePresenter presenter;
	private Panel panel;
	private Panel windowNews;
	private VerticalLayout subContentNews;
	private HorizontalLayout horLayout;
	private Panel windowRecentFiles;
	private Panel windowRecentEvents;
	
	private Table lastNewsTable;
	private Table lastFilesTable;
	private Table lastEventsTable;

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
		
		// Noticias
		windowNews = new Panel("Novedades");
		
		subContentNews = new VerticalLayout();
		subContentNews.setMargin(false);
		subContentNews.setSizeFull();
		
		windowNews.setContent(subContentNews);
		windowNews.setHeight("140%");
		windowNews.setWidth("100%");
		
		lastNewsTable = createTable();
		presenter.initLastNewsTable();
		subContentNews.addComponent(lastNewsTable);
		//subContentNews.addComponent(lastNews);
		subContentNews.setId("NewsH");
		
		// Archivos
		windowRecentFiles = new Panel("Archivos más recientes");
		VerticalLayout subContentRecentFiles = new VerticalLayout();
		subContentRecentFiles.setMargin(false);
		subContentRecentFiles.setSizeFull();
		windowRecentFiles.setContent(subContentRecentFiles);
		windowRecentFiles.setHeight("60%");
		windowRecentFiles.setWidth("100%");
		// subContentRecentFiles.addComponent(new LastFilesImpl(this));
		lastFilesTable = createTable();
		presenter.initLastFilesTable();
		subContentRecentFiles.addComponent(lastFilesTable);
		subContentRecentFiles.setId("FilesH");
		
		// Eventos
		windowRecentEvents = new Panel("Eventos más recientes");
		VerticalLayout subContentRecentEvents = new VerticalLayout();
		subContentRecentEvents.setMargin(false);
		subContentRecentEvents.setSizeFull();
		windowRecentEvents.setContent(subContentRecentEvents);
		windowRecentEvents.setHeight("60%");
		windowRecentEvents.setWidth("100%");
		lastEventsTable = createTable();
		presenter.initLastEventsTable();
		subContentRecentEvents.addComponent(lastEventsTable);
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
	
	private Table createTable() {
		Table t = new Table() {
			private static final long serialVersionUID = 1L;
			
			@SuppressWarnings("rawtypes")
			@Override
			protected String formatPropertyValue(Object rowId, Object colId, Property property) {
				if ( property.getType() == Date.class ) { 
					return DateUtils.dateFormat( (Date) property.getValue());
				}
				return super.formatPropertyValue(rowId, colId, property);
			}
		};
		setTableStyleNames(t);
		return t;
	}
	
	@Override
	public void buildLastNewsTable(JPAContainer<News> container) {
		lastNewsTable.setId("lastNewsTable");
		lastNewsTable.addItemClickListener(this);
		lastNewsTable.setContainerDataSource(container);
		
		lastNewsTable.setSelectable(true);
		lastNewsTable.setVisibleColumns(new Object[] {"subject", "title", "author", "date"});
		lastNewsTable.setSizeFull();
		lastNewsTable.setImmediate(true);
		lastNewsTable.setColumnExpandRatio("subject", 0.25f);
		lastNewsTable.setColumnExpandRatio("title", 0.6f);
		lastNewsTable.setColumnExpandRatio("author", 0.15f);
		lastNewsTable.setColumnWidth("date", 150);
	}

	/*@Override
	public void setLastNews() {
		UserManager userService = new UserManager();
		String cn = userService.getCurrentUser().getCareer().getName();
		LastNews lastNews = new LastNews(cn,this);
		this.lastNews = lastNews;
	}*/
	
	@Override
	public void buildLastFilesTable(JPAContainer<File> container) {
		lastFilesTable.setId("lastFilesTable");
		lastFilesTable.addItemClickListener(this);
		lastFilesTable.setSelectable(true);
		lastFilesTable.setContainerDataSource(container);
		lastFilesTable.setVisibleColumns(new Object[] {"subject", "name", "author", "creationDate"});
		lastFilesTable.setSizeFull();
	}
	
	@Override
	public void buildLastEventsTable(JPAContainer<UserCalendarEvent> container) {
		lastEventsTable.setId("lastEventsTable");
		lastEventsTable.addItemClickListener(this);
		lastEventsTable.setSelectable(true);
		lastEventsTable.setContainerDataSource(container);
		lastEventsTable.setVisibleColumns(new Object[] {"subjectId", "caption", "start"});
		lastEventsTable.addGeneratedColumn("subjectId", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				Integer id = (Integer) source.getItem(itemId).getItemProperty(columnId).getValue();
				return (new SubjectManager()).getById(id);
			}
			
		});
		lastEventsTable.setVisibleColumns(new Object[] {"subjectId", "caption", "start"});
		lastEventsTable.setSizeFull();
	}
	
	private void setTableStyleNames(Table table) {
		table.addStyleName("table-selectable");
		table.addStyleName(ValoTheme.TABLE_BORDERLESS);
		table.addStyleName(ValoTheme.TABLE_COMPACT);
		table.addStyleName(ValoTheme.TABLE_SMALL);
		table.addStyleName(ValoTheme.TABLE_NO_HEADER);
		table.addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
		table.addStyleName(ValoTheme.TABLE_NO_HORIZONTAL_LINES);
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

	@Override
	public void itemClick(ItemClickEvent event) {
		for ( HomeViewListener listener : listeners ) {
			Table t = (Table) event.getSource();
			listener.itemClick( t.getId(), event.getItemId() );
		}
	}

	public HomePresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(HomePresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void showEventWindow(UserCalendarEvent event) {
		addWindow( new EventWindowRO(event) );
	}
	
}
