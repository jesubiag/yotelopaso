package com.yotelopaso.presenters;

import com.yotelopaso.domain.User;
import com.yotelopaso.persistence.UserManager;
import com.yotelopaso.views.MainView;
import com.yotelopaso.views.implementations.MainViewImpl;

public class MainPresenter implements MainView.MainViewListener {
	
	MainViewImpl view;
	User model;
	UserManager userManager= new UserManager();
	
	public MainPresenter(MainViewImpl view) {
		this.view = view;
	}

}
