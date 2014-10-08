package com.example.views;

public interface SubjectsView extends AbstractHomeView {
	
	public void setSubjects(String careerName, int year);
	public void nagivate(String viewName);
	public void cleanComponents();
	public void buildSubjectLayout(String subjectName, String careerName);
	public void showNewsEditorWindow();
	
	interface SubjectsViewListener extends AbstractHomeViewListener {
		public void buttonClick(String subjectName);
		public void setDefaultContent();
		public void setSubjectContent(String subjectName);
		public void selectedTabChange(String caption);
	}
	
	public void addListener(SubjectsViewListener listener);

}
