function validateLogin()
{
	var username =  document.getElementById("uname").value;
	var password = document.getElementById("pass").value;
	
	if(username.length == 0 || password.length == 0)
	{
		alert("Incorrect username or password");
		return false;
	}
	return true;
}