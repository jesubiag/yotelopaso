package com.example.presenters;

import com.example.domain.User;
import com.example.persistence.NewsManager;
import com.example.persistence.SubjectManager;
import com.example.persistence.UserManager;
import com.example.utils.StringUtils;
import com.example.vaadintest01.Vaadintest01UI;
import com.example.views.SubjectsView;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.ui.UI;

public class SubjectsPresenter extends AbstractHomePresenter<SubjectsView> implements SubjectsView.SubjectsViewListener {
	
	SubjectsView view;
	NewsManager newsService;
	SubjectManager subjectService;
	UserManager userService;
	String userCareer;

	public SubjectsPresenter(SubjectsView view, UserManager userService, SubjectManager subjectService) {
		super(view, userService);
		
		this.view = view;
		this.subjectService = subjectService;
		this.userService = userService;
		
		view.addListener(this);
	}

	@Override
	public void buttonClick(String caption) {
		// TODO: Arreglar, esto debería funcionar, no debería repetirse el contenido del método en este
		//super.panelButtonClick(caption);
		
		switch (caption) {
		case "Inicio":
			view.nagivate(Vaadintest01UI.HOME_VIEW);
			break;
		case "Materias":
			view.nagivate(Vaadintest01UI.SUBJECTS_VIEW);
			break;
		case "Mi Calendario":
			//navigator.navigateTo(Vaadintest01UI.CALENDAR_VIEW);
			break;
		case "Mi Actividad":
			//navigator.navigateTo(Vaadintest01UI.ACTIVITY_VIEW);
			break;
		case "Logout":
			//logout
			break;
		case "Nueva Noticia":
			view.showNewsEditorWindow();
			break;
		default:
			this.userCareer = userService.getCurrentUser().getCareer().getName();
			view.cleanComponents();
			view.nagivate(Vaadintest01UI.SUBJECTS_VIEW + "/" + caption);
			//view.buildSubjectLayout(caption);
			break;
		}
	}

	@Override
	public void setDefaultContent() {
		User currentUser = userService.getCurrentUser();
		String careerName = currentUser.getCareer().getName();
		for (int i=1; i<=5; i++) {
			view.setSubjects(careerName, i);
		}
	}

	@Override
	public void setSubjectContent(String subjectName) {
		view.cleanComponents();
		view.buildSubjectLayout(subjectName, userCareer);
	}

	@Override
	public void selectedTabChange(String caption) {
		//String currentPage = StringUtils.parseURL(Page.getCurrent().getUriFragment())[1];
		//System.out.println(caption);
		//Page.getCurrent().setUriFragment(caption);
		//view.nagivate(Vaadintest01UI.SUBJECTS_VIEW + "/" + currentPage + "/" + caption);
		
	}

}
