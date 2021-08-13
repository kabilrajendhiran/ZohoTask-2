<%@ page import="org.owasp.encoder.Encode" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script type="text/javascript" src="js/registervalidation.js"></script>
</head>
<body>

<div class="container">
    <div class="row mt-5">
        <div class="col"></div>
        <div class="col">

            <form action="Register" method="post" onsubmit="return validate();" >
                <label class="form-label">Enter First Name </label>
                <input class="form-control" type="text" id="firstname" name="firstname">

                <label class="form-label">Enter Last Name </label>
                <input class="form-control" type="text" id="lastname" name="lastname">

                <label class="form-label">Enter Email ID </label>
                <input class="form-control" type="text" id="email" name="email">

                <label class="form-label">Enter Password </label>
                <input class="form-control" type="password" id="pass" name="password">

                <label class="form-label">Enter Confirm Password </label>
                <input class="form-control" type="password" id="confirmpass" name="confirmpass">

                <div class="text-center mt-3">
                    <input type="submit" class="btn btn-success" value="Register">
                    <input type="button" class="btn btn-primary" onclick="window.location='login.jsp'" value="Login">
                </div>

            </form>

        </div>
        <div class="col"></div>
    </div>




<div>
    <%
        String name =(String)request.getAttribute("name");
        String msg = (String)request.getAttribute("msg");

        if(msg!=null)
        {
            out.write("<h4>"+ Encode.forHtml(name)+" "+msg+ "</h4>");
        }

        if(msg!=null && msg.equals("user registered successsfully"))
        {
            /*out.write("<a href='login.jsp'>Click to proceed</p>");*/
            out.write("<script> " +
                    "  setTimeout(function () { " +
                    " window.location.href = 'login.jsp'; " +
                    " }, 2000);" +
                    " </script>");

        }
    %>
</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous" ></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous" ></script>
</body>
</html>
