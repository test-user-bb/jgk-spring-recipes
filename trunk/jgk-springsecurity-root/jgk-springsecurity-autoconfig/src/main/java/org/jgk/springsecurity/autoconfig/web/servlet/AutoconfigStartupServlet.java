package org.jgk.springsecurity.autoconfig.web.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class AutoconfigStartupServlet
 */
@WebServlet(description = "Does startup things", urlPatterns = { "/AutoconfigStartupServlet" }, loadOnStartup=1)
public class AutoconfigStartupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutoconfigStartupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("AutoconfigStartupServlet");
	}

}
