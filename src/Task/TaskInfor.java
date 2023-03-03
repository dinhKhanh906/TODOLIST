package Task;

import java.awt.HeadlessException;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class TaskInfor {
	private int id;
	private String grName;
	private String title;
	private String detail;
	private Calendar timeCreate;
	private Calendar deadLine;
	private boolean onReminder;
	
	public TaskInfor() {}
	
	public TaskInfor(String grName, String title, String detail, Calendar deadLine, boolean onReminder) {
		// TODO Auto-generated constructor stub
		this.setGrName(grName);
		this.setTitle(title);
		this.setDetail(detail);
		this.setTimeCreate();
		this.setDeadLine(deadLine);
		this.setReminder(onReminder);
	}
	
	public void edit(String title, String detail, Calendar deadLine, boolean onReminder) {
		this.setTitle(title);
		this.setDetail(detail);
		this.setDeadLine(deadLine);
		this.setReminder(onReminder);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(title.length() <= 10) this.title = title;
		else {
			try {
				JOptionPane.showMessageDialog(null, "this title is too long");
			}catch(HeadlessException e) {
				System.out.println(e);
			}
		}
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String description) {
		this.detail = description;
	}

	public Calendar getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate() {
		// set timeCreate is current time
		this.timeCreate = Calendar.getInstance();
	}
	
	
	public void setTimeCreate(Calendar timeCreate) {
		this.timeCreate = timeCreate;
	}

	public Calendar getDeadLine() {
		return deadLine;
	}
	
	public Date getDeadLineByDate() {
		int y = deadLine.get(Calendar.YEAR);
		int m = deadLine.get(Calendar.MONTH);
		int d = deadLine.get(Calendar.DAY_OF_MONTH);
		
		return new Date(y, m, d);
	}

	public void setDeadLine(Calendar deadLine) {
		this.deadLine = deadLine;
	}

	public boolean isOnReminder() {
		return onReminder;
	}

	public void setReminder(boolean onReminder) {
		this.onReminder = onReminder;
	}
	
	public String calendarToString(Calendar calendar) {
		String hour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
		String min = Integer.toString(calendar.get(Calendar.MINUTE));
		String sec = Integer.toString(calendar.get(Calendar.SECOND));
		String dd = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
		String mm = Integer.toString(calendar.get(Calendar.MONTH) + 1);
		String yyyy = Integer.toString(calendar.get(Calendar.YEAR));

		String str = yyyy + "-" + mm + "-" + dd + " " + hour + ":" + min;
		return str;
	}

	public String getGrName() {
		return grName;
	}

	public void setGrName(String grName) {
		this.grName = grName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
