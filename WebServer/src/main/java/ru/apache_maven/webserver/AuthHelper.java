package ru.apache_maven.webserver;

    class AuthHelper {

	public static boolean auth(String login, String password)
	{
		if(login.equals("[" + BDConnect.data.get(3) + "]") & password.equals("[" + BDConnect.data.get(2) + "]"))
		{
			return true;
		}
		return false;
	}
}
