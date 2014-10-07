package com.example.views.implementations;

import java.util.ArrayList;
import java.util.List;

import com.example.presenters.SubjectsPresenter;
import com.example.utils.StringUtils;
import com.example.views.SubjectsView;
import com.example.views.templates.AbstractHomeViewImpl;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class SubjectsViewImpl extends AbstractHomeViewImpl implements SubjectsView, 
ClickListener{

	private static final long serialVersionUID = 1L;
	
	private SubjectsPresenter presenter;
	final HorizontalLayout mainLayout = new HorizontalLayout();
	final Panel panel = new Panel();
	final TabSheet sections = new TabSheet();
	
	@Override
	public void enter(ViewChangeEvent event) {
		
		super.enter(event);
		
		mainLayout.setSizeFull();
		mainLayout.setMargin(true);
		
		getRightLayout().addComponent(panel);
		
		panel.setContent(mainLayout);
		panel.setSizeFull();
		
		cleanComponents();
		
		String parameters = event.getParameters(); 
		
		if (parameters.isEmpty() || parameters == null) {
			panel.setCaption("Materias");
			presenter.setDefaultContent();
			return;
		}
		// TODO: mejorar la navegacion y manejo de la url (una vez que lo implemente)
		String [] uriFragments = StringUtils.parseURL(parameters);
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
				listener.buttonClick(event.getButton().getCaption());
		}
	}

	public SubjectsPresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(SubjectsPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setSubjects(String careerName, int year) {
		SubjectsByYearImpl c = new SubjectsByYearImpl(careerName, year, this);
		this.mainLayout.addComponent(c);
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
		mainLayout.addComponent(subjectLayout);
		subjectLayout.addComponent(new Label(subjectName));
		
		// TODO: usar metodos para crear el contenido de cada pestaña
		VerticalLayout tabNews = new VerticalLayout();
		tabNews.setSizeFull();
		VerticalLayout tabFiles = new VerticalLayout();
		HorizontalLayout topLayout = new HorizontalLayout();
		topLayout.setWidth("100%");
		topLayout.setMargin(true);
		Button createNews = new Button("Nueva Noticia");
		createNews.addStyleName("primary");
		topLayout.addComponent(createNews);
		topLayout.setComponentAlignment(createNews, Alignment.MIDDLE_RIGHT);
		tabNews.addComponent(topLayout);
		tabNews.addComponent(new Label("<hr/>", ContentMode.HTML));
		tabNews.addComponent(new SubjectNewsImpl(careerName, subjectName, this));
		tabFiles.addComponent(new Label("Archivos de " + subjectName));
		sections.removeAllComponents();
		sections.addTab(tabNews, "Noticias");
		sections.addTab(tabFiles, "Archivos");
		//sections.addSelectedTabChangeListener(this);
		subjectLayout.addComponent(sections);
	}

	//@Override
	//public void selectedTabChange(SelectedTabChangeEvent event) {
	//	for (SubjectsViewListener listener : listeners) {
	//		listener.selectedTabChange(event.getTabSheet().getCaption());
	//	}
	//}
}

