package Group;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import Controller.GrDAO;

public class ComboBoxGroupModel extends DefaultComboBoxModel{
	public ComboBoxGroupModel() {
		setElements();
	}
	
	public void setElements() {
		List<GroupInfor> list = new GrDAO().getAll();
		super.removeAllElements();
		for (GroupInfor groupInfor : list) {
			super.addElement(groupInfor.getName());
		}
	}
}
