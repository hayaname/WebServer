  <!doctype html public "-//w3c//dtd html 4.0 transitional//en">

  <%@ page contentType="text/html; charset=windows-1251" %>
  <%@ page import="java.util.*, java.text.*, ru.apache_maven.webserver.BDConnect" %>
  <html>
      <head>
          <title>Shutdown page</title>
          <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
     </head>
  <body bgcolor="white">
  <div style="margin:0 auto; width: 400px">
  <% String port = (String)request.getAttribute("port"); %>
  <% String path = (String)request.getAttribute("path"); %>
  Server restarts, please refresh your page...<br>
  <b>Port</b>: <%=port%> <br>
  <b>Context path: <%=path%></b>
  </div>
  </body>
  </html>

