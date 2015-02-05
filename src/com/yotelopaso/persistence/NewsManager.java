package com.yotelopaso.persistence;

import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.filter.Compare;
import com.yotelopaso.domain.News;
import com.yotelopaso.domain.Subject;

public class NewsManager extends DataManager<News> {
	
	public NewsManager() {
		super(News.class);
		container.sort(new String[] {"date"}, new boolean[] {false});
	}
	
	@SuppressWarnings("unchecked")
	public List<News> getAllFromCareer(String careerName) {
		String jpql = "SELECT n FROM News n WHERE n.career.name = :careerName "
				+ "ORDER BY n.id DESC";
		Query query = em.createQuery(jpql);
		query.setParameter("careerName", careerName);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<News> filterByCareerAndSubject(String careerName, String subjectName) {
		String jpql = "SELECT n "
				+ "FROM News n "
				+ "WHERE n.career.name = :careerName "
				+ "AND n.subject.name = :subjectName "
				+ "ORDER BY n.id DESC";
		Query query = em.createQuery(jpql);
		query.setParameter("careerName", careerName);
		query.setParameter("subjectName", subjectName);
		return query.getResultList();
	}
	

	public JPAContainer<News> getNewsFromSubscription(Set<Subject> subjects) {
		String propertyId = "subject";
		for (Subject s : subjects) {
			Filter filter = new Compare.Equal(propertyId, s);
			container.addContainerFilter(filter);
		}
		return container;
	}

}
