package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.fileUpload;
import model.bo.FileBO;
import model.bo.impl.FileBOImpl;
@WebServlet("/HomepageServlet")
public class HomepageServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FileBO fileBO;
	
	public HomepageServlet() {
		fileBO = new FileBOImpl();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		List<fileUpload> fileUploads = new ArrayList<>();
		fileUploads = fileBO.getAllMyFiles((int) request.getSession().getAttribute("id"));
		request.setAttribute("fileUploads", fileUploads);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
	}
}
