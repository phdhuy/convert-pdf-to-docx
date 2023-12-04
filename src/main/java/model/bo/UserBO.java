package model.bo;

import java.util.Optional;

import model.bean.User;

public interface UserBO {
	
	void createUser(String username, String password, String firstname, String lastname);
	
	Optional<User> getUserById(int id);
	
	boolean checklogin(String username, String password);
	
	boolean isValidUsername(String username);
	
	Optional<User> getUserByUsername(String username);
}
