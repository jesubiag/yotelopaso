package com.yotelopaso.views.components;

public interface SubjectsByYear {
	
	public void setTitleCaption(String titleCaption);
	public void cleanComponents();
	
	public void setTreeCaption(String careerName);
	public void setTreeRoot(String rootTitle);
	public void setTreeLeafs(String leafSubject, String rootTitle);
	
	interface SubjectsByYearListener {
		public void setContent(String careerName);
	}
	
	public void addListener(SubjectsByYearListener listener);

}
