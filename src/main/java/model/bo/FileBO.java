package model.bo;

import java.io.File;
import java.util.Optional;

import javax.servlet.http.Part;

import model.bean.fileUpload;

public interface FileBO {
	
	//Upload file 
	void processUpload();
	
	//Convert file
	void convertFiles();
	
	//Dowload file
    Optional<fileUpload> getFile(int fid);
    
}
