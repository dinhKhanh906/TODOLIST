package MainApp;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;

import Group.ComboBoxGroupModel;
import Group.GroupInfor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddGroup extends JFrame{

	private JTextPane txtName;
	public CreateTaskFrame frmCreate;

	/**
	 * Create the application.
	 */
	public AddGroup() {
		initialize();
		this.setVisible(true);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle("Add new group");
		this.getContentPane().setBackground(new Color(255, 248, 220));
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\My project\\Java\\ToDoList\\icons\\icons8-happy-file-32.png"));
		this.setBounds(300, 100, 315, 164);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		txtName = new JTextPane();
		txtName.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		txtName.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
		txtName.setBounds(10, 36, 281, 42);
		this.getContentPane().add(txtName);
		
		JLabel lbl = new JLabel("Name's new group");
		lbl.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lbl.setBounds(10, 13, 150, 20);
		this.getContentPane().add(lbl);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtName.getText().trim().equals("")) {
					GroupInfor grInfor = new GroupInfor(txtName.getText().trim());
					AppManager.data.grDAO.save(grInfor);
					// reload model of combobox
					AppManager.home.cbbGroup.setModel(new ComboBoxGroupModel());
					if(frmCreate != null) frmCreate.cbbGroup.setModel(new ComboBoxGroupModel());
					setVisible(false);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "this name can not empty");
				}
			}
		});
		btnAdd.setBackground(new Color(152, 251, 152));
		btnAdd.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnAdd.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnAdd.setBounds(105, 88, 85, 29);
		this.getContentPane().add(btnAdd);
	}

}
