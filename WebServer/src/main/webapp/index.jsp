  <!doctype html public "-//w3c//dtd html 4.0 transitional//en">

  <%@ page contentType="text/html; charset=windows-1251" %>
  <%@ page import="java.util.*, java.text.*, ru.apache_maven.webserver.BDConnect" %>

<html>
<title>Authentication page</title>
</head>
<body bgcolor="white">
<form method="post" action= <%=getFromBD()%>>
<div style="margin:0 auto; width: 400px">
<h1>Приветствую Вас!</h1> <br>
<input value="Tap on me!" type="submit" style="width:500;height:75px">
</div>
</form>
</body>
</html>
 <%!
     String getFromBD()
     {
	  if(BDConnect.data.get(1).equals("/"))
	         {
		  return "/authentication.jsp";
	         }
	         else
	         {
	        	 return BDConnect.data.get(1) + "/authentication.jsp";
	         }
     }
  %>