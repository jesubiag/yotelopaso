package com.yotelopaso.views;

public interface SubjectsView extends AbstractHomeView {
	
	public void setSubjects(String careerName);
	public void nagivate(String viewName);
	public void cleanComponents();
	public void buildSubjectLayout(String subjectName, String careerName);
	public void showNewsEditorWindow();
	public void toggleTreeRoot(String rootName);
	
	interface SubjectsViewListener extends AbstractHomeViewListener {
		public void itemClick(String subjectName);
		public void setDefaultContent();
		public void setSubjectContent(String subjectName);
		public void selectedTabChange(String caption);
	}
	
	public void addListener(SubjectsViewListener listener);

}
