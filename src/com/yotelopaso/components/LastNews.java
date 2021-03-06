package com.yotelopaso.components;

import java.util.List;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.yotelopaso.domain.News;
import com.yotelopaso.persistence.NewsManager;
import com.yotelopaso.utils.DateUtils;
import com.yotelopaso.utils.Hr;

public class LastNews extends CustomComponent {
	
	private static final long serialVersionUID = 1L;
	
	private VerticalLayout mainLayout;
	private NewsManager newsMngr = new NewsManager();
	private String careerName;
	private ClickListener parentView;
	
	public LastNews(String careerName, ClickListener parentView) {
		this.careerName = careerName;
		this.parentView = parentView;
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
			
			// title
			Label title = new Label();
			title.setContentMode(ContentMode.HTML);
			
			// author
			Label autMail = new Label();
			autMail.setContentMode(ContentMode.HTML);
			
			HorizontalLayout topHorizontalLayout = new HorizontalLayout();
			VerticalLayout elementLayout = new VerticalLayout();
			
			topHorizontalLayout.setWidth("100%");
			topHorizontalLayout.setSpacing(false);
			
			elementLayout.setMargin(false);
			elementLayout.setSizeFull();
			elementLayout.setSpacing(true);
			
			date.setValue(DateUtils.dateFormat(n.getDate()));
			career.setValue(careerName);
			subject.setValue(n.getSubject().getName());
			content.setValue(n.getContent());
			title.setValue(n.getTitle());
			autMail.setValue(n.getAuthor().getEmail());
			
			Button ampliarButton = new Button("Ampliar");
			ampliarButton.addClickListener(parentView);
			ampliarButton.setData(n.getId());
			ampliarButton.setHeight("70%");
			ampliarButton.setWidth("40%");
			
			topHorizontalLayout.addComponents(date, career, subject, autMail, ampliarButton);
			elementLayout.addComponents(topHorizontalLayout, title, new Hr());
			mainLayout.addComponent(elementLayout);
		}
	}

}
