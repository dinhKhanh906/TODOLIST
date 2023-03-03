package Controller;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class SQLiteConnection {
	protected static String crTasks = "CREATE TABLE Tasks("
			+ "    id INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "    grName NVARCHAR(10) NOT NULL REFERENCES GR(name),"
			+ "    title NVARCHAR(15) NOT NULL CHECK (title != ''),"
			+ "    detail NVARCHAR(100),"
			+ "    createTime datetime DEFAULT (datetime('now', 'localtime')) NOT NULL,"
			+ "    deadline datetime CHECK(deadline > createTime) NOT NULL,"
			+ "    reminder BIT CHECK (reminder = 1 or reminder = 0) NOT NULL"
			+ ");";
	protected static String crGr = "CREATE TABLE GR(" + "    name NVARCHAR(10) PRIMARY KEY NOT NULL" + ");";

	protected static String url = "jdbc:sqlite:TODOLIST.db";
	protected Connection connection = null;
	public static void main(String[] args) {
		new SQLiteConnection();
	}
	
	public SQLiteConnection() {
		connectSqlite();
	}
	
	private void connectSqlite() {
		File f = new File("TODOLIST.db"); // name of file data base
		
		try {
			// check file database has already exist
			if (f.createNewFile()) {
				// did not have the file before -> create new file
				// and connect
				connectDatabase();
				// create tables for database
				createTables();
			}
			else {
				// had the file before -> open this file 
				//and connect
				connectDatabase();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// create new table into database
	private void createTables() {
		try {
			// create GR table
			PreparedStatement createGr = connection.prepareStatement(crGr); createGr.execute();
			// create Tasks table
			PreparedStatement createTasks = connection.prepareStatement(crTasks); createTasks.execute();
		}catch (Exception e) {
			/*
			 * JOptionPane.showMessageDialog(null, e.getMessage()); e.printStackTrace();
			 */
		}
	}
	
	private void connectDatabase() {
		try {
			connection = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println("failed");
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
}
