package com.yotelopaso.views;

import com.vaadin.navigator.View;
import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

public interface AuthView extends View {
	
	public void addWindow(Window window);
	public void navigate(String viewName);
	public void closeWindow(Window window);
	public <T extends AbstractComponentContainer> Label addLine(final T component);
	public void openLink(String url, String windowName);

}
