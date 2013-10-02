  <!doctype html public "-//w3c//dtd html 4.0 transitional//en">

  <%@ page contentType="text/html; charset=windows-1251" %>
  <%@ page import="java.util.*, java.text.*, ru.apache_maven.webserver.BDConnect" %>
  <html>
      <head>
          <title>Authentication page</title>
           <script>
	function submit_ (log, pas)
	{
	if (log.length != 0  & pas.length != 0) return true;
	else {alert ('ERROR'); return false}
	}
		</script>
          <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
     </head>
  <body bgcolor="white">
  <div style="margin:0 auto; width: 400px">
  <form method="post" action= <%=getFromBD()%>>
           <input name="login"
                  value="Enter your login"
                  onfocus="if (this.value == 'Enter your login') this.value = ''     "
                  onblur=" if (this.value == ''     ) this.value = 'Enter your login'"
           >
           <input name="password"
                  value="Enter your password"
                  onfocus="if (this.value == 'Enter your password') this.value = '',         this.type = 'password'"
                  onblur=" if (this.value == ''        ) this.value = 'Enter your password', this.type = 'text'    "
           >
           <input value="Enter"
           type="submit"
           onclick="return submit_ (this.form.login.value, this.form.password.value)">
  </form>
  </div>
  </body>
  </html>

  <%!
     String getFromBD()
     {
	  if(BDConnect.data.get(1).equals("/"))
	         {
		  return "/settings.jsp";
	         }
	         else
	         {
	        	 return BDConnect.data.get(1) + "/settings.jsp";
	         }
     }
  %>