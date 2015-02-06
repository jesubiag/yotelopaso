package com.yotelopaso.views;

import java.util.List;

import com.yotelopaso.domain.Subject;

public interface SubscriptionsView extends AbstractHomeView {
	
	public void setOptionGroupData(List<Subject> subjectList, int index);
	public void selectSubjects(Integer year, Integer id);
	public void showSuccesfullSaveNotification(String message);
	
	interface SubscriptionsViewListener extends AbstractHomeViewListener {
		public void getData();
		public void setData();
		public void setSelectedSubjects();
		public void buttonClick(String caption, Object... values);
	}
	
	public void addListener(SubscriptionsViewListener listener);

}
