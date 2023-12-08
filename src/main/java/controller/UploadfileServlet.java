package controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
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
    
    UserBO userBo = new UserBOImpl();
    FileBO fileBo = new FileBOImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getPart("files").getSize() != 0L) {
        	HttpSession session = request.getSession();
        	int userId = (int)session.getAttribute("id");
        	fileBo.processUpload(userId,request);
        	request.getSession().setAttribute("message", "Upload succed");
            response.sendRedirect("index.jsp");       
          }
        else {
        	request.getSession().setAttribute("message", "No file chosen");
        	response.sendRedirect("index.jsp");
         }
             
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         this.doPost(request, response);
    }
}
