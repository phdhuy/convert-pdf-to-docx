package controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import model.bo.FileBO;
import model.bo.impl.FileBOImpl;
@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener{
	
	private FileBO fileBO;
	
	public ServletContextListener() {
		this.fileBO = new FileBOImpl();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		fileBO.startQueueChecking();
	}
}
