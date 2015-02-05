package com.yotelopaso.views;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.yotelopaso.domain.File;
import com.yotelopaso.domain.News;
import com.yotelopaso.domain.UserCalendarEvent;


public interface HomeView extends AbstractHomeView {
	
	public void buildLastNewsTable(JPAContainer<News> container);
	public void buildLastFilesTable(JPAContainer<File> container);
	public void buildLastEventsTable(JPAContainer<UserCalendarEvent> container);
	
	interface HomeViewListener extends AbstractHomeViewListener {
		public void initLastNewsTable();
		public void initLastFilesTable();
		public void initLastEventsTable();
		public void itemClick(String sourceId, Object itemId);
		public void setInitData();
	}
	
	public void addListener(HomeViewListener listener);

	public void showEventWindow(UserCalendarEvent event);
	public void showNewsVisualizerWindow(Long newsId);

}
