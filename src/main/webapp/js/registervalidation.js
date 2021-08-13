function validate()
{
	var firstname = document.getElementById("firstname").value;
	var lastname = document.getElementById("lastname").value;
	var email = document.getElementById("email").value;
	var password = document.getElementById("pass").value;
	var confirmpassword = document.getElementById("confirmpass").value;
	
	console.log(email);
	
	if(firstname.length===0)
	{
		alert("Please Enter your first name");
		return false;
	}
	
	if(lastname.length===0)
	{
		alert("Please Enter your last name");
		return false;
	}
	
	if(email.length===0)
	{
		alert("Please Enter your email");
		return false;
	}
	
	
	var regex =/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[#@$!%*?&])[A-Za-z\d#@$!%*?&]{8,}$/
	
	
	if(password.length===0)
	{
		alert("Please enter your password");
		return false;
	}
	
	if(!regex.test(password))
	{
		alert("Your password should contain atleast one uppercase, one lowercase and one symbol");
		return false;
	}
	
	if(password!==confirmpassword)
	{
		alert("Password and confirm password does not match")
		return false;
	}
	
	console.log("TRUE");
	return true;
	
}