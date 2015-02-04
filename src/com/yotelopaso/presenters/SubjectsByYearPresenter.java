package com.yotelopaso.presenters;

import java.util.HashMap;
import java.util.List;

import com.yotelopaso.components.SubjectsByYear;
import com.yotelopaso.domain.Subject;
import com.yotelopaso.persistence.SubjectManager;

public class SubjectsByYearPresenter implements SubjectsByYear.SubjectsByYearListener {
	
	SubjectsByYear view;
	SubjectManager service;
	
	public SubjectsByYearPresenter(SubjectsByYear view) {
		this.view = view;
		this.service = new SubjectManager();
		
		view.addListener(this);
	}
	
	public void setContent(String careerName) {
		view.cleanComponents();
		view.setTreeCaption(careerName);
		List<Subject> filteredSubjects = service.filterByCareer(careerName);
		HashMap<Integer, String> treeElements = new HashMap<Integer, String>();
		for (int i=1; i<6; i++) {
			treeElements.put(i, "Materias de " + i + "º Año");
			view.setTreeRoot(treeElements.get(i));
		}
		for (Subject s : filteredSubjects) {
			Integer year = s.getYear();
			view.setTreeLeafs(s.getName(), treeElements.get(year));
		}
	}

}
