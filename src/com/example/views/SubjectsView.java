package com.example.views;

public interface SubjectsView extends AbstractHomeView {
	
	interface SubjectsViewListener extends AbstractHomeViewListener {
		public void buttonClick(String caption);
		public void componentAttachedToContainer(String caption);
	}
	
	public void addListener(SubjectsViewListener listener);

}
