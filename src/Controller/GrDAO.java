package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Group.GroupInfor;

public class GrDAO extends SQLiteConnection implements DAO<GroupInfor>{

	private List<GroupInfor> allGroups = new ArrayList<>();
	
	@Override
	public List<GroupInfor> getAll() {
		String sqlStatement = "SELECT * FROM Gr";
		
		try {
			PreparedStatement prepare = connection.prepareStatement(sqlStatement);
			ResultSet result = prepare.executeQuery();
			
			while(result.next()) {
				GroupInfor group = new GroupInfor();
				group.setName(result.getString("name"));
				
				allGroups.add(group);
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
		return allGroups;
	}

	@Override
	public boolean save(GroupInfor newItem) {
		String sqlStatement = "INSERT INTO GR(name) VALUES(?);";
		
		try {
			PreparedStatement prepare = super.connection.prepareStatement(sqlStatement);
			prepare.setString(1, newItem.getName());
			
			prepare.execute();
			
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Group name invalid");
			
			return false;
		}
	}

	@Override
	public boolean update(Object key, GroupInfor newValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(GroupInfor item) {
		// TODO Auto-generated method stub
		
	}

}
