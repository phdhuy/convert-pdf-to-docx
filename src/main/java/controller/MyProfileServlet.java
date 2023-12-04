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

import model.bean.User;
import model.bo.UserBO;
import model.bo.impl.UserBOImpl;
@WebServlet("/MyProfileServlet")
public class MyProfileServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
	
	UserBO userBO = new UserBOImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		doGet(request, response);
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object idAttribute = session.getAttribute("id");

		if (idAttribute != null && idAttribute instanceof Integer) {
		    int userId = (Integer) idAttribute;
		    User user = userBO.getUserById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
		    request.setAttribute("user", user);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
		    dispatcher.forward(request, response);
		} else {
		    response.sendRedirect("login.jsp");
		}

    }
}
