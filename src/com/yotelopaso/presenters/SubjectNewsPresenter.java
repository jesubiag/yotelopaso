package com.yotelopaso.presenters;

import java.util.List;

import com.yotelopaso.domain.News;
import com.yotelopaso.persistence.NewsManager;
import com.yotelopaso.utils.DateUtils;
import com.yotelopaso.views.components.SubjectNews;

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
