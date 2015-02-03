package com.yotelopaso.views;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.yotelopaso.domain.File;
import com.yotelopaso.domain.News;


public interface HomeView extends AbstractHomeView {
	
	public void buildLastNewsTable(JPAContainer<News> container);
	public void buildLastFilesTable(JPAContainer<File> container);
	
	interface HomeViewListener extends AbstractHomeViewListener {
		public void initLastNewsTable();
		public void initLastFilesTable();
		public void buttonClick(String caption, Long newsId);
		public void itemClick(String sourceId, Object itemId);
	}
	
	public void addListener(HomeViewListener listener);

	public void showNewsEditorWindow(Long id);

}
