package com.yotelopaso.views;

import com.vaadin.ui.Button.ClickEvent;

public interface HomeView extends AbstractHomeView {
	
	public void setLastNews();
	
	interface HomeViewListener extends AbstractHomeViewListener {
		public void addWindowsNewsContent(String caption);
		public void buttonClick(String caption, ClickEvent event);
	}
	
	public void addListener(HomeViewListener listener);

	public void showNewsEditorWindow(Long id);

}
