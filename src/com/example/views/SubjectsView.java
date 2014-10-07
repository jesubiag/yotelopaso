package com.example.views;

public interface SubjectsView extends AbstractHomeView {
	
	public void setSubjects(String careerName, int year);
	
	interface SubjectsViewListener extends AbstractHomeViewListener {
		public void buttonClick(String caption);
		public void setContent();
	}
	
	public void addListener(SubjectsViewListener listener);

}
