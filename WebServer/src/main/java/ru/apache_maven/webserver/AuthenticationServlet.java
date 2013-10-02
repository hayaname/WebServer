package ru.apache_maven.webserver;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationServlet extends HttpServlet {
	 /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
     {
		request.getRequestDispatcher("/WEB-INF/authentication.jsp").forward(request,response);
	 }

	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {
		 request.getRequestDispatcher("/WEB-INF/authentication.jsp").forward(request,response);
      }
		}
