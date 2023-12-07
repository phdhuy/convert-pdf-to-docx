package model.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import common.ConnectDB;
import model.bean.User;
import model.bean.fileUpload;
import model.dao.FileDao;

public class FileDaoImpl implements FileDao  {
	
    
	//Upload file
	@Override
	public  void upload(String fileName, User user , int fileStatus )
	{ 
		try {
		Connection conn = ConnectDB.getMySQLConnection();
        Statement stmt = (Statement)conn.createStatement();
        String sql = "";
        if (!isFileExist(fileName)) {
            sql = "INSERT INTO `uploadfiles`(`userId`, `fileName`, `fileStatus`) VALUES (" + user.getId() + ",'" + fileName + "'," + fileStatus + ")";
        } else {
            sql = "UPDATE uploadfiles set fileStatus = " + fileStatus + " where fileName = " + fileName;
        }

        stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isFileExist(String fileName) {
		 try {
			Connection conn = ConnectDB.getMySQLConnection();
	        Statement stmt = (Statement)conn.createStatement();
	        String sql = "SELECT * FROM uploadfiles WHERE fileName = '" + fileName + "'";
	        ResultSet rs = stmt.executeQuery(sql);
	        return rs.next();
		} catch (Exception e) {
			return false;
			// TODO Auto-generated catch block
		}
         
		
	}
    
	//Convert file
	@Override
	public List<fileUpload> getListConverterFile(User user) {
		List<fileUpload> files = new ArrayList();

        try {
            Connection conn = ConnectDB.getMySQLConnection();
            Statement stmt = (Statement)conn.createStatement();
            String sql = "select * from uploadfiles where userId = " + user.getId() + " and fileStatus = " + 0;
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
            	fileUpload file = new fileUpload();
                int fileId = rs.getInt("fileId");
                int userId = rs.getInt("userId");
                String fileName = rs.getString("fileName");
                int fileStatus = rs.getInt("fileStatus");
                file.setFid(fileId);
                file.setUid(userId);
                file.setFname(fileName);
                file.setFstatus(fileStatus);
                files.add(file);
            }

            return files;
        } catch (Exception e) {
            return null;
        }
	}

	@Override
	public void changeStatus(int fileId, int fileStatus) {
		try {
            Connection conn = ConnectDB.getMySQLConnection();
            Statement stmt = (Statement)conn.createStatement();
            String sql = "UPDATE uploadfiles set fileStatus = " + fileStatus + " where fileId = " + fileId;
            stmt.execute(sql);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
	}
	
    //Dowload File
	@Override
	public Optional<fileUpload> getFile(int fId) {
		fileUpload file = new fileUpload();

        try {
            Connection conn = ConnectDB.getMySQLConnection();
            Statement stmt = (Statement)conn.createStatement();
            String sql = "SELECT * FROM uploadfiles where fileId = " + fId;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                int fileId = rs.getInt("fileId");
                int userId = rs.getInt("userId");
                String fileName = rs.getString("fileName").split("\\.")[0] + ".docx";
                int fileStatus = 2;
                file.setFid(fileId);
                file.setUid(userId);
                file.setFname(fileName);
                file.setFstatus(fileStatus);
                return Optional.of(file);
            }

        } catch (Exception e) {
        	e.printStackTrace();
        }
        return Optional.empty();
	}
}
