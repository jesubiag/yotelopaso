package com.example.presenters;

import com.example.persistence.NewsManager;
import com.example.persistence.UserManager;
import com.example.views.SubjectsView;

public class SubjectsPresenter extends AbstractHomePresenter<SubjectsView> implements SubjectsView.SubjectsViewListener {
	
	SubjectsView view;
	NewsManager service;

	public SubjectsPresenter(SubjectsView view, UserManager userService, NewsManager newsService) {
		super(view, userService);
		
		this.view = view;
		this.service = newsService;
		
		view.addListener(this);
	}

}
