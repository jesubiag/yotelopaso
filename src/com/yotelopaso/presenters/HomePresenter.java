package com.yotelopaso.presenters;

import java.util.Set;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.yotelopaso.domain.File;
import com.yotelopaso.domain.News;
import com.yotelopaso.domain.Subject;
import com.yotelopaso.domain.User;
import com.yotelopaso.domain.UserCalendarEvent;
import com.yotelopaso.persistence.CalendarManager;
import com.yotelopaso.persistence.FileManager;
import com.yotelopaso.persistence.NewsManager;
import com.yotelopaso.persistence.UserManager;
import com.yotelopaso.views.HomeView;

public class HomePresenter extends AbstractHomePresenter<HomeView> implements HomeView.HomeViewListener {
	
	HomeView view;
	UserManager service;
	NewsManager newsService = new NewsManager();
	FileManager fileService = new FileManager();
	CalendarManager calendarService = new CalendarManager();
	private User currentUser;
	private Set<Subject> subjects;
	
	public HomePresenter(HomeView view, UserManager service) {
		super(view);
		
		this.view = view;
		this.service = service;
		
		view.addListener(this);
	}
	

	@Override
	public void itemClick(String sourceId, Object itemId) {
		switch (sourceId) {
		case "lastNewsTable":
			view.showNewsVisualizerWindow( (Long) itemId );
			break;
		case "lastFilesTable":
			Long fileId = (Long) itemId;
			File f = (new FileManager()).getById(fileId);
			view.openLink(f.getUrl(), "_blank");
			break;
		case "lastEventsTable":
			// TODO cambiar esto. No debe ser posible editar el evento. No al menos si no es el autor
			Long eventId = (Long) itemId;
			view.showEventWindow( calendarService.getById(eventId) );
		default:
			break;
		}
		
	}
	
	@Override
	public void setInitData() {
		currentUser = service.getCurrentUser();
		subjects = currentUser.getSubscriptedSubjects();
	}

	@Override
	public void initLastNewsTable() {
		JPAContainer<News> cn = newsService.getNewsFromSubscription(subjects);
		view.buildLastNewsTable(cn);
	}

	@Override
	public void initLastFilesTable() {
		JPAContainer<File> cf = fileService.getFilesFromSubscription(subjects);
		view.buildLastFilesTable(cf);
	}
	
	@Override
	public void initLastEventsTable() {
		JPAContainer<UserCalendarEvent> ce = calendarService.
				getEventsFromSubscription(subjects, currentUser.getId());
		view.buildLastEventsTable(ce);
	}

}
