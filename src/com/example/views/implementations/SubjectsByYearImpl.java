package com.example.views.implementations;

import java.util.ArrayList;
import java.util.List;

import com.example.presenters.SubjectsByYearPresenter;
import com.example.views.components.SubjectsByYear;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class SubjectsByYearImpl extends CustomComponent implements SubjectsByYear {

	private static final long serialVersionUID = 1L;
	
	private VerticalLayout mainLayout;
	private VerticalLayout contentLayout;
	private String careerName;
	private ClickListener parentView;
	private int year;
	private Label title;
	private SubjectsByYearPresenter presenter;

	//public SubjectsByYearImpl(final String careerName, final int year, ClickListener parentView) {
	public SubjectsByYearImpl(final String careerName, final int year, ClickListener parentView) {
		this.presenter = new SubjectsByYearPresenter(this);
		this.careerName = careerName;
		this.year = year;
		this.parentView = parentView;
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	private void buildMainLayout() {
		mainLayout = new VerticalLayout();
		
		// title
		title = new Label();
		
		// contentLayout
		contentLayout = new VerticalLayout();
		
		/**Delego al presenter que genere la informacion necesaria para crear los componentes
		* titulo y botones
		*/
		presenter.setContent(careerName, year);
		
		// Agrego los componentes
		mainLayout.addComponents(title, contentLayout);
		
	}
	
	List<SubjectsByYearListener> componentListeners = new ArrayList<SubjectsByYearListener>();
	@Override
	public void addListener(SubjectsByYearListener listener) {
		componentListeners.add(listener);
	}
	
	public void addButtonToContentLayout(String buttonCaption) {
		Button subjectButton = new Button(buttonCaption);
		subjectButton.addStyleName(ValoTheme.BUTTON_LINK);
		subjectButton.addClickListener(this.parentView);
		this.contentLayout.addComponent(subjectButton);
	}
	
	public void cleanComponents() {
		mainLayout.removeAllComponents();
		contentLayout.removeAllComponents();
	}

	public VerticalLayout getContentLayout() {
		return contentLayout;
	}

	public void setContentLayout(VerticalLayout contentLayout) {
		this.contentLayout = contentLayout;
	}

	@Override
	public void setTitleCaption(String titleCaption) {
		this.title.setCaption(titleCaption);
	}

}
