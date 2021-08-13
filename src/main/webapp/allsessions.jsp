<%@ page import="com.company.auth.AuthFilter" %>
<%@ page import="java.util.List" %>
<%@ page import="com.company.ticket.TicketDTO" %>
<%@ page import="org.owasp.encoder.Encode" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<%
    AuthFilter af = new AuthFilter();
    response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
    boolean flag = af.doFilter(request);

    if(!flag)
    {
        response.sendRedirect("login.jsp");
    }
%>

<%--<form action='${pageContext.request.contextPath}/RemoveSession' method='post'>
    <input type='hidden' value=''>
    <input type='submit' value='LogOut'>
</form>--%>

<div class="container">
    <div class="row">
        <div class="col">
            <table class="table table-bordered">
                <thead class="table-dark">
                    <tr>
                        <th scope="col">Email Id</th>
                        <th scope="col">Created at</th>
                        <th scope="col">Valid Thru</th>
                        <th scope="col">Ticket Id</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>

                <tbody>
            <%


                List<TicketDTO> ticketDTOS =(List<TicketDTO>) request.getAttribute("tickets");
                for (TicketDTO tDto :ticketDTOS) {
                    out.write("<tr>");
                    out.write("<td>"+ Encode.forHtml(tDto.getEmailId())+"</td>");
                    out.write("<td>"+ Encode.forHtml(tDto.getCreatedAt().toString()) +"</td>");
                    out.write("<td>"+ Encode.forHtml(tDto.getValidUpto().toString()) +"</td>");
                    out.write("<td>"+ Encode.forHtml(tDto.getTicketId()) +"</td>");
                    out.write("<td>");
                    out.write("<form action='RemoveSession' method='post'>\n" +
                            "    <input type='hidden' name='ticketId' value='"+ Encode.forHtml(tDto.getTicketId()) +"'>\n" +
                            "    <input class='btn btn-danger' type='submit' value='LogOut'>\n" +
                            "</form>\n");
                    out.write("</td>");
                    out.write("</tr>");

                }
            %>
                </tbody>
            </table>

        </div>
    </div>



</div>



</body>
</html>
