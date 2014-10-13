package com.yotelopaso.presenters;

import com.yotelopaso.persistence.UserManager;
import com.yotelopaso.views.HomeView;
import com.yotelopaso.views.components.LastNews;

public class HomePresenter extends AbstractHomePresenter<HomeView> implements HomeView.HomeViewListener {
	
	HomeView view;
	UserManager service;
	
	public HomePresenter(HomeView view, UserManager service) {
		super(view, service);
		
		this.view = view;
		this.service = service;
		
		view.addListener(this);
	}

	@Override
	public void addWindowsNewsContent(String caption) {
		switch (caption) {
		case "Novedades":
			String cn = service.getCurrentUser().getCareer().getName();
			LastNews lastNews = new LastNews(cn);
			view.setLastNews(lastNews);
			break;
		}
		
	}

}
