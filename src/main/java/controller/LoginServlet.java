package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bo.UserBO;
import model.bo.impl.UserBOImpl;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	UserBO userBO = new UserBOImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(userBO.checklogin(username, password) == true) {
        	response.sendRedirect("inde.jsp");
        	
        }
        else {
			response.sendRedirect("login.jsp");
		}
    }
}
