package com.yotelopaso.views;


import com.vaadin.data.Item;
import com.vaadin.ui.Button.ClickEvent;
import com.yotelopaso.domain.File;
import com.yotelopaso.domain.File.Type;
import com.yotelopaso.persistence.NewsManager;


public interface SubjectsView extends AbstractHomeView {
	
	public void setSubjects(String careerName);
	public void nagivate(String viewName);
	public void cleanComponents();
	public void buildSubjectLayout(String subjectName, String careerName);
	public void showNewsEditorWindow(Long id);
	public void showUploadFileWindow(Type fileType);
	public void toggleTreeRoot(String rootName);
	public void showFileDetail(String authorName, String date, String name, String desc);
	
	interface SubjectsViewListener extends AbstractHomeViewListener {
		public void treeItemClick(String subjectName);
		public void tableItemClick(Item item);
		public void buttonClick(String caption);
		public void setDefaultContent();
		public void setSubjectContent(String subjectName);
		public void selectedTabChange(String caption);
		public void buttonClick(String caption, Long newsId);
	}
	
	public void addListener(SubjectsViewListener listener);
	public void deleteNew(Long newsId);
	
	

}
