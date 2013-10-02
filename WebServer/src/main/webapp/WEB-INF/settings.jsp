  <!doctype html public "-//w3c//dtd html 4.0 transitional//en">

  <%@ page contentType="text/html; charset=windows-1251" %>
  <%@ page import="java.util.*, java.text.*, ru.apache_maven.webserver.BDConnect" %>
  <html>
      <head>
          <title>Settings page</title>
           <script>
	function submit_ (portServer, pathToContext)
	{
	if (portServer.length != 0 & pathToContext.length != 0) return true;
	else {alert ('ERROR: input variables are should not be empty'); return false}
	}
		</script>
          <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
     </head>
  <body bgcolor="white">
    <form method="post" action= <%=getFromBD()%>>
    <b>Please, input server settings:</b><br>
    <b>Server port:</b>
           <input name="port"
                  type="text"
           ><br>
           <b>Set path (format: / or xxx):</b>
           <input name="path"
				  type="text"
           ><br>
           <input value="Run"
           type="submit"
           onclick="return submit_ (this.form.port.value, this.form.path.value)">
  </form>

  </body>
  </html>

  <%!
     String getFromBD()
     {
	  if(BDConnect.data.get(1).equals("/"))
	         {
		  return "/shutdown.jsp";
	         }
	         else
	         {
	        	 return BDConnect.data.get(1) + "/shutdown.jsp";
	         }
     }
  %>