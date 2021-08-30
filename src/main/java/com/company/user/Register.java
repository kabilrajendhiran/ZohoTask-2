package com.company.user;

import com.company.auth.Auth;
import com.company.data.AuthDatabaseContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;



public class Register extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	AuthDatabaseContext database = new AuthDatabaseContext();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("register.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String emailId = request.getParameter("email");
		String password = request.getParameter("password");

		String hashedPassword = Auth.generateStorngPasswordHash(password);
		String res= "";
		
		try {
			res = database.register(firstname,lastname,emailId,hashedPassword);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("msg", res);
		request.setAttribute("name", firstname);
		RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
		dispatcher.include(request, response);
	}

}
