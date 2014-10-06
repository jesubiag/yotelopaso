package com.example.views.components;

import com.vaadin.ui.Component;

public interface SubjectsByYear {
	
	public void addButtonToContentLayout(String buttonCaption);
	
	interface SubjectsByYearListener {
		
	}
	
	public void addListener(SubjectsByYearListener listener);

}
