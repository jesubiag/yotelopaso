package com.yotelopaso.views;

import com.yotelopaso.views.components.LastNews;

public interface HomeView extends AbstractHomeView {
	
	public void setLastNews(LastNews lastNews);
	
	interface HomeViewListener extends AbstractHomeViewListener {
		public void addWindowsNewsContent(String caption);
	}
	
	public void addListener(HomeViewListener listener);

}
