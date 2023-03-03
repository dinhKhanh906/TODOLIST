package MainApp;

import java.util.ArrayList;
import java.util.List;

public enum SortBy {
	all("All"),
	new_est("New-est"),
	deadline("Deadline"),
	reminderOn("ON-remind"),
	reminderOff("OFF-remind");
	//--
	
	public String value;
	SortBy(String value){
		this.value = value;
	}
	
	public static List<String> getAllValues(){
		List<String> list = new ArrayList<>();
		list.add(all.value);
		list.add(new_est.value);
		list.add(deadline.value);
		list.add(reminderOn.value);
		list.add(reminderOff.value);
		
		return list;
	}

	public static SortBy findByString(String strValue) {
		for (SortBy s : values()) {
			if(strValue.equals(s.value)) return s;
		}
		
		return null;
	}
}
