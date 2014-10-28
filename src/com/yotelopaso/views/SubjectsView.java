package com.yotelopaso.views;

import java.util.Date;

import com.vaadin.data.Item;

public interface SubjectsView extends AbstractHomeView {
	
	public void setSubjects(String careerName);
	public void nagivate(String viewName);
	public void cleanComponents();
	public void buildSubjectLayout(String subjectName, String careerName);
	public void showNewsEditorWindow();
	public void toggleTreeRoot(String rootName);
	public void showFileDetail(String authorName, String date, String name, String desc);
	
	interface SubjectsViewListener extends AbstractHomeViewListener {
		public void treeItemClick(String subjectName);
		public void tableItemClick(Item item);
		public void buttonClick(String caption);
		public void setDefaultContent();
		public void setSubjectContent(String subjectName);
		public void selectedTabChange(String caption);
	}
	
	public void addListener(SubjectsViewListener listener);
	
	

}
