package MainApp;

import javax.swing.JFrame;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import Task.TaskInfor;
import com.toedter.calendar.JDateChooser;

import Controller.ReminderManager;
import Group.ComboBoxGroupModel;

import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class EditTaskFrame {

	private JFrame frmEditTask;

	private int idTaskEditting;
	private JComboBox cbbGroup;
	private JTextPane txtDetail;
	private JTextPane txtTitle;
	private JDateChooser dateChooser;
	private JSpinner spHour;
	private JSpinner spMin;
	private JLabel lblRemindValue;

	public EditTaskFrame(TaskInfor infor) {
		initialize();
		frmEditTask.setVisible(true);
		frmEditTask.setTitle("Edit (" + infor.getTitle() + ") task");
		idTaskEditting = infor.getId();
		// set group name
		for (int i = 0; i < cbbGroup.getModel().getSize(); i++) {
			if (infor.getGrName().equals(cbbGroup.getItemAt(i).toString())) {
				cbbGroup.setSelectedIndex(i);
			}

		}
		txtTitle.setText(infor.getTitle()); // set title
		txtDetail.setText(infor.getDetail()); // set Detail
		lblRemindValue.setText(infor.isOnReminder() ? "On" : "Off");
		dateChooser.setCalendar(infor.getDeadLine()); // set date of deadline
		spHour.setValue(infor.getDeadLine().get(Calendar.HOUR_OF_DAY));
		spMin.setValue(infor.getDeadLine().get(Calendar.MINUTE));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEditTask = new JFrame();
		frmEditTask.getContentPane().setBackground(new Color(240, 255, 240));
		frmEditTask.setIconImage(
				Toolkit.getDefaultToolkit().getImage("D:\\My project\\Java\\ToDoList\\icons\\icons8-pencil-30.png"));
		frmEditTask.setResizable(false);
		frmEditTask.setBounds(new Rectangle(450, 100, 0, 0));
		frmEditTask.setBounds(100, 100, 472, 354);
		frmEditTask.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEditTask.getContentPane().setLayout(null);

		JPanel panelDetail = new JPanel();
		panelDetail.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelDetail.setBounds(10, 150, 441, 124);
		frmEditTask.getContentPane().add(panelDetail);
		panelDetail.setLayout(null);

		JLabel lblDetail = new JLabel("Detail");
		lblDetail.setIcon(new ImageIcon("D:\\My project\\Java\\ToDoList\\icons\\icons8-write-24.png"));
		lblDetail.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblDetail.setBounds(5, 0, 80, 25);
		panelDetail.add(lblDetail);

		txtDetail = new JTextPane();
		txtDetail.setBackground(new Color(253, 245, 230));
		txtDetail.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtDetail.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
		txtDetail.setBounds(10, 25, 421, 90);
		panelDetail.add(txtDetail);

		JPanel panelTitle = new JPanel();
		panelTitle.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTitle.setBounds(10, 10, 221, 55);
		frmEditTask.getContentPane().add(panelTitle);
		panelTitle.setLayout(null);

		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblTitle.setBounds(5, 0, 45, 20);
		panelTitle.add(lblTitle);

		txtTitle = new JTextPane();
		txtTitle.setFont(new Font("Comic Sans MS", Font.ITALIC, 11));
		txtTitle.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtTitle.setBackground(new Color(253, 245, 230));
		txtTitle.setBounds(10, 20, 200, 25);
		panelTitle.add(txtTitle);

		JPanel panelGroup = new JPanel();
		panelGroup.setLayout(null);
		panelGroup.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelGroup.setBounds(241, 10, 210, 55);
		frmEditTask.getContentPane().add(panelGroup);

		JLabel lblGroup = new JLabel("Group");
		lblGroup.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblGroup.setBounds(5, 0, 45, 20);
		panelGroup.add(lblGroup);

		cbbGroup = new JComboBox();
		cbbGroup.setModel(new ComboBoxGroupModel());
		cbbGroup.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cbbGroup.setToolTipText("current group");
		cbbGroup.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		cbbGroup.setBounds(10, 20, 149, 25);
		panelGroup.add(cbbGroup);

		JPanel panelDeadline = new JPanel();
		panelDeadline.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelDeadline.setBounds(10, 75, 326, 65);
		frmEditTask.getContentPane().add(panelDeadline);
		panelDeadline.setLayout(null);

		JLabel lblDeadline = new JLabel("Deadline");
		lblDeadline.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblDeadline.setBounds(5, 0, 60, 20);
		panelDeadline.add(lblDeadline);

		dateChooser = new JDateChooser();
		dateChooser.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		dateChooser.setBounds(46, 25, 110, 25);
		dateChooser.setMinSelectableDate(Calendar.getInstance().getTime());
		panelDeadline.add(dateChooser);

		JLabel lblDate = new JLabel("date");
		lblDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblDate.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		lblDate.setBounds(15, 25, 29, 25);
		panelDeadline.add(lblDate);

		spHour = new JSpinner();
		spHour.setModel(new SpinnerNumberModel(12, 0, 23, 1));
		spHour.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		spHour.setBounds(198, 25, 42, 25);
		panelDeadline.add(spHour);

		JLabel lblHour = new JLabel("hour");
		lblHour.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		lblHour.setBounds(166, 25, 36, 25);
		panelDeadline.add(lblHour);

		JLabel lblMin = new JLabel("min");
		lblMin.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		lblMin.setBounds(250, 25, 36, 25);
		panelDeadline.add(lblMin);

		spMin = new JSpinner();
		spMin.setModel(new SpinnerNumberModel(30, 0, 59, 1));
		spMin.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		spMin.setBounds(274, 25, 42, 25);
		panelDeadline.add(spMin);

		JLabel lblHHType = new JLabel("24h type");
		lblHHType.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblHHType.setFont(new Font("Comic Sans MS", Font.BOLD, 10));
		lblHHType.setBounds(166, 5, 55, 20);
		panelDeadline.add(lblHHType);

		JPanel panelReminder = new JPanel();
		panelReminder.setLayout(null);
		panelReminder.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelReminder.setBounds(346, 75, 105, 65);
		frmEditTask.getContentPane().add(panelReminder);

		JLabel lblReminder = new JLabel("Reminder");
		lblReminder.setIcon(new ImageIcon("D:\\My project\\Java\\ToDoList\\icons\\icons8-alarm-24.png"));
		lblReminder.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblReminder.setBounds(5, 0, 100, 25);
		panelReminder.add(lblReminder);

		lblRemindValue = new JLabel("Off");
		lblRemindValue.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblRemindValue.setBounds(70, 25, 45, 30);
		panelReminder.add(lblRemindValue);

		JButton btnReminder = new JButton("Set");
		btnReminder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lblRemindValue.getText().equals("Off"))
					lblRemindValue.setText("On");
				else
					lblRemindValue.setText("Off");
			}
		});
		btnReminder.setBackground(new Color(216, 191, 216));
		btnReminder.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnReminder.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		btnReminder.setBounds(10, 25, 55, 30);

		panelReminder.add(btnReminder);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// update value task
				if(saveValueAction()) {
					JOptionPane.showMessageDialog(null, "Update completed");
					frmEditTask.setVisible(false);
					
					Home frmHome = AppManager.home;
					String grName = frmHome.cbbGroup.getSelectedItem().toString();
					frmHome.reloadViewport(SortBy.findByString(frmHome.cbbSort.getSelectedItem().toString()), grName);
				
					// reload reminders
					AppManager.reminder = new ReminderManager(AppManager.data.taskDAO);
					AppManager.reminder.runReminders();
				}else{
					JOptionPane.showMessageDialog(null, "Update failded", "Failed", JOptionPane.ERROR_MESSAGE);
				};
			}
		});
		btnSave.setBackground(new Color(175, 238, 238));
		btnSave.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnSave.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		btnSave.setBounds(178, 286, 85, 21);
		frmEditTask.getContentPane().add(btnSave);
	}
	private boolean saveValueAction() {
		String grName = cbbGroup.getSelectedItem().toString();
		String title = txtTitle.getText();
		String detail = txtDetail.getText();
		boolean reminder = lblRemindValue.getText().equals("On") ? true : false; 
		//
		Calendar deadline = dateChooser.getCalendar();
		deadline.set(Calendar.HOUR_OF_DAY, (int) spHour.getValue());
		deadline.set(Calendar.MINUTE, (int) spMin.getValue());
		//
		TaskInfor newInfor = new TaskInfor(grName, title, detail, deadline, reminder);
		
		return AppManager.data.taskDAO.update(idTaskEditting, newInfor);
	}
}
