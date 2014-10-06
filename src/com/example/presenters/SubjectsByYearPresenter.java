package com.example.presenters;

import java.util.List;

import com.example.domain.Subject;
import com.example.persistence.SubjectManager;
import com.example.views.components.SubjectsByYear;

public class SubjectsByYearPresenter implements SubjectsByYear.SubjectsByYearListener {
	
	SubjectsByYear view;
	SubjectManager service;
	
	public SubjectsByYearPresenter(SubjectsByYear view) {
		this.view = view;
		this.service = new SubjectManager();
		
		view.addListener(this);
	}
	
	public void setContent(String careerName, int year) {
		List<Subject> filteredSubjects = service.filterByCareerAndYear(careerName, year);
		for (Subject s : filteredSubjects) {
			view.addButtonToContentLayout(s.getName());
		}
	}

}
