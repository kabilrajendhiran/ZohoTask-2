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
        response.sendRedirect("Home");
    }
%>

<div class="container">
    <div class="row">
        <div class="col"></div>
        <div class="col">

            <form action="${pageContext.request.contextPath}/Login" method="post" onsubmit="return validateLogin();">
                <label for="uname" class="form-label">User name</label>
                <input type="text" name="uname" id="uname" class="form-control">
                <label for="pass" class="form-label">Password</label>
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

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous" ></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous" ></script>
</body>
</html>
