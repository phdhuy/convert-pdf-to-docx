package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	FileBO fileBo = new FileBOImpl();
    public ConvertfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		    String fileName = request.getParameter("fileName");
		    int fileId = Integer.parseInt(request.getParameter("fileId"));
		    fileBo.convertFiles(fileName, fileId);
        	request.getSession().setAttribute("message", "Converted files");
            response.sendRedirect("convert.jsp");       
           
	}

}
