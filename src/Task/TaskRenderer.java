package Task;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import Controller.ReminderManager;
import MainApp.AppManager;
import MainApp.EditTaskFrame;
import MainApp.SortBy;

public class TaskRenderer extends JPanel{
	
	public AppManager manager;
	public EditTaskFrame editFrm;
	
	private int x = 10, y = 10, width = 618, height = 100;
	private int space = 20;
	private int index;
	private TaskInfor taskInfor;
	private JRadioButton btnCheckFinish;
	private JTextPane txtTitle;
	private JLabel lblTimeCreate;
	private JLabel lblDeadline;
	private JTextPane txtDetail;
	private JButton btnEdit;
	private boolean reminder;
	
	public TaskRenderer() {
		reminder = false;
		initialize(0,
					"..",
					"4pm-yyyy/mm/dd",
					"5pm-yyyy/mm/dd",
					"Detail....",
					reminder);
	}
	
	/*
	 * public TaskRenderer(int index) {
	 * 
	 * initialize(index, "..", "4pm-yyyy/mm/dd", "5pm-yyyy/mm/dd", "thứ ơ....",
	 * reminder); }
	 */
	
	public TaskRenderer(int index,TaskInfor infor) {
		this.taskInfor = infor;
		initialize(index, 
					infor.getTitle(), 
					infor.calendarToString(infor.getTimeCreate()), 
					infor.calendarToString(infor.getDeadLine()),
					infor.getDetail(),
					infor.isOnReminder());
	}
	
	public void setSpace(int value) {
		this.space = value;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getSpace() {
		return space;
	}
	
	public JTextPane getLblTitle() {
		return txtTitle;
	}
	
	private void setPosition(int index) {
		// set x
		//
		// set y
		this.y += (height + space) * index;
	}
	
	private void finishTask() {
		this.setVisible(false);

		// remove this task infor in database
		manager.data.taskDAO.delete(taskInfor);
		// reload renderer
		manager.home.reloadViewport(SortBy.findByString(manager.home.cbbSort.getSelectedItem().toString()), manager.home.cbbGroup.getSelectedItem().toString());
		// reload reminders
		manager.reminder = new ReminderManager(manager.data.taskDAO);
		manager.reminder.runReminders();
	}
	private void initialize(int index, String title, String timeCreate, String deadline, String detail, boolean onReminder) {	
		setPosition(index);
		
		this.setBackground(new Color(250, 235, 215));
		this.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.setBounds(this.x, this.y, this.width, this.height);
		this.setLayout(null);
		
		btnCheckFinish = new JRadioButton("");
		btnCheckFinish.setBackground(new Color(250, 235, 215));
		btnCheckFinish.setIcon(null);
		btnCheckFinish.setToolTipText("select to finish this task");
		btnCheckFinish.setBounds(6, 38, 21, 21);
		btnCheckFinish.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Completed");
				finishTask();
			}
		});
		this.add(btnCheckFinish);
		
		txtTitle = new JTextPane();
		txtTitle.setText("Title: " + title);
		txtTitle.setToolTipText("title");
		txtTitle.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtTitle.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		txtTitle.setBounds(33, 5, 138, 20);
		txtTitle.setBackground(new Color(250, 235, 215));
		txtTitle.setSelectionColor(new Color(188, 143, 143));
		txtTitle.setEditable(false);
		this.add(txtTitle);
		
		// create a label into this frame
		lblTimeCreate = new JLabel("Create: " + timeCreate);
		lblTimeCreate.setToolTipText("time create");
		lblTimeCreate.setFont(new Font("Comic Sans MS", Font.BOLD, 10));
		lblTimeCreate.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblTimeCreate.setBounds(181, 5, 160, 20);
		this.add(lblTimeCreate);
		
		lblDeadline = new JLabel("Deadline: " + deadline);
		lblDeadline.setToolTipText("deadline");
		lblDeadline.setFont(new Font("Comic Sans MS", Font.BOLD, 10));
		lblDeadline.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblDeadline.setBounds(353, 5, 160, 20);
		this.add(lblDeadline);
		
		JScrollPane scrollDescription = new JScrollPane();
		scrollDescription.setBackground(new Color(176, 224, 230));
		scrollDescription.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollDescription.setBorder(new LineBorder(new Color(130, 135, 144)));
		scrollDescription.setBounds(33, 30, 479, 65);
		this.add(scrollDescription);
		
		txtDetail = new JTextPane();
		txtDetail.setText(detail); 
		txtDetail.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtDetail.setToolTipText("description");
		txtDetail.setBackground(new Color(250, 235, 215));
		txtDetail.setSelectionColor(new Color(188, 143, 143));
		txtDetail.setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
		txtDetail.setEditable(false);
		scrollDescription.setViewportView(txtDetail);
		
		JPanel panelReminder = new JPanel();
		panelReminder.setToolTipText("reminder");
		panelReminder.setBackground(new Color(250, 235, 215));
		panelReminder.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelReminder.setBounds(523, 5, 85, 44);
		this.add(panelReminder);
		panelReminder.setLayout(null);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon("D:\\My project\\Java\\ToDoList\\icons\\icons8-alarm-24.png"));
		lblIcon.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		lblIcon.setBounds(10, 10, 25, 25);
		panelReminder.add(lblIcon);
		
		JLabel lblRemindValue = new JLabel();
		lblRemindValue.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblRemindValue.setBounds(40, 6, 45, 30);
		if(onReminder) lblRemindValue.setText("On");
		else lblRemindValue.setText("Off");
		panelReminder.add(lblRemindValue);
		
		btnEdit = new JButton("edit");
		btnEdit.setBackground(new Color(245, 245, 220));
		btnEdit.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnEdit.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnEdit.setBounds(523, 59, 85, 31);
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				editFrm = new EditTaskFrame(taskInfor);
			}
		});
		this.add(btnEdit);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}
