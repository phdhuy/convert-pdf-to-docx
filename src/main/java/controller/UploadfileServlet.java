package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.bean.fileUpload;
import model.bo.FileBO;
import model.bo.UserBO;
import model.bo.impl.FileBOImpl;
import model.bo.impl.UserBOImpl;

@WebServlet("/UploadfileServlet")
@MultipartConfig(
    fileSizeThreshold = 2097152,
    maxFileSize = 52428800L,
    maxRequestSize = 524288000L
)
public class UploadfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private UserBO userBO;
    private FileBO fileBo;
    
    public UploadfileServlet() {
    	this.userBO = new UserBOImpl();
    	this.fileBo = new FileBOImpl();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<fileUpload> fileUploads = new ArrayList<>();
    	HttpSession session = request.getSession();
    	int userId = (int)session.getAttribute("id");
		
		
        if (request.getPart("files").getSize() != 0L) {
        	session = request.getSession();
        	fileBo.processUpload(userId,request);
        	request.getSession().setAttribute("message", "Upload successfull!");
        	fileUploads = fileBo.getAllMyFiles(userId);
    		request.setAttribute("fileUploads", fileUploads);
        	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);     
          }
        else {
        	request.getSession().setAttribute("message", "No file chosen");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
         }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
}
