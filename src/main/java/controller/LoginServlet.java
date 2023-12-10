package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.BCrypt;
import model.bean.User;
import model.bean.fileUpload;
import model.bo.FileBO;
import model.bo.UserBO;
import model.bo.impl.FileBOImpl;
import model.bo.impl.UserBOImpl;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserBO userBO;
	private FileBO fileBO;
	
	public LoginServlet() {
		userBO = new UserBOImpl();
		fileBO = new FileBOImpl();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        Optional<User> user = userBO.getUserByUsername(username);
        if(!user.isEmpty()) {
        	if(BCrypt.checkpw(password, user.get().getPassword())) {
        		HttpSession session = request.getSession();
        		session.setAttribute("id", user.get().getId());
        		session.setAttribute("us", user.get().getUsername());
        		
        		List<fileUpload> fileUploads = new ArrayList<>();
        		fileUploads = fileBO.getAllMyFiles(user.get().getId());
        		request.setAttribute("fileUploads", fileUploads);
        		
        		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
        	}
        	else {
        		String errorMessage = "Wrong password!";
                request.setAttribute("errorMessage", errorMessage);
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
        	}
        }
        else {
        	String errorMessage = "Wrong username!";
            request.setAttribute("errorMessage", errorMessage);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
