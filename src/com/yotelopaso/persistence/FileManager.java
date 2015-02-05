package com.yotelopaso.persistence;

import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.filter.Compare;
import com.yotelopaso.domain.File;
import com.yotelopaso.domain.Subject;

public class FileManager extends DataManager<File> {

	public FileManager() {
		super(File.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<File> filterByCareer(Integer careerId) {
		String jpql = "SELECT f "
				+ "FROM File f "
				+ "WHERE f.career.id = :id "
				+ "ORDER BY f.creationDate DESC";
		Query query = em.createQuery(jpql);
		query.setParameter("id", careerId);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<File> filterByCareerSubjectType(String careerName, String subjectName, File.Type fileType) {
		String jpql = "SELECT f "
				+ "FROM File f "
				+ "WHERE f.subject.name = :subjectName "
				+ "AND f.type = :type "
				+ "AND f.subject.career.name = :careerName "
				+ "ORDER BY f.creationDate DESC";
		Query query = em.createQuery(jpql);
		query.setParameter("subjectName", subjectName);
		query.setParameter("type", fileType);
		query.setParameter("careerName", careerName);
		return query.getResultList();
	};
	
	public JPAContainer<File> getFilesFromSubscription(Set<Subject> subjects) {
		String propertyId = "subject";
		for (Subject s : subjects) {
			Filter filter = new Compare.Equal(propertyId, s);
			container.addContainerFilter(filter);
		}
		return container;
	}

}
