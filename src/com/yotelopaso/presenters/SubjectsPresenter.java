package com.yotelopaso.presenters;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component.Event;
import com.vaadin.ui.UI;
import com.yotelopaso.Vaadintest01UI;
import com.yotelopaso.domain.User;
import com.yotelopaso.persistence.NewsManager;
import com.yotelopaso.persistence.SubjectManager;
import com.yotelopaso.persistence.UserManager;
import com.yotelopaso.utils.StringUtils;
import com.yotelopaso.views.SubjectsView;

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
	public void itemClick(String caption) {
		// TODO: Arreglar, esto debería funcionar, no debería repetirse el contenido del método en este
		//super.panelButtonClick(caption);
		
		// No se como implementar lo siguiente en un case
		if (caption.contains("Materias")) {
			// hacer algo en la view
			view.toggleTreeRoot(caption);
			return;
		}
		
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
			VaadinSession.getCurrent().setAttribute("userId", null);
			VaadinSession.getCurrent().setAttribute("currentUser", null);
			view.nagivate(Vaadintest01UI.MAIN_VIEW);
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
		view.setSubjects(careerName);
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

	@Override
	public void buttonClick(String caption, ClickEvent event) {
		// TODO Auto-generated method stub
		switch (caption) {
		case "Nueva Noticia":
			view.showNewsEditorWindow(null);
			break;
		case "Editar":
			view.showNewsEditorWindow((Long)event.getButton().getData());
			break;
		case "Eliminar":
			//newsService.delete((Long)event.getButton().getData());
		}
	}
}
