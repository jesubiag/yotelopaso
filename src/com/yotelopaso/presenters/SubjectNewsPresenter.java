package com.yotelopaso.presenters;

import java.util.List;

import com.yotelopaso.components.SubjectNews;
import com.yotelopaso.domain.News;
import com.yotelopaso.domain.User;
import com.yotelopaso.persistence.NewsManager;
import com.yotelopaso.persistence.UserManager;
import com.yotelopaso.utils.DateUtils;

public class SubjectNewsPresenter implements SubjectNews.SubjectNewsListener {
	
	SubjectNews view;
	NewsManager service = new NewsManager();
	UserManager manUser = new UserManager();
	
	public SubjectNewsPresenter(SubjectNews view) {
		this.view = view;
	}

	@Override
	public void setContent(String careerName, String subjectName) {
		List<News> news = service.filterByCareerAndSubject(careerName, subjectName);
		for (News n : news) {
			String date = DateUtils.dateFormat(n.getDate());
			String title = n.getTitle();
			String content = n.getContent();
			Long id = n.getId();
			User autor = n.getAuthor();
			if (manUser.getCurrentUser().getId() == autor.getId())
					view.buildComponent(date, title, content, id, autor.getUsername());
			else
					view.buildComponent(date, title, content, null, autor.getUsername());
		}
		
	}

}
