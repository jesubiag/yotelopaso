package com.yotelopaso.persistence;

import java.util.List;

import javax.persistence.Query;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.yotelopaso.domain.Subject;

public class SubjectManager extends DataManager<Subject> {
	
	public SubjectManager() {
		super(Subject.class);
		container.sort(new String[] {"name"}, new boolean[] {true});
	}
	
	@SuppressWarnings("unchecked")
	public List<Subject> filterByCareerAndYear(final String careerName, final int year) {
		String jpql = "SELECT s "
				+ "FROM Subject s "
				+ "WHERE s.career.name = :careerName "
				+ "AND s.year = :year "
				+ "ORDER BY s.name ASC";
		//Agregar orden a la query
		Query query = em.createQuery(jpql);
		query.setParameter("careerName", careerName);
		query.setParameter("year", year);
		return query.getResultList();
	}
	
	public List<Subject> filterByCareer(final String careerName) {
		return getByProperty("career.name", careerName);
	}
	
	public JPAContainer<Subject> filterContainerByCareer(Integer careerId) {
		String propertyId = "career.id";
		return filterContainer(propertyId, careerId);
	}
}
