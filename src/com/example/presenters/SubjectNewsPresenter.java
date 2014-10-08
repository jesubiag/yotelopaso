package com.example.presenters;

import java.util.List;

import com.example.domain.News;
import com.example.persistence.NewsManager;
import com.example.utils.DateUtils;
import com.example.views.components.SubjectNews;

public class SubjectNewsPresenter implements SubjectNews.SubjectNewsListener {
	
	SubjectNews view;
	NewsManager service = new NewsManager();
	
	public SubjectNewsPresenter(SubjectNews view) {
		this.view = view;
	}

	@Override
	public void setContent(String careerName, String subjectName) {
		List<News> news = service.filterByCareerAndSubject(careerName, subjectName);
		for (News n : news) {
			String date = DateUtils.dateFormat(n.getDate());
			String content = n.getContent();
			view.buildComponent(date, content);
		}
		
	}

}
