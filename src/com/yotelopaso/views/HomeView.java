package com.yotelopaso.views;


public interface HomeView extends AbstractHomeView {
	
	public void setLastNews();
	
	interface HomeViewListener extends AbstractHomeViewListener {
		public void addWindowsNewsContent(String caption);
		public void buttonClick(String caption, Long newsId);
	}
	
	public void addListener(HomeViewListener listener);

	public void showNewsEditorWindow(Long id);

}
