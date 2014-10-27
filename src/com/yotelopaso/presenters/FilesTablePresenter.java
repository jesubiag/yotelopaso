package com.yotelopaso.presenters;

import com.yotelopaso.persistence.FileManager;
import com.yotelopaso.views.components.FilesTable;

public class FilesTablePresenter implements FilesTable.FilesTableListener {
	
	FilesTable view;
	FileManager fileService;
	
	public FilesTablePresenter(FilesTable view, FileManager fileService) {
		this.view = view;
		this.fileService = fileService;
	}

}
