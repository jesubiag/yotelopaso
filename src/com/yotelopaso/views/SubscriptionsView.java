package com.yotelopaso.views;

import java.util.List;
import java.util.Set;

import com.yotelopaso.domain.Subject;

public interface SubscriptionsView extends AbstractHomeView {
	
	public void setOptionGroupData(List<Subject> subjectList, int index);
	public void selectSubjects(Set<Subject> subjects);
	public void showSuccesfullSaveNotification(String message);
	
	interface SubscriptionsViewListener extends AbstractHomeViewListener {
		public void getData();
		public void setData();
		public void setSelectedSubjects();
		public void buttonClick(String caption, Object... values);
	}
	
	public void addListener(SubscriptionsViewListener listener);

}
