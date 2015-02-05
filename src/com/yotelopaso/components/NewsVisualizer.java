package com.yotelopaso.components;

import java.util.Date;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import com.yotelopaso.domain.News;
import com.yotelopaso.domain.Subject;
import com.yotelopaso.domain.User;
import com.yotelopaso.persistence.NewsManager;
import com.yotelopaso.utils.DateUtils;


public class NewsVisualizer extends Window {

	private static final long serialVersionUID = 1L;

	private NewsManager manNews = new NewsManager();
	private News news = new News();
	private Subject materia;
	private User author;
	private Date newsDate;
	private Long id;
	private HorizontalLayout topHorizontalLayout;

	public NewsVisualizer(Long id) {
		this.id = id;
		news=manNews.getById(this.id);
		materia=news.getSubject();
		author=news.getAuthor();
		newsDate=news.getDate();
			
		Label date = new Label();
		date.setValue("Publicado: " + DateUtils.dateFormat(newsDate));
		date.setStyleName(ValoTheme.LABEL_TINY);
		date.setSizeUndefined();
		
		Label authorName = new Label();
		authorName.setValue("Autor: " + author.getUsername());
		authorName.setStyleName(ValoTheme.LABEL_TINY);
		authorName.setSizeUndefined();
		
		Label contentNews = new Label();
		contentNews.setContentMode(ContentMode.HTML);
		contentNews.setValue(news.getContent());
		contentNews.setWidth("100%");
		
		Panel panel = new Panel();
		panel.setContent(contentNews);
		panel.setWidth("100%");
		panel.setHeight("300px");
		panel.setStyleName(ValoTheme.PANEL_WELL);
		
		topHorizontalLayout = new HorizontalLayout();
		topHorizontalLayout.setSizeFull();
		topHorizontalLayout.setSpacing(true);
		
		topHorizontalLayout.addComponents(authorName,date);
		topHorizontalLayout.setComponentAlignment(date, Alignment.MIDDLE_RIGHT);
		topHorizontalLayout.setComponentAlignment(authorName, Alignment.MIDDLE_LEFT);
		
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.setMargin(true);
		vLayout.addComponent(panel);
		vLayout.addComponent(topHorizontalLayout);
		
		setContent(vLayout);
		center();
		setWidth("650px");	
		setResizable(false);
		setModal(true);
		setCaption(materia.getName() + " - " + news.getTitle());
	}

}