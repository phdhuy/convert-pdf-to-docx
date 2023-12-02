package model.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Dao<T> {
	
	List<T> getAll();
	
	Optional<T> get(int id);
	
	void save(T t);
	
	void update(T t);
	
	void delete(T t);
}
