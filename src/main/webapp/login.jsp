<%@ page import="com.company.ticket.CookieManager" %>
<%@ page import="com.company.auth.AuthFilter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <script type="text/javascript" src="js/loginvalidation.js"></script>
</head>
<body>
<%
    AuthFilter authFilter = new AuthFilter();
    boolean flag = authFilter.doFilter(request);
    if(flag)
    {
        response.sendRedirect("welcome.jsp");
    }
%>

<div class="container">
    <div class="row">
        <div class="col"></div>
        <div class="col">
            <form action="Login" method="post" onsubmit="return validateLogin();">
                <label class="form-label">User name</label>
                <input type="text" name="uname" id="uname" class="form-control">
                <label class="form-label">Password</label>
                <input type="password" name="pass" id="pass" class="form-control">
                <div class="text-center mt-2">
                    <input class="btn btn-success" type="submit" value="login">
                    <input class="btn btn-primary" type="button" onclick="window.location='register.jsp'" value="Register">
                </div>

            </form>

            <div>
                <h3>
                    <%
                        String errormsg =(String)request.getAttribute("errormsg");
                        if(errormsg!=null){
                            out.write(errormsg);
                        }
                    %>
                </h3>
            </div>
        </div>
        <div class="col"></div>
    </div>
</div>


</body>
</html>
