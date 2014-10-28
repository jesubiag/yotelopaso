package com.yotelopaso.views.components;

public interface SubjectNews {
	
	public void buildComponent(String date, String title, String content, Long id, String authorMail);
	
	interface SubjectNewsListener {
		public void setContent(String careerName, String subjectName);
	}

}
