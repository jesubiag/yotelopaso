package com.example.presenters;

import com.example.domain.User;
import com.example.persistence.UserManager;
import com.example.views.MainView;
import com.example.views.implementations.MainViewImpl;
import com.vaadin.navigator.Navigator;

public class MainPresenter implements MainView.MainViewListener {
	
	MainViewImpl view;
	User model;
	UserManager userManager= new UserManager();
	
	public MainPresenter(MainViewImpl view) {
		this.view = view;
	}

}
