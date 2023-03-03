package Controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

import Task.TaskInfor;
import Task.TaskReminder;

public class ReminderManager {
	Timer timer;
	List<TaskReminder> allTaskReminder = new ArrayList<>();
	
	public List<TaskReminder> getAllTaskReminder() {
		return allTaskReminder;
	}

	public void setAllTaskReminder(List<TaskReminder> allTaskReminder) {
		this.allTaskReminder = allTaskReminder;
	}

	public ReminderManager(TaskDAO dao) {
		List<TaskInfor> allInfor = dao.getAll();
		for (TaskInfor taskInfor : allInfor) {
			int compareTime = taskInfor.getDeadLine().compareTo(Calendar.getInstance()); // return value > 0 if deadline is after current time
			if(taskInfor.isOnReminder() && compareTime > 0) {
				TaskReminder newReminder = new TaskReminder(taskInfor.getDeadLine(), taskInfor.getTitle());
				allTaskReminder.add(newReminder);	
			}
		}
	}
	
	public void runReminders() {
		if(timer != null) removeCurrentReminders();
		
		timer = new Timer();
		for (TaskReminder taskReminder : allTaskReminder) {
			timer.schedule(taskReminder, taskReminder.getTimeRemind());
			
		}
		System.out.println("run reminder");
	}
	
	private void removeCurrentReminders() {
		timer.cancel();
		timer.purge();
	}
}
