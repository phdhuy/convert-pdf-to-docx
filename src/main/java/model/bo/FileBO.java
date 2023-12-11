package model.bo;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import model.bean.fileUpload;

public interface FileBO {
	
	//Upload file 
	void processUpload(int userId , HttpServletRequest req);
	
	//Convert file
	void convertFiles(String fileName ,int fileId );
	
	//Dowload file
    Optional<fileUpload> getFile(int fid);
   // Queue
    void pushFileToQueue(String fileName, int fileId);
    
    void checkFileInQueue();
    
    void startQueueChecking();
    
    List<fileUpload> getAllMyFiles(int userId);
    
    List<fileUpload> getAllMyFilesConverted(int userId);
    
    List<fileUpload> getFileFromQueue(int userId);
}
