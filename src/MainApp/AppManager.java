package MainApp;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

import Controller.DataManager;
import Controller.ReminderManager;
public class AppManager {
	public static DataManager data = new DataManager();
	public static ReminderManager reminder;
	public static Home home;
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home = new Home();
					home.setVisible(true);
					// load reminders
					reminder = new ReminderManager(data.taskDAO);
					reminder.runReminders();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
			}
		});
	}
	
}
