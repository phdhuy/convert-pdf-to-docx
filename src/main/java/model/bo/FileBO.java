package model.bo;

import java.io.File;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import model.bean.fileUpload;

public interface FileBO {
	
	//Upload file 
	void processUpload(int userId , HttpServletRequest req);
	
	//Convert file
	void convertFiles(String fileName ,int fileId );
	
	//Dowload file
    Optional<fileUpload> getFile(int fid);
    
}
