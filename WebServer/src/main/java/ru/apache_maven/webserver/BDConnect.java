package ru.apache_maven.webserver;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class BDConnect {

	 private static final String DERBY_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";  // драйвер DERBY
	 private static final String DERBY_URL = "jdbc:derby:";   // URL Derby
	 private static final String DB_NAME = "SETTINGS";     //  БД
	 private static final String APPLICATION_PATH = "src" + File.separator + "main" + File.separator + "webapp";
	 private int SERVER_PORT;
	 private String PATH_TO_INDEX_HTML;
	 private static final String LOGIN = "123";
	 private static final String PASSWORD = "123";

	 private static Connection conn = null;
	 private static Statement st = null;
	 private static  ResultSet rs = null;

	/*
	 * initDBConnection() - создаем подключение к БД
	 */

	public void setPort(int port)
	{
		this.SERVER_PORT = port;
	}

	public void setPath(String path)
	{
		this.PATH_TO_INDEX_HTML = path;
	}

	public int getPort()
	{
		return SERVER_PORT;
	}

	public String getPath()
	{
		return PATH_TO_INDEX_HTML;
	}

	public static void initDBConnection() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException

	{
		Driver driverDerby = (Driver)Class.forName(DERBY_DRIVER).newInstance();

		DriverManager.registerDriver(driverDerby);

		conn = DriverManager.getConnection(DERBY_URL + DB_NAME + ";create=true");

		System.out.println("\n----------------------------------------------------") ;
		System.out.println("Соединение установлено. База данных " + DB_NAME + " успешно подключена...");
	}

	private static String TABLE = "CREATE TABLE PROPERTIES(id_record INTEGER, port INTEGER, path_to_index_html VARCHAR(10), login VARCHAR(6), pwd VARCHAR(6), app_path VARCHAR(50))";
	private static String INSERTQUERY = "insert into PROPERTIES values(?,?,?,?,?,?)";

	/*
	 * Добавление данных в таблицу
	 */

	public void insertStatements(String portOfServer, String pathContext)
	{
		try {
			  st = conn.createStatement();
			  DatabaseMetaData dbmd = conn.getMetaData();
			  rs = dbmd.getTables(null, null, "PROPERTIES", null);

			  if(!rs.next())
			  {
				  st.executeUpdate(TABLE);
				  System.out.println("Таблица " + "PROPERTIES" + " успешно создана...");
				  System.out.println();
			  } else  System.out.println("Таблица " + "PROPERTIES" + " уже была создана...");

			  if(portOfServer == null & pathContext == null)
			  {
			  setPort(8080);
			  setPath("/");
			  }

			  else
			  {
				  setPort (Integer.parseInt(portOfServer.replace("[", " ").replace("]", " ").trim()));

				  if(pathContext.replace("[", " ").replace("]", " ").trim().equals("/"))
				  {
					  setPath(pathContext.replace("[", " ").replace("]", " ").trim());
				  }
				  else
				  {
				  setPath("/" + pathContext.replace("[", " ").replace("]", " ").trim());
				  }
		      }

			  PreparedStatement insertQuery = conn.prepareStatement (INSERTQUERY);
			  if(!selectCounts())
			  {
			  insertQuery.setInt(1,1);
			  insertQuery.setInt(2, getPort());
			  insertQuery.setString(3, getPath());
			  insertQuery.setString(4, LOGIN);
			  insertQuery.setString(5, PASSWORD);
			  insertQuery.setString(6, APPLICATION_PATH);
			  insertQuery.executeUpdate();
			  }


			  System.out.println("Id PORT PATH Login  Password  App_path");

			  String query = "SELECT * FROM PROPERTIES";
			  rs = st.executeQuery(query);

			  while (rs.next()) {
				  	 int id = rs.getInt("id_record");
			         String serverPort = rs.getString("port");
			         String pathToHTML = rs.getString("path_to_index_html");
			         String login = rs.getString("login");
			         String pwd = rs.getString("pwd");
			         String app_path = rs.getString("app_path");
			         System.out.println(id + "  " + serverPort+ " " + pathToHTML + "  " + login + "  " + pwd  + "  " + app_path);
			      }


			  insertQuery.close();
			  st.close();
			  conn.close();

			  System.out.println("\n----------------------------------------------------") ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	/*
	 * Удаляем строки
	 */

	public static void deleteRows() throws SQLException

	{
		st = conn.createStatement();
		int count = st.executeUpdate("DELETE FROM PROPERTIES");
		System.out.println("Количество удаленных строк: " + count);
		st.close();
		conn.close();
	}


	public static void dropTable() throws SQLException
	{
		st = conn.createStatement();
        int count = st.executeUpdate("DROP TABLE PROPERTIES");

        System.out.println("Удаленных таблиц: " + count);
        conn.close();
	}

	public static boolean selectCounts() throws SQLException
	{
		String query = "SELECT id_record FROM PROPERTIES WHERE id_record = 1";
		st = conn.createStatement();
        rs = st.executeQuery(query);
        if(rs.next())
        {
        	return true;
        } else {return false;}
	}

	/*
	 * Получаю значения, содержащиеся в БД
	 */

	public static ArrayList<Object> data;

	public static ArrayList<Object> getDataFromDB() throws SQLException
	{

		String query = "SELECT * FROM PROPERTIES";
		data = new ArrayList<Object>();
		data.clear();
		st = conn.createStatement();
        rs = st.executeQuery(query);

        while (rs.next()) {
        	int serverPort = rs.getInt("port");
	          data.add(serverPort);
	        String pathToHTML = rs.getString("path_to_index_html");
	          data.add(pathToHTML);
		    String pwd = rs.getString("pwd");
		      data.add(pwd);
		    String login = rs.getString("login");
		      data.add(login);
			String app_path = rs.getString("app_path");
			  data.add(app_path);
	          return data;
	      }
        rs.close();
        conn.close();
        return data;
	}

}
