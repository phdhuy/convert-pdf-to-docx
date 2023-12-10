package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.BCrypt;
import model.bo.UserBO;
import model.bo.impl.UserBOImpl;
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserBO userBO;
	
	public RegisterServlet() {
		this.userBO = new UserBOImpl();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String plainPassword = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        
        if(userBO.isValidUsername(username)) { 
        	userBO.createUser(username, hashedPassword, firstname, lastname);
        	response.sendRedirect("login.jsp");
        }
        else {
        	String errorMessage = "Invalid username. Please choose a different username.";
            request.setAttribute("errorMessage", errorMessage);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
        }
    }
}
