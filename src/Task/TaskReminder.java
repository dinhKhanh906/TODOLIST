package Task;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

public class TaskReminder extends TimerTask{
	private String title;
	private Date timeRemind;
	
	public TaskReminder(Calendar deadline, String title) {
		this.title = title;
		this.timeRemind = deadline.getTime();
	}
	
	public Date getTimeRemind() {
		return timeRemind;
	}
	
	@Override
	public void run() {
		if(SystemTray.isSupported()) {
			try {
				this.displayTray();
				//
				// end
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("SystemTray is not supported");
		}
	}

	private void displayTray() throws AWTException, MalformedURLException {
		try {
			// Obtain only one instance of the SystemTray object
			SystemTray tray = SystemTray.getSystemTray();

			// If you want to create an icon in the system tray to preview
			Image image = Toolkit.getDefaultToolkit().createImage("some-icon.png");
			// Alternative (if the icon is on the classpath):
			// Image image =
			// Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

			TrayIcon trayIcon = new TrayIcon(image, "Java AWT Tray Demo");
			// Let the system resize the image if needed
			trayIcon.setImageAutoSize(true);
			// Set tooltip text for the tray icon
			trayIcon.setToolTip("System tray icon demo");
			tray.add(trayIcon);

			// Display info notification:
			trayIcon.displayMessage("TO_DO_LIST reminder", title, MessageType.INFO);
			// Error:
			// trayIcon.displayMessage("Hello, World", "Java Notification Demo",
			// MessageType.ERROR);
			// Warning:
			// trayIcon.displayMessage("Hello, World", "Java Notification Demo",
			// MessageType.WARNING);
		} catch (Exception ex) {
			System.err.print(ex);
		}
	}
}
