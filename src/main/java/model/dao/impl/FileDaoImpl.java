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
	public  void upload(String fileName, int userId , int fileStatus )
	{ 
		try {
		Connection conn = ConnectDB.getMySQLConnection();        
        if (!isFileExist(fileName ,userId)) {
        	PreparedStatement pstm = conn.prepareStatement("INSERT INTO uploadfiles (userId, fileName, fileStatus) VALUES (?, ?, ?)");
        	pstm.setInt(1,userId);
        	pstm.setString(2,fileName);
        	pstm.setInt(3,fileStatus);
        	pstm.executeUpdate();
        } else {
        	PreparedStatement pstm = conn.prepareStatement("UPDATE uploadfiles set fileStatus = ? where fileName = ?");
        	pstm.setInt(1, fileStatus);
        	pstm.setString(2, fileName);
            pstm.executeUpdate();
        }

        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isFileExist(String fileName, int userId) {
		 try {
			Connection conn = ConnectDB.getMySQLConnection();
	        
			PreparedStatement pstm = conn.prepareStatement("SELECT * FROM uploadfiles WHERE fileName = ? and userId = ?");
			pstm.setString(1,fileName);
			pstm.setInt(2,userId);
			pstm.executeUpdate();
			ResultSet rs = pstm.executeQuery();
			
	        return rs.next();
		} catch (Exception e) {
			return false;
			// TODO Auto-generated catch block
		}
         
		
	}
    
	@Override
	public void changeStatus(int fileId, int fileStatus) {
		try {
            Connection conn = ConnectDB.getMySQLConnection();
			PreparedStatement pstm = conn.prepareStatement("UPDATE uploadfiles set fileStatus = ? where fileId = ?");
            pstm.setInt(1,fileStatus);
            pstm.setInt(2, fileId);
			pstm.executeUpdate();
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
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM uploadfiles where fileId = ?");
            pstm.setInt(1, fId);
            ResultSet rs = pstm.executeQuery();
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
