package com.yotelopaso.presenters;

import java.util.List;

import com.yotelopaso.domain.File;
import com.yotelopaso.domain.File.Type;
import com.yotelopaso.persistence.FileManager;
import com.yotelopaso.views.components.FilesTable;

public class FilesTablePresenter implements FilesTable.FilesTableListener {
	
	FilesTable view;
	FileManager fileService;
	
	public FilesTablePresenter(FilesTable view) {
		this.view = view;
		this.fileService = new FileManager();
	}

	@Override
	public void getData(String subjectName, String careerName, String type) {
		//Esto no debería ir acá, sino en la clase que enumera
		File.Type fileType = null;
		switch (type) {
		case "Apuntes":
			fileType = File.Type.APUNTE;
			break;
		case "Finales":
			fileType = File.Type.FINAL;
			break;
		case "Parciales":
			fileType = File.Type.FINAL;
			break;
		case "TPs":
			fileType = File.Type.TP;
			break;
		}
		List<File> subjectFiles = fileService.filterByCareerSubjectType(careerName, subjectName, fileType);
		for (File f : subjectFiles) {
			view.buildTable(f.getName(), f.getUrl(), f.getAuthor().getName(), 
					f.getCreationDate(), f.getId(), f.getDescription());
		}
	}

}
