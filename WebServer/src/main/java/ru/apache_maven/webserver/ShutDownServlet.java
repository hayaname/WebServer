package ru.apache_maven.webserver;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShutDownServlet extends HttpServlet {
	 /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	static Thread insertThread;
	static BDConnect bd = new BDConnect();
	 String port, path;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		 Map<String, String[]> mapGetParameters = request.getParameterMap();
		 port = Arrays.toString(mapGetParameters.get("port"));
		 path = Arrays.toString(mapGetParameters.get("path"));
		 request.setAttribute("port", port);
		 request.setAttribute("path", path);
		 request.getRequestDispatcher("/WEB-INF/shutdown.jsp").forward(request,response);

				try {
					BDConnect.initDBConnection();
					BDConnect.deleteRows();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					System.out.println("InstantiationException: " + e1.getMessage());
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					System.out.println("IllegalAccessException: " + e1.getMessage());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					System.out.println("ClassNotFoundException: " + e1.getMessage());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("SQLException: " + e1.getMessage());
				}

			 Thread thread = new Thread(new Runnable()
		        {
		            public void run()
		            {

		  		       try {
		  		    	insertToBD();
						WebServer.stopServerJetty();
						WebServer.main(null);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            }
		        });
			 thread.start();
    }

	public synchronized void insertToBD() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
    	BDConnect.initDBConnection();
    	bd.insertStatements(port, path);
	}
}
