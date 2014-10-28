package com.yotelopaso.views;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component.Event;

public interface SubjectsView extends AbstractHomeView {
	
	public void setSubjects(String careerName);
	public void nagivate(String viewName);
	public void cleanComponents();
	public void buildSubjectLayout(String subjectName, String careerName);
	public void showNewsEditorWindow(Long id);
	public void toggleTreeRoot(String rootName);
	
	interface SubjectsViewListener extends AbstractHomeViewListener {
		public void itemClick(String subjectName);
		public void setDefaultContent();
		public void setSubjectContent(String subjectName);
		public void selectedTabChange(String caption);
		public void buttonClick(String caption, ClickEvent event);
	}
	
	public void addListener(SubjectsViewListener listener);

}
