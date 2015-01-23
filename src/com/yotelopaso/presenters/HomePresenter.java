package com.yotelopaso.presenters;

import com.vaadin.ui.Button.ClickEvent;
import com.yotelopaso.persistence.UserManager;
import com.yotelopaso.views.HomeView;

public class HomePresenter extends AbstractHomePresenter<HomeView> implements HomeView.HomeViewListener {
	
	HomeView view;
	UserManager service;
	
	public HomePresenter(HomeView view, UserManager service) {
		super(view, service);
		
		this.view = view;
		this.service = service;
		
		view.addListener(this);
	}
	
	
	public void buttonClick(String caption, ClickEvent event) {
		// TODO Auto-generated method stub
		switch (caption) {
		case "Ampliar":
			view.showNewsEditorWindow((Long)event.getButton().getData());
			break;
		}
	}
	
	@Override
	public void addWindowsNewsContent(String caption) {
		switch (caption) {
		case "Novedades":
			//String cn = service.getCurrentUser().getCareer().getName();
			//LastNews lastNews = new LastNews(cn);
			view.setLastNews();
			break;
		}
		
	}
	
	
}
