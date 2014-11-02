package com.yotelopaso.views.implementations;

import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;
import com.yotelopaso.presenters.LastFilesPresenter;
import com.yotelopaso.views.components.LastFiles;

public class LastFilesImpl extends CustomComponent implements LastFiles {
	
	private static final long serialVersionUID = 1L;
	final private VerticalLayout mainLayout = new VerticalLayout();
	private LastFilesPresenter presenter;
	private ClickListener parentView;
	
	public LastFilesImpl(ClickListener parentView) {
		this.parentView = parentView;
		this.presenter = new LastFilesPresenter(this);
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}
	
	private void buildMainLayout() {
		mainLayout.setWidth("100%");
		mainLayout.setHeightUndefined();
		
		presenter.setContent();
	}

	@Override
	public void setComponentContent(String date, String subject, String author,
			String name, String url) {
		
		Label fileDate = new Label(date, ContentMode.HTML);
		Label fileSubject = new Label(subject, ContentMode.HTML);
		Label fileAuthor = new Label(author, ContentMode.HTML);
		Label text = new Label("ha subido", ContentMode.HTML);
		Link fileLink = new Link(name, new ExternalResource(url));
		fileLink.setTargetName("_blank");
		
		VerticalLayout contentLayout = new VerticalLayout();
		contentLayout.setSizeFull();
		
		HorizontalLayout topLayout = new HorizontalLayout();
		topLayout.setSizeUndefined();
		topLayout.setSpacing(true);
		topLayout.addComponents(fileDate, fileSubject);
		
		HorizontalLayout bottomLayout = new HorizontalLayout();
		bottomLayout.setSizeUndefined();
		bottomLayout.setSpacing(true);
		bottomLayout.addComponents(fileAuthor, text, fileLink);
		
		contentLayout.addComponents(topLayout, bottomLayout);
		
		mainLayout.addComponent(contentLayout);
	
	}
	
}
