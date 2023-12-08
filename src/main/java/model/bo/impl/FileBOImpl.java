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
	public void processUpload() {
		try {
            Iterator var2 = this.request.getParts().iterator();

            while(var2.hasNext()) {
                Part part = (Part)var2.next();
                if (part.getName().equals("files_upload")) {
                    String filename = this.extractFileName(part);
                    filename = (new File(filename)).getName();

                    try {
                        File file = new File(this.getFolderUpload(), filename);
                        Files.copy(part.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        fileDao.upload(filename, this.user, 0);
                    } catch (Exception var5) {
                        fileDao.upload(filename, this.user, 1);
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
        return "";
    }
	
	public File getFolderUpload() {
        File folderUpload = new File(System.getProperty("user.home") + "/pdfs");
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }
    
    //Convert file
    
    @Override
    public void convertFiles() {
        List<fileUpload> files = fileDao.getListConverterFile(this.user);

        for (fileUpload file : files) {
            String filename = file.getFname().split("\\.")[0];

            try {
                convertFile(filename);
                fileDao.changeStatus(file.getFid(), 2);
            } catch (Exception e) {
                fileDao.changeStatus(file.getFid(), 3);
            }
        }
    }
    
	public void convertFile(String filename) throws Exception {
        PdfDocument pdf = new PdfDocument();
        pdf.loadFromFile(getFolderPath("pdfs").getAbsolutePath() + File.separator + filename + ".pdf");
        pdf.saveToFile(getFolderPath("docxs").getAbsolutePath() + File.separator + filename + ".docx", FileFormat.DOCX);
        pdf.close();
    }
    
	public File getFolderPath(String folder) {
        File folderUpload = new File(System.getProperty("user.home") + "/" + folder);
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }

        return folderUpload;
    }
     
	//Download file
	
	@Override
	public Optional<fileUpload> getFile(int fid) {
        return fileDao.getFile(fid);
	}
    
}
