package model.bo.impl;

import java.util.Optional;

import model.bean.User;
import model.bo.UserBO;
import model.dao.Dao;
import model.dao.impl.UserDAO;

public class UserBOImpl implements UserBO{
	
	Dao<User> userDao = new UserDAO();

	@Override
	public void createUser(String username, String password, String firstname, String lastname) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		
		userDao.save(user);
	}

	@Override
	public Optional<User> getUserById(int id) {
		Optional<User> user = userDao.get(id);
		return user;
	}
}
