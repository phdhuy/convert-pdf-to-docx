package model.dao;

import java.util.Optional;

import model.bean.User;

public interface UserDao extends Dao<User>{
	
	boolean checkLogin(String username, String password);
	
	boolean isValidUsername(String username);
	
	Optional<User> findByUsername(String username);
}
