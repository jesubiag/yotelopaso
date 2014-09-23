package com.example.views.components;

import java.util.List;

import com.example.domain.News;
import com.example.persistence.NewsManager;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class LastNews extends CustomComponent {

	private static final long serialVersionUID = 1L;
	
	private VerticalLayout mainLayout;
	private Label date;
	private Label career;
	private Label subject;
	private Label content;
	private Button more;
	private NewsManager newsMngr = new NewsManager();
	private String careerName;
	
	public LastNews(String careerName) {
		this.careerName = careerName;
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}
	
	private void buildMainLayout() {
		
		HorizontalLayout topHorizontalLayout = new HorizontalLayout();
		VerticalLayout elementLayout = new VerticalLayout();
		
		mainLayout = new VerticalLayout();
		
		// date
		date = new Label();
		date.setContentMode(ContentMode.HTML);
		
		// career
		career = new Label();
		career.setContentMode(ContentMode.HTML);
		
		// subject
		subject = new Label();
		subject.setContentMode(ContentMode.HTML);
		
		// content
		content = new Label();
		content.setContentMode(ContentMode.HTML);
		
		// more
		more = new Button("Leer más >>");
		more.addStyleName(ValoTheme.BUTTON_LINK);
		
		topHorizontalLayout.setWidth("100%");
		topHorizontalLayout.setSpacing(true);
		
		elementLayout.setMargin(true);
		elementLayout.setSizeFull();
		elementLayout.setSpacing(true);
		
		List<News> news = newsMngr.getAllFromCareer(this.careerName);
		
		for (News n : news) {
			date.setValue(n.getDate().toString());
			career.setValue(careerName);
			subject.setValue(n.getSubject().getName());
			content.setValue(n.getContent());
			topHorizontalLayout.addComponents(date, career, subject);
			elementLayout.addComponents(topHorizontalLayout, content, more);
			mainLayout.addComponent(elementLayout);
		}
	}

}
