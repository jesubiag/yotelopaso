package com.yotelopaso.views.components;

public interface LastFiles {
	
	public void setComponentContent(String date, String subject, 
			String author, String name, String url);
	
	interface LastFilesListener {
		public void setContent();
	};

}
