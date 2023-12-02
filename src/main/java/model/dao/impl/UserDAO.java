package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import common.ConnectDB;
import model.bean.User;
import model.dao.Dao;

public class UserDAO implements Dao<User> {
	
	Connection conn = null;
	Statement st = null;
	PreparedStatement preSt = null;

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		try (Connection conn = ConnectDB.getMySQLConnection();
	             PreparedStatement pstm = conn.prepareStatement("SELECT * FROM users")) {
				ResultSet rs = pstm.executeQuery();
				while (rs.next()) {
					int id = rs.getInt("id");
					String username = rs.getString("username");
					String password = rs.getString("password");
					String firstname = rs.getString("firstname");
					String lastname = rs.getString("lastname");
					
					User user = new User();
					user.setId(id);
					user.setUsername(username);
					user.setPassword(password);
					user.setFirstname(firstname);
					user.setLastname(lastname);
					users.add(user);
				}
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();	      
	        }
		return users;
	}

	@Override
    public Optional<User> get(int id) {
        try (Connection conn = ConnectDB.getMySQLConnection();
             PreparedStatement pstm = conn.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            pstm.setObject(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");

                User user = new User();
                user.setId(userId);
                user.setUsername(username);
                user.setPassword(password);
                user.setFirstname(firstname);
                user.setLastname(lastname);
                return Optional.of(user);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

	@Override
	public void save(User user) {
	    try (Connection conn = ConnectDB.getMySQLConnection();
	         PreparedStatement pstm = conn.prepareStatement("INSERT INTO users (username, password, firstname, lastname) VALUES (?, ?, ?, ?)")) {

	        pstm.setString(1, user.getUsername());
	        pstm.setString(2, user.getPassword());
	        pstm.setString(3, user.getFirstname());
	        pstm.setString(4, user.getLastname());

	        pstm.executeUpdate();

	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void update(User user) {
	    try (Connection conn = ConnectDB.getMySQLConnection();
	         PreparedStatement pstm = conn.prepareStatement("UPDATE users SET username=?, password=?, firstname=?, lastname=? WHERE id=?")) {

	        pstm.setString(1, user.getUsername());
	        pstm.setString(2, user.getPassword());
	        pstm.setString(3, user.getFirstname());
	        pstm.setString(4, user.getLastname());
	        pstm.setInt(5, user.getId()); 

	        pstm.executeUpdate();

	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void delete(User user) {
	    try (Connection conn = ConnectDB.getMySQLConnection();
	         PreparedStatement pstm = conn.prepareStatement("DELETE FROM users WHERE id=?")) {

	        pstm.setInt(1, user.getId()); 

	        pstm.executeUpdate();

	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	}
}
