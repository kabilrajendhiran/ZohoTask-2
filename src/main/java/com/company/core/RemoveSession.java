package com.company.core;

import com.company.auth.AuthFilter;
import com.company.ticket.TicketManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RemoveSession extends HttpServlet {
    AuthFilter authFilter = new AuthFilter();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!authFilter.doFilter(req))
        {
            return;
        }

        String ticketId = req.getParameter("ticketId");

        TicketManager ticketManager = new TicketManager();
        ticketManager.removeTicket(ticketId);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("GetAllSessions");
        requestDispatcher.forward(req,resp);

    }
}
