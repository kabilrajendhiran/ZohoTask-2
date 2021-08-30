package com.company.user;

import com.company.ticket.CookieManager;
import com.company.ticket.TicketManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class Logout extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	TicketManager ticketManager = new TicketManager();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CookieManager cookieManager = new CookieManager();

		String ticketID = cookieManager.getAuthCookieValue(request.getCookies());
		if(ticketID!=null)
		{
			ticketManager.removeTicket(ticketID);
		}

		response.addCookie(new Cookie("auth",""));
		response.sendRedirect("login.jsp");
	}


}
