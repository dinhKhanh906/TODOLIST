package Group;

import java.util.List;
import Task.TaskInfor;

public class GroupInfor {
	private String name;
	
	public List<TaskInfor> listTasks;

	public GroupInfor() {}
	public GroupInfor(String name) {
		setName(name);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
