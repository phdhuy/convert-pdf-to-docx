package controller;

import java.io.IOException;
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

/**
 * Servlet implementation class ConvertfileServlet
 */
@WebServlet("/ConvertfileServlet")
public class ConvertfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private FileBO fileBo;
    public ConvertfileServlet() {
        super();
        fileBo = new FileBOImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<fileUpload> fileUploads = fileBo.getAllMyFilesConverted((int) request.getSession().getAttribute("id"));
    	request.setAttribute("fileUploads", fileUploads);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("convert.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		    String fileName = request.getParameter("fileName");
		    int fileId = Integer.parseInt(request.getParameter("fileId"));
		    fileBo.pushFileToQueue(fileName, fileId);
        	request.getSession().setAttribute("message", "Converted files");
        	
        	doGet(request, response);
	}

}
