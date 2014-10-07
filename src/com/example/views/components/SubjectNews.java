package com.example.views.components;

public interface SubjectNews {
	
	public void buildComponent(String date, String content);
	
	interface SubjectNewsListener {
		public void setContent(String careerName, String subjectName);
	}

}
