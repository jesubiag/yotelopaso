package com.example.views.components;

public interface SubjectsByYear {
	
	public void addButtonToContentLayout(String buttonCaption);
	public void setTitleCaption(String titleCaption);
	public void cleanComponents();
	
	interface SubjectsByYearListener {
		public void setContent(String careerName, int year);
	}
	
	public void addListener(SubjectsByYearListener listener);

}
