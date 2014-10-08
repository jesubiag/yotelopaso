package com.example.views.components;

import java.util.List;

import com.example.domain.News;
import com.example.persistence.NewsManager;
import com.example.utils.DateUtils;
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
	private NewsManager newsMngr = new NewsManager();
	private String careerName;
	
	public LastNews(String careerName) {
		this.careerName = careerName;
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}
	
	private void buildMainLayout() {
		
		mainLayout = new VerticalLayout();
		
		List<News> news = newsMngr.getAllFromCareer(this.careerName);
		
		for (News n : news) {
			
			// date
			Label date = new Label();
			date.setContentMode(ContentMode.HTML);
			
			// career
			Label career = new Label();
			career.setContentMode(ContentMode.HTML);
			
			// subject
			Label subject = new Label();
			subject.setContentMode(ContentMode.HTML);
			
			// content
			Label content = new Label();
			content.setContentMode(ContentMode.HTML);
			HorizontalLayout topHorizontalLayout = new HorizontalLayout();
			VerticalLayout elementLayout = new VerticalLayout();
			
			topHorizontalLayout.setWidth("100%");
			topHorizontalLayout.setSpacing(false);
			
			elementLayout.setMargin(true);
			elementLayout.setSizeFull();
			elementLayout.setSpacing(true);
			
			date.setValue(DateUtils.dateFormat(n.getDate()));
			career.setValue(careerName);
			subject.setValue(n.getSubject().getName());
			content.setValue(n.getContent());
			topHorizontalLayout.addComponents(date, career, subject);
			elementLayout.addComponents(topHorizontalLayout, content);
			mainLayout.addComponent(elementLayout);
		}
	}

}
