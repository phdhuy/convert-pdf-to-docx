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
 * Servlet implementation class PushFileIntoQueue
 */
@WebServlet("/GetFileFromQueueServlet")
public class GetFileFromQueueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private FileBO fileBo;

    public GetFileFromQueueServlet() {
        super();
        fileBo = new FileBOImpl();

        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<fileUpload> fileUploads = fileBo.getFileFromQueue((int) request.getSession().getAttribute("id"));
    	request.setAttribute("fileInQueue", fileUploads);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("Queue.jsp");
        dispatcher.forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
