package controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.fileUpload;
import model.bo.FileBO;
import model.bo.impl.FileBOImpl;

/**
 * Servlet implementation class DownloadFileServlet
 */
@WebServlet("/DownloadFileServlet")
public class DownloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int fileId = 0;
		FileBO fileBo = new FileBOImpl();
		try {
		    fileId = Integer.parseInt(request.getParameter("fileId"));
		} catch (Exception e) {
		}

		Optional<fileUpload> file = fileBo.getFile(fileId);

		if (file.isPresent()) {
		    fileUpload fileUploadObject = file.get();
		    String fileName = fileUploadObject.getFname();
		    String scrpath = this.GetFolderPath("docx").getAbsolutePath()+ File.separator + fileName;

		    File fileToSend = new File(scrpath);
		    
		    if (fileToSend.exists()) {
		        response.setContentType("application/octet-stream");
		        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
		        response.setContentLength((int) fileToSend.length());

		        try (InputStream inputStream = new FileInputStream(fileToSend);
		             OutputStream outStream = response.getOutputStream()) {

		            byte[] buffer = new byte[4096];
		            int bytesRead;
		            
		            while ((bytesRead = inputStream.read(buffer)) != -1) {
		                outStream.write(buffer, 0, bytesRead);
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    } else {
		        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		    }
		} else {
		    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}

        	
     }
	
	    private File GetFolderPath(String folderName) {
            File folderUpload = new File(System.getProperty("user.home") + "/"+ folderName);
            if (!folderUpload.exists()) {
                folderUpload.mkdirs();
            }

            return folderUpload;
     }

}
