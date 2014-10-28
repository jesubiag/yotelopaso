package com.yotelopaso.views.implementations;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.yotelopaso.presenters.SubjectsPresenter;
import com.yotelopaso.views.SubjectsView;
import com.yotelopaso.views.components.Editor;
import com.yotelopaso.views.templates.AbstractHomeViewImpl;

public class SubjectsViewImpl extends AbstractHomeViewImpl implements SubjectsView, 
ItemClickListener {

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

	public SubjectsPresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(SubjectsPresenter presenter) {
		this.presenter = presenter;
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
		VerticalLayout subjectLayout = new VerticalLayout();
		subjectLayout.setSizeFull();
		subjectLayout.addComponent(new Label(subjectName));
		mainLayout.addComponent(subjectLayout);
		
		// TODO: usar metodos para crear el contenido de cada pestaña
		// Noticias
		VerticalLayout tabNews = new VerticalLayout();
		tabNews.setSizeFull();
		VerticalLayout tabFiles = new VerticalLayout();
		HorizontalLayout topLayout = new HorizontalLayout();
		topLayout.setWidth("100%");
		topLayout.setMargin(true);
		
		PopupView editor = new PopupView(new Editor(subjectName));
		topLayout.addComponent(editor);
		
		topLayout.setComponentAlignment(editor, Alignment.MIDDLE_LEFT);
		tabNews.addComponent(topLayout);
		tabNews.addComponent(new Label("<hr/>", ContentMode.HTML));
		
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
	public void showNewsEditorWindow() {
	}

	@Override
	public void toggleTreeRoot(String rootName) {
		subjectsTreeComponent.toggleRoot(rootName);
	}
	
	private void buildFilesView(final TabSheet ft, String subjectName, String careerName) {
		final VerticalLayout subtabParciales = new VerticalLayout();
		final VerticalLayout subtabApuntes = new VerticalLayout();
		final VerticalLayout subtabTPs = new VerticalLayout();
		final VerticalLayout subtabFinales = new VerticalLayout();
		
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
			tables.put(entry.getKey(), aux);
			entry.getValue().addComponent(aux);
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


	//@Override
	//public void selectedTabChange(SelectedTabChangeEvent event) {
	//	for (SubjectsViewListener listener : listeners) {
	//		listener.selectedTabChange(event.getTabSheet().getCaption());
	//	}
	//}
}

