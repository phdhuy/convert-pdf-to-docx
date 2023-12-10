package model.dao;
 
import java.util.List;
import java.util.Optional;

import model.bean.*;
 
public interface FileDao {
	//upload file 
	void upload(String fileName,int userId , int status);
	
	boolean isFileExist(String fileName , int userId);
	
	//convert file 
//	List<fileUpload> getListConverterFile(User user);
	
	void changeStatus(int fileId, int fileStatus);
	
	//dowload file
	Optional<fileUpload> getFile(int fileId);
	
	List<fileUpload> getAllMyFiles(int userId);
	
	List<fileUpload> getAllMyFilesConverted(int userId);
}
