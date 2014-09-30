package com.example.presenters;

import com.example.persistence.UserManager;
import com.example.views.HomeView;
import com.example.views.components.LastNews;

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
	public void componentAttachedToContainer(String caption) {
		switch (caption) {
		case "Novedades":
			String cn = service.getCurrentUser().getCareer().getName();
			LastNews lastNews = new LastNews(cn);
			view.setLastNews(lastNews);
			break;
		}
		
	}

}
