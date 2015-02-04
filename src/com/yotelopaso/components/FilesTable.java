package com.yotelopaso.components;

import java.util.Date;

public interface FilesTable {
	
	public void buildTable(String name, String url, String authorName, 
			Date fileDate, Long id, String description);
	
	interface FilesTableListener {
		public void getData(String subjectName, String careerName, String type);
	}
	

}
