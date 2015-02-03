package com.yotelopaso.presenters;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.yotelopaso.domain.File;
import com.yotelopaso.domain.News;
import com.yotelopaso.persistence.FileManager;
import com.yotelopaso.persistence.NewsManager;
import com.yotelopaso.persistence.UserManager;
import com.yotelopaso.views.HomeView;

public class HomePresenter extends AbstractHomePresenter<HomeView> implements HomeView.HomeViewListener {
	
	HomeView view;
	UserManager service;
	NewsManager newsService = new NewsManager();
	FileManager fileService = new FileManager();
	
	public HomePresenter(HomeView view, UserManager service) {
		super(view);
		
		this.view = view;
		this.service = service;
		
		view.addListener(this);
	}
	
	@Override
	public void buttonClick(String caption, Long newsId) {
		panelButtonClick(caption);
		switch (caption) {
		case "Ampliar":
			view.showNewsEditorWindow( newsId );
			break;
		}
	}
	
	@Override
	public void itemClick(String sourceId, Object itemId) {
		switch (sourceId) {
		case "lastNewsTable":
			view.showNewsEditorWindow( (Long) itemId );
			break;
		case "lastFilesTable":
			Long fileId = (Long) itemId;
			File f = (new FileManager()).getById(fileId);
			view.openLink(f.getUrl(), "_blank");
		default:
			break;
		}
		
	}

	@Override
	public void initLastNewsTable() {
		String p = "career.id";
		Integer id = service.getCurrentUser().getCareer().getId();
		JPAContainer<News> cn = newsService.filterContainer(p, id);
		view.buildLastNewsTable(cn);
	}

	@Override
	public void initLastFilesTable() {
		String p = "career.id";
		Integer id = service.getCurrentUser().getCareer().getId();
		JPAContainer<File> cf = fileService.filterContainer(p, id);
		view.buildLastFilesTable(cf);
	}
}
