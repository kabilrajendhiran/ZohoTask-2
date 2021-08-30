package com.company.core;

import com.company.auth.AuthFilter;
import com.company.ticket.CookieManager;
import com.company.ticket.TicketManager;
import com.company.user.UserManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetAllSession extends HttpServlet {
    AuthFilter authFilter = new AuthFilter();
    TicketManager ticketManager = new TicketManager();
    CookieManager cookieManager = new CookieManager();
    UserManager userManager = new UserManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!authFilter.doFilter(req)){
           resp.sendRedirect("login.jsp");
           return;
        }

        String tickedID = cookieManager.getAuthCookieValue(req.getCookies());
        String email = userManager.getUserEmailIdFromTicketId(tickedID);
        req.setAttribute("tickets",ticketManager.getAllTickets(email));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("allsessions.jsp");
        requestDispatcher.forward(req,resp);
    }
}
