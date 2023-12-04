package controller;

import java.io.IOException;
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
import model.bo.UserBO;
import model.bo.impl.UserBOImpl;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	UserBO userBO = new UserBOImpl();
	
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
        		response.sendRedirect("index.jsp");
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
