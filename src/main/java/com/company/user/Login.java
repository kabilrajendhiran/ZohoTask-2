package com.company.user;

import com.company.ticket.TicketManager;
import com.company.data.AuthDatabaseContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	AuthDatabaseContext database = new AuthDatabaseContext();
	TicketManager ticketManager = new TicketManager();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		String email = request.getParameter("uname");
		String password = request.getParameter("pass");

		try {
			String res = database.login(email, password);
			if(res.equals("Logged in succesfully"))
			{
				Cookie cookie = new Cookie("auth", ticketManager.createTicket(email));
				cookie.setHttpOnly(true);
				response.addCookie(cookie);
				response.sendRedirect("Home");
				/*request.getRequestDispatcher("/Home").forward(request,response);*/
			}
			else {
				request.setAttribute("errormsg", res);
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.include(request, response);
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | SQLException | IOException e) {
			e.printStackTrace();
		}


	}

}
