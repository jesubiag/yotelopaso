package com.yotelopaso.views;

import com.vaadin.navigator.View;
import com.vaadin.ui.Window;

public interface AuthView extends View {
	
	public void addWindow(Window window);
	public void navigate(String viewName);

}
