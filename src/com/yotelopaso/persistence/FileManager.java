package com.yotelopaso.persistence;

import java.util.List;

import javax.persistence.Query;

import com.yotelopaso.domain.File;

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

}
