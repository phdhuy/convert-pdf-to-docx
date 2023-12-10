package controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class UserCheckFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		httpServletRequest.setAttribute("origin", httpServletRequest.getRequestURI());

        if (!httpServletRequest.getRequestURI().contains("login") && httpServletRequest.getSession() == null) {
            forward(request, response, "login.jsp");
            return;
        }

        chain.doFilter(request, response);
	}
	
	private void forward(ServletRequest request, ServletResponse response, String path)
	        throws ServletException, IOException {
	    RequestDispatcher dispatcher = request.getRequestDispatcher(request.getServletContext().getContextPath() + "/" + path);
	    dispatcher.forward(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
