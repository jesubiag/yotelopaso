package com.yotelopaso.persistence;

import java.util.List;

import javax.persistence.Query;

import com.yotelopaso.domain.Subject;

public class SubjectManager extends DataManager<Subject> {
	
	public SubjectManager() {
		super(Subject.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Subject> filterByCareerAndYear(final String careerName, final int year) {
		String jpql = "SELECT s "
				+ "FROM Subject s "
				+ "WHERE s.career.name = :careerName "
				+ "AND s.year = :year";
		Query query = em.createQuery(jpql);
		query.setParameter("careerName", careerName);
		query.setParameter("year", year);
		return query.getResultList();
	}
	
	public List<Subject> filterByCareer(final String careerName) {
		return getByProperty("career.name", careerName);
	}
	
}
