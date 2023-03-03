package Task;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import MainApp.AppManager;
import MainApp.EditTaskFrame;
import MainApp.SortBy;

public class ViewPortTasks extends JPanel{
	
	public AppManager manager;
	public EditTaskFrame editFrm;
	
	public void reloadTasks(SortBy type, String grName) {
		List<TaskInfor> allTasks = new ArrayList<>();
		if(grName != null && grName != "") allTasks = AppManager.data.taskDAO.getSortBy(type, grName);
		
		this.removeAll();
		for(int i=0; i<allTasks.toArray().length; i++) {
			TaskRenderer renderer = new TaskRenderer(i, allTasks.get(i));
			this.add(renderer);
		}
	}
	
	private int numberOfTasks = 0;
	
	public int getNumberOfTasks() {
		return numberOfTasks;
	}
	@Override
	public Component add(Component comp) {
		// TODO Auto-generated method stub
		if(comp instanceof TaskRenderer) {
			TaskRenderer task = new TaskRenderer();
			task = (TaskRenderer) comp;

			task.manager = manager;
			// update number of tasks in view port
			numberOfTasks++;
			// get height & space of a task
			int taskHeight = task.getHeight();
			int taskSpace = task.getSpace();
			// set new height of view port
			int newHeight = (taskHeight + taskSpace) * numberOfTasks + 20;
			// change size (only set height & ignor width) of view port
			this.setPreferredSize(new Dimension(10, newHeight));
			
			return super.add(comp);
		}
		
		return null;
	}
}
