package com.yotelopaso.presenters;

import java.util.List;

import com.yotelopaso.domain.Subject;
import com.yotelopaso.persistence.SubjectManager;
import com.yotelopaso.views.components.SubjectsByYear;

public class SubjectsByYearPresenter implements SubjectsByYear.SubjectsByYearListener {
	
	SubjectsByYear view;
	SubjectManager service;
	
	public SubjectsByYearPresenter(SubjectsByYear view) {
		this.view = view;
		this.service = new SubjectManager();
		
		view.addListener(this);
	}
	
	public void setContent(String careerName, int year) {
		view.cleanComponents();
		List<Subject> filteredSubjects = service.filterByCareerAndYear(careerName, year);
		for (Subject s : filteredSubjects) {
			view.setTitleCaption("Materias de " + s.getYear() + "º año");
			view.addButtonToContentLayout(s.getName());
		}
	}

}
