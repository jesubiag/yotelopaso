package com.yotelopaso.views.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.TabSheet;
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
	private SubjectsByYearImpl subjectsTreeComponent;
	private String subjectName;
	
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
	public void itemClick(ItemClickEvent event) {
		//super.buttonClick(event);
		for (SubjectsViewListener listener : listeners) {
				listener.itemClick((String) event.getItemId());
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
		
		//PopupView editor = new PopupView(new Editor(subjectName));
		//topLayout.addComponent(editor);
		//topLayout.setComponentAlignment(editor, Alignment.MIDDLE_LEFT);
		
		Button createNews = new Button("Nueva Noticia");
		createNews.addClickListener(this);
		createNews.addStyleName("primary");
		topLayout.addComponent(createNews);
		
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
		
		buildFilesView(filesTypes);
		
		
		
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
		UI.getCurrent().addWindow(new Editor(subjectName));
	}

	@Override
	public void toggleTreeRoot(String rootName) {
		subjectsTreeComponent.toggleRoot(rootName);
	}
	
	private void buildFilesView(final TabSheet ft) {
		final VerticalLayout subtabParciales = new VerticalLayout();
		final VerticalLayout subtabApuntes = new VerticalLayout();
		final VerticalLayout subtabTPs = new VerticalLayout();
		final VerticalLayout subtabFinales = new VerticalLayout();
		
		HashMap<String, VerticalLayout> tabs = new HashMap<String, VerticalLayout>();
		
		tabs.put("Apuntes", subtabApuntes);
		tabs.put("Finales", subtabFinales);
		tabs.put("Parciales", subtabParciales);
		tabs.put("TPs", subtabTPs);
		
		for (Map.Entry<String, VerticalLayout> entry : tabs.entrySet()) {
			ft.addTab(entry.getValue(), entry.getKey());
			//entry.getValue().addComponent(new FilesTableImpl());
		}
		
		
		//TODO: setear como posicion el ancho del segundo componente
		
	}


	//@Override
	//public void selectedTabChange(SelectedTabChangeEvent event) {
	//	for (SubjectsViewListener listener : listeners) {
	//		listener.selectedTabChange(event.getTabSheet().getCaption());
	//	}
	//}
}

