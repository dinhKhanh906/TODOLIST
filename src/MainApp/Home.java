package MainApp;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;

import Group.ComboBoxGroupModel;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Toolkit;

import Task.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame{

	//public JFrame this;
	public ViewPortTasks panelViewPort;
	
	private JScrollPane scrollPane;
	public JComboBox cbbGroup;
	public JComboBox cbbSort;
	
	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void reloadViewport(SortBy type, String grName) {
		panelViewPort = new ViewPortTasks();
		panelViewPort.setBackground(new Color(192, 192, 192));
		panelViewPort.setLayout(null);
		scrollPane.setViewportView(panelViewPort);

		// add new task --> for test
		panelViewPort.reloadTasks(type, grName);
	}
	private void sortByAction() {
		
	}
	private void initialize() {
		//this = new JFrame();
		this.getContentPane().setBackground(new Color(255, 250, 240));
		this.setTitle("To-do-list");
		this.setIconImage(Toolkit.getDefaultToolkit()
				.getImage("D:\\My project\\Java\\ToDoList\\icons\\icons8-checklist-32 (1).png"));
		this.setResizable(false);
		this.setBounds(400, 50, 800, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 10, 657, 643);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		this.getContentPane().add(scrollPane);

		JButton btnCreate = new JButton("create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// open create new task frame
				if(cbbGroup.getItemAt(0) != null) {
					new CreateTaskFrame();
				}
				else {
					JOptionPane.showMessageDialog(null, "have not any group, now");
					new AddGroup();
				}
			}
		});
		btnCreate.setBackground(new Color(152, 251, 152));
		btnCreate.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnCreate.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnCreate.setToolTipText("create new task");
		btnCreate.setIcon(null);
		btnCreate.setBounds(677, 20, 99, 40);
		this.getContentPane().add(btnCreate);

		cbbSort = new JComboBox();
		cbbSort.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cbbSort.setBackground(new Color(224, 255, 255));
		cbbSort.setToolTipText("sort tasks");
		cbbSort.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		cbbSort.setModel(new DefaultComboBoxModel<>(SortBy.getAllValues().toArray()));
		cbbSort.setBounds(677, 180, 99, 40);
		cbbSort.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String grName = cbbGroup.getSelectedItem().toString();
				reloadViewport(SortBy.findByString(cbbSort.getSelectedItem().toString()), grName);
			}
		});
		this.getContentPane().add(cbbSort);

		cbbGroup = new JComboBox();
		cbbGroup.setModel(new ComboBoxGroupModel());
		cbbGroup.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cbbGroup.setBackground(new Color(222, 184, 135));
		cbbGroup.setToolTipText("select group to view");
		cbbGroup.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		cbbGroup.setBounds(677, 100, 99, 40);
		cbbGroup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String grName = cbbGroup.getSelectedItem().toString();
				reloadViewport(SortBy.findByString(cbbSort.getSelectedItem().toString()), grName);
			}
		});
		this.getContentPane().add(cbbGroup);

		JLabel lblGroup = new JLabel("group");
		lblGroup.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		lblGroup.setBounds(677, 80, 45, 20);
		this.getContentPane().add(lblGroup);

		JLabel lblSort = new JLabel("sort by");
		lblSort.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		lblSort.setBounds(677, 160, 60, 20);
		this.getContentPane().add(lblSort);
		
		// load tasks renderer
		if(cbbGroup.getSelectedItem() != null) {
			String grName = cbbGroup.getSelectedItem().toString();
			reloadViewport(SortBy.all, grName);
		}else reloadViewport(SortBy.all, null);
	}
}
