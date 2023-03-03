package Controller;

import java.util.List;

public interface DAO<T>{
	
	List<T> getAll();
	
	boolean save(T newItem);
	
	boolean update(Object key, T newValue);
	
	void delete(T item);
}
