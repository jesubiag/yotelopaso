package com.yotelopaso.persistence;

import java.util.List;

import javax.persistence.Query;

import com.yotelopaso.domain.News;

public class NewsManager extends DataManager<News> {
	
	public NewsManager() {
		super(News.class);
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

}
