package ru.apache_maven.webserver;

import java.sql.SQLException;
import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.FragmentConfiguration;
import org.eclipse.jetty.webapp.MetaInfConfiguration;
import org.eclipse.jetty.webapp.TagLibConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;


/**
 * WebServer
 *
 */

public class WebServer
{
	static BDConnect bd;

    public static void main(String[] args) throws Exception
    {
       bd = new BDConnect();
       insertToBD();
       BDConnect.initDBConnection();
       BDConnect.getDataFromDB();
       go((Integer)BDConnect.data.get(0), (String)BDConnect.data.get(1), (String)BDConnect.data.get(4));
    }

    public static void insertToBD() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
    	BDConnect.initDBConnection();
    	bd.insertStatements(null, null);
    }

    private static Server server;
    public static final String CONTEXT_PATH = "/";

    public static void go(Integer port, String context_path, String app_path) throws Exception
    {
		server = new Server(port);
		WebAppContext context = new WebAppContext();

		context.setResourceBase(app_path);
		context.setDescriptor(app_path + "WEB-INF/web.xml");

		context.setConfigurations(new Configuration[] {
				new AnnotationConfiguration(), new WebXmlConfiguration(),
				new WebInfConfiguration(), new TagLibConfiguration(),
				new PlusConfiguration(), new MetaInfConfiguration(),
				new FragmentConfiguration(), new EnvConfiguration() });

		context.setContextPath(context_path);
		context.setParentLoaderPriority(true);
		server.setHandler(context);

        try {
			server.start();
	        server.join();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 throw new RuntimeException(e);
		}
    }

    public static synchronized void stopServerJetty() throws Exception
    {
//    	server.destroy();
    	server.stop();
    }

}