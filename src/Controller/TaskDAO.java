package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import MainApp.SortBy;
import Task.TaskInfor;

public class TaskDAO extends SQLiteConnection implements DAO<TaskInfor>{

	@Override
	public List<TaskInfor> getAll() {
		List<TaskInfor> allTaskInfors = new ArrayList<>();
		
		try {
			String statement = "SELECT * FROM Tasks";
			PreparedStatement prepare = super.connection.prepareStatement(statement);
			ResultSet result = prepare.executeQuery();
			
			while(result.next()) {
				TaskInfor task = new TaskInfor();
				task.setId(result.getInt("id"));
				task.setTitle(result.getString("title"));
				task.setDetail(result.getString("detail"));
				task.setGrName(result.getString("grName"));
				
				// format datetime
				SimpleDateFormat format_dt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				// set deadline
				String str_deadline = result.getString("deadline");
				Calendar cal_deadline = Calendar.getInstance();
				java.util.Date date_dl = format_dt.parse(str_deadline);
				cal_deadline.setTime(date_dl);
				task.setDeadLine(cal_deadline);
				
				// set create time
				String str_create = result.getString("createTime");
				Calendar cal_create = Calendar.getInstance();
				java.util.Date date_create = format_dt.parse(str_create);
				cal_create.setTime(date_create);
				task.setTimeCreate(cal_create);
				
				// set reminder
				task.setReminder(result.getInt("reminder") == 1 ? true : false);
				
				allTaskInfors.add(task);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return allTaskInfors;
	}
	
	@Override
	public boolean save(TaskInfor newItem) {
		String sqlStatement = "INSERT INTO Tasks(grName, title, detail, deadline, reminder) "
				+ "VALUES(?, ?, ?, ?, ?);";
		
		try {
			PreparedStatement prepare = super.connection.prepareStatement(sqlStatement);
			prepare.setString(1, newItem.getGrName());
			prepare.setString(2, newItem.getTitle());
			prepare.setString(3, newItem.getDetail());
			
			prepare.setString(4, newItem.calendarToString(newItem.getDeadLine()));
			prepare.setInt(5, newItem.isOnReminder() ? 1 : 0);
			
			prepare.executeUpdate();
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Task information invalid", "Input warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}

	@Override
	public boolean update(Object id, TaskInfor newValue) {
		String statement = "UPDATE Tasks " 
							+ "SET grName = ?, title = ?, detail = ?, deadline = ?, reminder = ? "
							+ "WHERE id = " + id + ";";
		try {
			PreparedStatement prepare = super.connection.prepareStatement(statement);
			prepare.setString(1, newValue.getGrName());
			prepare.setString(2, newValue.getTitle());
			prepare.setString(3, newValue.getDetail());
			prepare.setString(4, newValue.calendarToString(newValue.getDeadLine()));
			prepare.setInt(5, newValue.isOnReminder() ? 1 : 0);
			
			return prepare.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}

	@Override
	public void delete(TaskInfor item) {
		String statement = "DELETE FROM Tasks WHERE id = ?;";

		try {
			PreparedStatement prepare = super.connection.prepareStatement(statement);
			prepare.setString(1, item.getId() + "");
			
			prepare.executeUpdate();
		} catch (SQLException e) {
			System.out.println("delete failed");
			e.printStackTrace();
		}
	}
	
	public List<TaskInfor> getSortBy(SortBy type, String grName) {
		List<TaskInfor> list = new ArrayList<>();
		String statement = "SELECT * FROM Tasks ";
		if(grName != null) statement += "WHERE grName = '" + grName + "' ";
		switch (type) {
		case all: {
			statement += ";";
			break;
		}
		case deadline: {
			statement += "ORDER BY datetime(deadline) ASC;";
			break;
		}
		case new_est: {
			statement += "ORDER BY datetime(createTime) ASC;";
			break;
		}
		case reminderOn: {
			statement += "AND reminder = 1;";
			break;
		}
		case reminderOff: {
			statement += "AND reminder = 0;";
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
		
		try {
			PreparedStatement prepare = super.connection.prepareStatement(statement);
			ResultSet result = prepare.executeQuery();
			
			while(result.next()) {
				TaskInfor task = new TaskInfor();
				task.setId(result.getInt("id"));
				task.setTitle(result.getString("title"));
				task.setDetail(result.getString("detail"));
				task.setGrName(result.getString("grName"));
				
				// format datetime
				SimpleDateFormat format_dt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				// set deadline
				String str_deadline = result.getString("deadline");
				Calendar cal_deadline = Calendar.getInstance();
				java.util.Date date_dl = format_dt.parse(str_deadline);
				cal_deadline.setTime(date_dl);
				task.setDeadLine(cal_deadline);
				
				// set create time
				String str_create = result.getString("createTime");
				Calendar cal_create = Calendar.getInstance();
				java.util.Date date_create = format_dt.parse(str_create);
				cal_create.setTime(date_create);
				task.setTimeCreate(cal_create);
				
				// set reminder
				task.setReminder(result.getInt("reminder") == 1 ? true : false);
				
				list.add(task);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
