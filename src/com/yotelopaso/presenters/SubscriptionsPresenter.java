package com.yotelopaso.presenters;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yotelopaso.Vaadintest01UI;
import com.yotelopaso.domain.Subject;
import com.yotelopaso.domain.User;
import com.yotelopaso.persistence.SubjectManager;
import com.yotelopaso.persistence.UserManager;
import com.yotelopaso.views.SubscriptionsView;

public class SubscriptionsPresenter extends AbstractHomePresenter<SubscriptionsView> 
implements SubscriptionsView.SubscriptionsViewListener {
	
	private SubscriptionsView view;
	private UserManager userService;
	private SubjectManager subjectService = new SubjectManager();
	private User currentUser;
	private Set<Subject> subscriptedSubjects;
	private List<Subject> s1;
	private List<Subject> s2;
	private List<Subject> s3;
	private List<Subject> s4;
	private List<Subject> s5;
	private List<Subject>[] lists;

	public SubscriptionsPresenter(SubscriptionsView view, UserManager userService) {
		super(view);
		this.view = view;
		this.userService = userService;
		
		view.addListener(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void getData() {
		currentUser = userService.getCurrentUser();
		getSubjectsLists(currentUser.getCareer().getName(), s1, s2, s3, s4, s5);
		subscriptedSubjects = currentUser.getSubscriptedSubjects();
	}
	
	@SuppressWarnings("unchecked")
	private void getSubjectsLists(String careerName, List<Subject>... subjectsLists) {
		lists = subjectsLists;
		for (int i=0; i < subjectsLists.length; i++) {
			subjectsLists[i] = subjectService.filterByCareerAndYear(careerName, i+1);
		}
	}

	@Override
	public void setData() {
		for (int i=0; i < lists.length; i++) {
			view.setOptionGroupData(lists[i], i);
		}
	}

	@Override
	public void setSelectedSubjects() {
		for (Subject s : subscriptedSubjects) {
			view.selectSubjects(s.getYear(), s.getId());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void buttonClick(String caption, Object... values) {
		panelButtonClick(caption);
		switch (caption) {
		case "Guardar":
			Set<Subject> newSubjects = new HashSet<Subject>();
			for (int i=0; i < values.length; i++) {
				Collection<Integer> c = (Collection<Integer>) values[i];
				for (int j : c) {
					newSubjects.add(subjectService.getById(j));
				}
			}
			newSubjects.add(subjectService.getById(1));
			userService.setCurrentUserSubjects(newSubjects);
			view.showSuccesfullSaveNotification("Suscripciones guardadas correctamente");
			view.navigate(Vaadintest01UI.HOME_VIEW);
			break;
		case "Cancelar":
			view.navigate(Vaadintest01UI.HOME_VIEW);
			break;
		default:
			break;
		}
		
	}

}
