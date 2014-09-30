package com.example.views;

public interface SubjectsView extends AbstractHomeView {
	
	interface SubjectsViewListener extends AbstractHomeViewListener {
		
	}
	
	public void addListener(SubjectsViewListener listener);

}
