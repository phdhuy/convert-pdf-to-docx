package controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getPart("files").getSize() != 0L) {
        	request.getSession().setAttribute("message", "Upload successfully");
//            String username = request.getParameter("username");
//            User user = userBo.getUserByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));;
//            Thread t = new Thread((Runnable) new FileBOImpl(request, user));
//            t.start();
            
        }

//        response.sendRedirect("profile.jsp");
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         this.doPost(request, response);
    }
}
