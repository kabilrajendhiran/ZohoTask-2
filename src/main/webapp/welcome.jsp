<%@ page import="org.owasp.encoder.Encode" %>
<%@ page import="com.company.ticket.CookieManager" %>
<%@ page import="com.company.auth.AuthFilter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div>

    <%
        AuthFilter af = new AuthFilter();
        response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
        boolean flag = af.doFilter(request);

        /*Cookie[] cookies = request.getCookies();

        if(cookies!=null)
        {
            for (Cookie cookie: cookies) {
                if(cookie.getName().equals("auth") && !(cookie.getValue()==null || cookie.getValue().isEmpty()))
                {
                    if(cookieManager.validateAuthCookie(cookie))
                    {
                        flag = true;
                    }
                }
            }
        }*/

        if(!flag)
        {
            response.sendRedirect("login.jsp");
        }
    %>


    <div class="container">
        <div class="row">
            <div class="col">
                <form action="GetAllSessions" method="post">
                    <input class="btn btn-success" type="submit" value="Show Tickets">
                </form>
                <form action="Logout" method="get">
                    <input class="btn btn-danger" type="submit" value="Logout">
                </form>
            </div>
        </div>

    </div>
</div>




<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
