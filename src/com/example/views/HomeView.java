package com.example.views;

import com.example.views.components.LastNews;

public interface HomeView extends AbstractHomeView {
	
	public void setLastNews(LastNews lastNews);
	
	interface HomeViewListener extends AbstractHomeViewListener {
		public void componentAttachedToContainer(String caption);
	}
	
	public void addListener(HomeViewListener listener);

}
