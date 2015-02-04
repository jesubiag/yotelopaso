package com.yotelopaso.presenters;

import java.util.List;

import com.yotelopaso.components.LastFiles;
import com.yotelopaso.domain.File;
import com.yotelopaso.persistence.FileManager;
import com.yotelopaso.persistence.UserManager;
import com.yotelopaso.utils.DateUtils;

public class LastFilesPresenter implements LastFiles.LastFilesListener {
	
	private FileManager fileService;
	private UserManager userService;
	private LastFiles view;
	
	public LastFilesPresenter(LastFiles view) {
		this.view = view;
		this.fileService = new FileManager();
		this.userService = new UserManager();
		
	}
	
	public void setContent() {
		Integer careerId = userService.getCurrentUser().getCareer().getId();
		List<File> files = fileService.filterByCareer(careerId);
		for (File f : files) {
			String subject = f.getSubject().getName();
			String date = DateUtils.dateFormat(f.getCreationDate());
			String author = f.getAuthor().getEmail();
			String url = f.getUrl();
			String name = f.getName();
			view.setComponentContent(date, subject, author, name, url);
		}
	}

}
