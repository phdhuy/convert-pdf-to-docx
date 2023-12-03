package model.dao;

import model.bean.User;

public interface UserDao extends Dao<User>{
	
	boolean checkLogin(String username, String password);
}
