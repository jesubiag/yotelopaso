package com.example.views.components;

public interface SubjectsByYear {
	
	public void addButtonToContentLayout(String buttonCaption);
	public void setTitleCaption(String titleCaption);
	
	interface SubjectsByYearListener {
		
	}
	
	public void addListener(SubjectsByYearListener listener);

}
