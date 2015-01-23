package com.yotelopaso.views.implementations;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.yotelopaso.presenters.SubjectsByYearPresenter;
import com.yotelopaso.views.components.SubjectsByYear;

public class SubjectsByYearImpl extends CustomComponent implements SubjectsByYear {

	private static final long serialVersionUID = 1L;
	
	private VerticalLayout mainLayout;
	private VerticalLayout contentLayout;
	private String careerName;
	private ItemClickListener parentView;
	private Label title;
	private SubjectsByYearPresenter presenter;
	private final Tree subjectsTree = new Tree();

	//public SubjectsByYearImpl(final String careerName, final int year, ClickListener parentView) {
	public SubjectsByYearImpl(final String careerName, ItemClickListener parentView) {
		this.presenter = new SubjectsByYearPresenter(this);
		this.careerName = careerName;
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
		presenter.setContent(careerName);
		
		subjectsTree.setImmediate(true);
		subjectsTree.addItemClickListener(this.parentView);
		contentLayout.addComponent(subjectsTree);
		
		// Agrego los componentes
		contentLayout.setSizeUndefined();
		mainLayout.setSizeUndefined();
		mainLayout.addComponents(title, contentLayout);
		
	}
	
	List<SubjectsByYearListener> componentListeners = new ArrayList<SubjectsByYearListener>();
	
	@Override
	public void addListener(SubjectsByYearListener listener) {
		componentListeners.add(listener);
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

	@Override
	public void setTreeRoot(String rootTitle) {
		subjectsTree.addItem(rootTitle);
	}

	@Override
	public void setTreeLeafs(String leafSubject, String rootTitle) {
		subjectsTree.addItem(leafSubject);
		subjectsTree.setParent(leafSubject, rootTitle);
		subjectsTree.setChildrenAllowed(leafSubject, false);
		
	}
	
	@Override
	public void setTreeCaption(String careerName) {
		subjectsTree.setCaption("Materias de " + careerName);
	}
	
	public void toggleRoot(String rootName) {
		if (subjectsTree.isRoot(rootName) && subjectsTree.isExpanded(rootName)) { 
			subjectsTree.collapseItem(rootName);
		} else {
			subjectsTree.expandItem(rootName);
		}
	}

}
