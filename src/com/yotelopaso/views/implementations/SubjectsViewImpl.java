package com.yotelopaso.views.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.yotelopaso.Vaadintest01UI;
import com.yotelopaso.domain.File.Type;
import com.yotelopaso.persistence.NewsManager;
import com.yotelopaso.presenters.SubjectsPresenter;
import com.yotelopaso.utils.Hr;
import com.yotelopaso.views.SubjectsView;
import com.yotelopaso.views.components.Editor;
import com.yotelopaso.views.components.UploadFiles;
import com.yotelopaso.views.templates.AbstractHomeViewImpl;

public class SubjectsViewImpl extends AbstractHomeViewImpl implements SubjectsView, 
ItemClickListener, ClickListener {

	private static final long serialVersionUID = 1L;
	
	private SubjectsPresenter presenter;
	final HorizontalLayout mainLayout = new HorizontalLayout();
	final Panel panel = new Panel();
	final TabSheet sections = new TabSheet();
	private FilesTableImpl filesTableApuntes;
	private FilesTableImpl filesTableParciales;
	private FilesTableImpl filesTableFinales;
	private FilesTableImpl filesTableTPs;
	private String currentTableData;
	private SubjectsByYearImpl subjectsTreeComponent;
	private String subjectName;
	private String careerName;
	
	@Override
	public void enter(ViewChangeEvent event) {
		
		super.enter(event);
		
		// Seteo el tamaño así para que el panel sea scrolleable
		//mainLayout.setWidth("100%");
		//mainLayout.setHeightUndefined();
		mainLayout.setSizeFull();
		
		mainLayout.setMargin(true);
		
		getRightLayout().addComponent(panel);
		
		panel.setContent(mainLayout);
		panel.setSizeFull();
		
		cleanComponents();
		
		String parameters = event.getParameters(); 
		subjectName = parameters;
		
		if (parameters.isEmpty() || parameters == null) {
			mainLayout.setSizeUndefined();
			panel.setCaption("Materias");
			presenter.setDefaultContent();
			return;
		}
		// TODO: mejorar la navegacion y manejo de la url (una vez que lo implemente)
		//String [] uriFragments = StringUtils.parseURL(parameters);
		//buildSubjectLayout(event.getParameters());
		presenter.setSubjectContent(event.getParameters());
	}
	
	List<SubjectsViewListener> listeners = new ArrayList<SubjectsViewListener>();

	@Override
	public void addListener(SubjectsViewListener listener) {
		listeners.add(listener);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		super.buttonClick(event);
		for (SubjectsViewListener listener : listeners) {
			listener.buttonClick(event.getButton().getCaption(), 
					(Long) event.getButton().getData());
		}
	}
	
	@Override
	public void itemClick(ItemClickEvent event) {
		//super.buttonClick(event);
		for (SubjectsViewListener listener : listeners) {
			Class<? extends Object> sourceClass = event.getSource().getClass();
			if (sourceClass == Tree.class) {
				listener.treeItemClick( (String) event.getItemId());
			} else if (sourceClass == Table.class) {
				Table currentTable = (Table) event.getSource();
				currentTableData = (String) currentTable.getData();
				listener.tableItemClick(event.getItem());
			}
		}
	}

	@Override
	public void setSubjects(String careerName) {
		subjectsTreeComponent = new SubjectsByYearImpl(careerName, this);
		this.mainLayout.addComponent(subjectsTreeComponent);
	}

	@Override
	public void nagivate(String viewName) {
		UI.getCurrent().getNavigator().navigateTo(viewName);
	}

	@Override
	public void cleanComponents() {
		mainLayout.removeAllComponents();
		sections.removeAllComponents();
	}

	@Override
	public void buildSubjectLayout(String subjectName, String careerName) {
		cleanComponents();
		// TODO: sacar esto cuando mejore la implementación de la recarga de componente
		this.careerName = careerName;
		VerticalLayout subjectLayout = new VerticalLayout();
		subjectLayout.setSizeFull();
		subjectLayout.addComponent(new Label(subjectName));
		mainLayout.addComponent(subjectLayout);
		
		// TODO: usar metodos para crear el contenido de cada pestaña
		// Noticias
		VerticalLayout tabNews = new VerticalLayout();
		tabNews.setSizeFull();
		VerticalLayout tabFiles = new VerticalLayout();
		tabFiles.setSizeFull();
		HorizontalLayout topLayout = new HorizontalLayout();
		topLayout.setWidth("100%");
		topLayout.setMargin(false);
		
		//PopupView editor = new PopupView(new Editor(subjectName));
		//topLayout.addComponent(editor);
		//topLayout.setComponentAlignment(editor, Alignment.MIDDLE_LEFT);
		
		Button createNews = new Button("Nueva Noticia");
		createNews.addClickListener(this);
		createNews.addStyleName("primary");
		createNews.setHeight("60%");
		topLayout.addComponent(createNews);
		topLayout.setComponentAlignment(createNews, Alignment.MIDDLE_LEFT);
		createNews.setId("NuevaNoticia");
		
		tabNews.addComponent(topLayout);
		tabNews.addComponent(new Hr());
		
		Panel newsContainer = new Panel();
		newsContainer.addStyleName(ValoTheme.PANEL_BORDERLESS);
		newsContainer.setSizeFull();
		VerticalLayout containerContent = new VerticalLayout();
		newsContainer.setContent(containerContent);
		containerContent.setWidth("100%");
		containerContent.setHeightUndefined();
		containerContent.addComponent(new SubjectNewsImpl(careerName, subjectName, this));
		
		tabNews.addComponent(newsContainer);
		tabNews.setExpandRatio(newsContainer, 1.0f);
		
		// Archivos
		TabSheet filesTypes = new TabSheet();
		
		buildFilesView(filesTypes, subjectName, careerName);
		
		
		
		tabFiles.addComponent(filesTypes);
		
		sections.setSizeFull();
		sections.removeAllComponents();
		sections.addTab(tabNews, "Noticias");
		sections.addTab(tabFiles, "Archivos");
		//sections.addSelectedTabChangeListener(this);
		subjectLayout.addComponent(sections);
		subjectLayout.setExpandRatio(sections, 1.0f);
	}
	
	@Override
	public void showNewsEditorWindow(Long id) {
		Vaadintest01UI.getCurrent().addWindow(new Editor(subjectName,id));
		//UI.getCurrent().addWindow(new Editor(subjectName));
	}
	@Override
	public void showUploadFileWindow(Type fileType) {
		Vaadintest01UI.getCurrent().addWindow(new UploadFiles(subjectName,fileType, this));
		//UI.getCurrent().addWindow(new Editor(subjectName));
	}
	
	@Override
	public void toggleTreeRoot(String rootName) {
		subjectsTreeComponent.toggleRoot(rootName);
	}
	
	private void buildFilesView(final TabSheet ft, String subjectName, String careerName) {
		
		final VerticalLayout subtabParciales = new VerticalLayout();
		subtabParciales.setSizeFull();
		subtabParciales.setSpacing(true);
		subtabParciales.setId("Parciales");
		final VerticalLayout subtabApuntes = new VerticalLayout();
		subtabApuntes.setSizeFull();
		subtabApuntes.setSpacing(true);
		subtabApuntes.setId("Apuntes");
		final VerticalLayout subtabTPs = new VerticalLayout();
		subtabTPs.setSizeFull();
		subtabTPs.setSpacing(true);
		subtabTPs.setId("Tps");
		final VerticalLayout subtabFinales = new VerticalLayout();
		subtabFinales.setSizeFull(); 
		subtabFinales.setSpacing(true);
		subtabFinales.setId("Finales");
		
		HashMap<String, VerticalLayout> tabs = new HashMap<String, VerticalLayout>();
		HashMap<String, FilesTableImpl> tables = new HashMap<String, FilesTableImpl>();
		
		tabs.put("Apuntes", subtabApuntes);
		tabs.put("Finales", subtabFinales);
		tabs.put("Parciales", subtabParciales);
		tabs.put("TPs", subtabTPs);
		
		// TODO: mejorar el siguiente bloque de codigo. Tal vez no convenga usar Hash sino listas
		for (Map.Entry<String, VerticalLayout> entry : tabs.entrySet()) {
			ft.addTab(entry.getValue(), entry.getKey());
			FilesTableImpl aux = new FilesTableImpl(subjectName, this, entry.getKey(), careerName);
			aux.setSizeFull();
			tables.put(entry.getKey(), aux);
			Button cargarArchivos = new Button("Subir " + entry.getKey());
			cargarArchivos.addStyleName("primary");
			cargarArchivos.addClickListener(this);
			entry.getValue().addComponent(cargarArchivos);
			entry.getValue().addComponent(aux);
			//entry.getValue().setExpandRatio(aux, 1.0f);
		}
		filesTableApuntes = tables.get("Apuntes");
		filesTableParciales = tables.get("Parciales");
		filesTableFinales = tables.get("Finales");
		filesTableTPs = tables.get("TPs");
		
		
		//TODO: setear como posicion el ancho del segundo componente
		
	}
	
	@Override
	public void showFileDetail(String authorName, String date, String name, 
			String desc) {
		switch (currentTableData) {
		case "Apuntes":
			filesTableApuntes.buildFileDetailLayout(authorName, date, name, desc);
			break;
		case "Finales":
			filesTableFinales.buildFileDetailLayout(authorName, date, name, desc);
			break;
		case "Parciales":
			filesTableParciales.buildFileDetailLayout(authorName, date, name, desc);
			break;
		case "TPs":
			filesTableTPs.buildFileDetailLayout(authorName, date, name, desc);
			break;
		default:
			break;
		}
		
	}	

	public SubjectsPresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(SubjectsPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void deleteNew(Long newsId) {
		NewsManager newsService = new NewsManager();
		String materia=newsService.getById(newsId).getSubject().getName();
		newsService.delete((Long)newsId);
		getUI().getNavigator().navigateTo(Vaadintest01UI.SUBJECTS_VIEW + "/" + materia);
		Notification notif = new Notification("Noticia Borrada", Notification.Type.ERROR_MESSAGE);
		notif.setDelayMsec(2500);
		notif.show(Page.getCurrent());
	}

	@Override
	// TODO: mejorar implementación de metodo de recarga
	public void reloadComponent() {
		//FilesTableImpl aux = null;
		switch (currentTableData) {
		case "Apuntes":
			//aux = new FilesTableImpl(subjectName, this, "Apuntes", careerName);
			//replaceComponent(filesTableApuntes, aux);
			filesTableApuntes.reload();
			break;
		case "Finales":
			filesTableFinales.reload();
			break;
		case "Parciales":
			filesTableParciales.reload();
			break;
		case "TPs":
			filesTableTPs.reload();
			break;
		default:
			break;
		}
	}



	//@Override
	//public void selectedTabChange(SelectedTabChangeEvent event) {
	//	for (SubjectsViewListener listener : listeners) {
	//		listener.selectedTabChange(event.getTabSheet().getCaption());
	//	}
	//}
}

