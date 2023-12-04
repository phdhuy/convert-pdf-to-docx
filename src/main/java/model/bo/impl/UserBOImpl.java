package model.bo.impl;

import java.util.Optional;

import model.bean.User;
import model.bo.UserBO;
import model.dao.Dao;
import model.dao.UserDao;
import model.dao.impl.UserDAOImpl;

public class UserBOImpl implements UserBO{
	
	UserDao userDao = new UserDAOImpl();

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

	@Override
	public boolean checklogin(String username, String password) {
		// TODO Auto-generated method stub
		return userDao.checkLogin(username, password);
	}

	@Override
	public boolean isValidUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.isValidUsername(username);
	}

	@Override
	public Optional<User> getUserByUsername(String username) {
		// TODO Auto-generated method stub
		Optional<User> user = userDao.findByUsername(username);
		return user;
	}
}
