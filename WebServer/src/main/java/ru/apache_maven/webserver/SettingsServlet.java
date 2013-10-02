package ru.apache_maven.webserver;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SettingsServlet extends HttpServlet {
	 /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	 {
		Map<String, String[]> mapGetParameters = request.getParameterMap();
			 if(AuthHelper.auth(Arrays.toString(mapGetParameters.get("login")), Arrays.toString(mapGetParameters.get("password"))))
			 {
				 request.getRequestDispatcher("/WEB-INF/settings.jsp").forward(request,response);
			 }
			 else
			 {
				 response.setContentType("text/html");
		         response.sendRedirect("authentication.jsp");
			 }
			}
	 }

