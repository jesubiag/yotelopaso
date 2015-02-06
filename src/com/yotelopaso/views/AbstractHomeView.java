package com.yotelopaso.views;

import com.vaadin.ui.Component;

public interface AbstractHomeView extends AuthView {
	
	interface AbstractHomeViewListener {
		void panelButtonClick(String caption);
	}
	
	public void addListener(AbstractHomeViewListener listener);

	public Component buildTitle(String frase);

}
