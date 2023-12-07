package controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.fileUpload;
import model.bo.FileBO;
import model.bo.impl.FileBOImpl;

public class DowloadFileServlet {
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        int fid = 0;
            FileBO fileBo = new FileBOImpl();
	        try {
	            fid = Integer.parseInt(request.getParameter("fid"));
	        } catch (Exception e) {
	        }

	        if (fid != 0) {
	            Optional<fileUpload> file = fileBo.getFile(fid);
	            String fileName = null;
	            if(file.isPresent())
	            {
	                fileUpload fileUploadObject = file.get();
                    fileName = fileUploadObject.getFname();
	            }
	            String scrpath = this.GetFolderPath("docxs").getAbsolutePath() + File.separator + fileName;
	            Path path = Paths.get(scrpath);
	            byte[] data = Files.readAllBytes(path);
	            response.setContentType("application/octet-stream");
	            response.setHeader("Content-disposition", "attachment; filename=" + file.get());
	            response.setContentLength(data.length);
	            InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
	            OutputStream outStream = response.getOutputStream();
	            byte[] buffer = new byte[4096];
	            int bytesRead = 1;
	            
	            while((bytesRead = inputStream.read(buffer)) != -1) {
	                outStream.write(buffer, 0, bytesRead);
	            }

	            inputStream.close();
	            outStream.close();
	        } else {
	            request.getSession().setAttribute("message", "File không tồn tại hoặc đã bị xóa!");
	        }

	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    }

	    private File GetFolderPath(String folder) {
	        File folderUpload = new File(System.getProperty("user.home") + "/" + folder);
	        if (!folderUpload.exists()) {
	            folderUpload.mkdirs();
	        }

	        return folderUpload;
	    }
}
