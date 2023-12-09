package model.bo.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;


import model.bean.User;
import model.bean.fileUpload;
import model.bo.FileBO;
import model.dao.FileDao;
import model.dao.impl.FileDaoImpl;

import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;

public class FileBOImpl implements FileBO {
	private HttpServletRequest request;
    private User user;
    
    public FileBOImpl() {};
    
    FileDao fileDao = new FileDaoImpl();
    
    //Upload file
    
    public FileBOImpl(HttpServletRequest request, User user){
        this.request = request;
        this.user = user;
    }
	@Override
	public void processUpload(int userId, HttpServletRequest req) {
		try {
            Iterator iterator = req.getParts().iterator();
            String folderPath = "pdfs";
            while(iterator.hasNext()) {
                Part part = (Part)iterator.next();
                if (part.getName().equals("files")) {
                    String filename = this.extractFileName(part);
                    filename = (new File(filename)).getName();
                    try {
                    	 File folderUpload = new File(System.getProperty("user.home")+ "/" + folderPath);
                         if (!folderUpload.exists()) {
                             folderUpload.mkdirs();
                         }
                        File file = new File(folderUpload, filename);
                        Files.copy(part.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        fileDao.upload(filename, userId, 0);
                    } catch (Exception e) {
                        fileDao.upload(filename, userId, 2);
                    }
                }
            }
        } catch (Exception e) {
        }

	}
	
	public String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return null;
    }
    //Convert file
    
    @Override
	public void convertFiles(String filename, int fileId ) {
    	String fileName = filename.split("\\.")[0];
    	try {
           PdfDocument pdf = new PdfDocument();
           String folderUpl = "pdfs";
           String folderDown = "docx";
           File folderUpload = new File(System.getProperty("user.home") + "/"  + folderUpl);
           File folderDownload = new File(System.getProperty("user.home")+ "/"  + folderDown);

//           System.out.print(folderUpload);
           if (!folderUpload.exists()) {
               folderUpload.mkdirs();
           }
           pdf.loadFromFile(folderUpload.getAbsolutePath() + File.separator + fileName + ".pdf");
           pdf.saveToFile(folderDownload.getAbsolutePath() + File.separator + fileName + ".docx", FileFormat.DOCX);
           pdf.close();
           fileDao.changeStatus(fileId, 1);
    	} catch (Exception e) {
           
    	}
    }
    

	//Download file
	
	@Override
	public Optional<fileUpload> getFile(int fid) {
        return fileDao.getFile(fid);
	}
    
}
