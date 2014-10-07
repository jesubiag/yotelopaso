package com.example.presenters;

import com.example.domain.User;
import com.example.persistence.NewsManager;
import com.example.persistence.SubjectManager;
import com.example.persistence.UserManager;
import com.example.views.SubjectsView;

public class SubjectsPresenter extends AbstractHomePresenter<SubjectsView> implements SubjectsView.SubjectsViewListener {
	
	SubjectsView view;
	NewsManager newsService;
	SubjectManager subjectService;
	UserManager userService;

	public SubjectsPresenter(SubjectsView view, UserManager userService, SubjectManager subjectService) {
		super(view, userService);
		
		this.view = view;
		this.subjectService = subjectService;
		this.userService = userService;
		
		view.addListener(this);
	}

	@Override
	public void buttonClick(String caption) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContent() {
		User currentUser = userService.getCurrentUser();
		String careerName = currentUser.getCareer().getName();
		for (int i=1; i<=5; i++) {
			view.setSubjects(careerName, i);
		}
	}

}
