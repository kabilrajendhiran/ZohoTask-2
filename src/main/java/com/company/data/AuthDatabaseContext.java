package com.company.data;

import com.company.auth.Auth;
import com.company.helper.Utility;
import com.company.ticket.Ticket;
import com.company.ticket.TicketDTO;
import com.company.user.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AuthDatabaseContext {

	public String createTicket(String userEmailId) throws SQLException {
		Connection connection = DBConfig.INSTANCE.getConnection();

		String createTicketSql = "INSERT INTO ticket (`ticket_id`, `createdAt`, `validUpTo`, `user_email`) VALUES (?,?,?,?)";
		PreparedStatement createTicketStatement = connection.prepareStatement(createTicketSql);

		Ticket ticket = new Ticket();

		createTicketStatement.setString(1,ticket.getTicketId());
		createTicketStatement.setString(2,ticket.getCreatedAtAsString());
		createTicketStatement.setString(3,ticket.getValidUpToAsString());
		createTicketStatement.setString(4,userEmailId);


		createTicketStatement.execute();
		connection.close();

		return ticket.getTicketId();

	}

	public Ticket getTicket(String ticketId) throws SQLException {
		Connection connection = DBConfig.INSTANCE.getConnection();
		PreparedStatement stmt = connection.prepareStatement("select * from ticket where ticket_id=?;");
		stmt.setString(1,ticketId);

		ResultSet set = stmt.executeQuery();
		Ticket ticket = null;
		while (set.next())
		{
			String tid = set.getString(1);
			LocalDateTime ct = Utility.convertDateTime(set.getString(2));
			LocalDateTime vt = Utility.convertDateTime(set.getString(3));
			/*int userId = set.getInt(6);*/
			ticket =new Ticket(tid,ct,vt);

		}

		connection.close();

		return ticket;
	}


	public String register(String fn, String ln, String email, String pass) throws SQLException
	{
		Connection connection = DBConfig.INSTANCE.getConnection();
		
		
		if(checkUserExist(email))
		{
			return "User already exists";
		}
		
		PreparedStatement insertUserStmt = connection.prepareStatement("Insert into user (`firstname`,`lastname`,`email`,`passwordhash`) values(?,?,?,?)");
		insertUserStmt.setString(1, fn);
		insertUserStmt.setString(2, ln);
		insertUserStmt.setString(3, email);
		insertUserStmt.setString(4, pass);
		
		int res = insertUserStmt.executeUpdate();
		
		connection.close();
		if(res>0)
		{
			return "user registered successsfully";
		}
		else {
			return "user registeration failed";
		}

	}
	
	public String login(String email,String password) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException
	{
		if(!checkUserExist(email))
		{
			return "User not exists";
		}
		
		Connection connection = DBConfig.INSTANCE.getConnection();
		PreparedStatement getUserPasswordStmt = connection.prepareStatement("Select passwordhash from user where email=?");
		getUserPasswordStmt.setString(1, email);
		
		ResultSet rs = getUserPasswordStmt.executeQuery();
		
		String hashedpassword = "";
		while(rs.next())
		{
			hashedpassword = rs.getString(1);
		}
		
		System.out.println(hashedpassword);	
		
		connection.close();
		
		if(!Auth.validatePassword(password, hashedpassword))
		{
			return "Password incorrect";
		}
		
		return "Logged in succesfully";
		
	}

	public void removeTicket(String ticketID) throws SQLException
	{
		Connection connection = DBConfig.INSTANCE.getConnection();
		String sql = "delete from ticket where ticket_id=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1,ticketID);
		statement.execute();
		connection.close();
	}

	public void removeAllTickets(String emailId) throws SQLException
	{
		Connection connection = DBConfig.INSTANCE.getConnection();
		String sql = "delete from ticket where user_email=?;";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1,emailId);
		statement.execute();
		connection.close();
	}

	
	public boolean checkUserExist(String email)
	{
		Connection connection = DBConfig.INSTANCE.getConnection();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("Select firstname from user where email=?");
			stmt.setString(1, email);
			
			ResultSet rs = stmt.executeQuery();
			
			String existinguser=null;
			
			while(rs.next())
			{
				existinguser = rs.getString(1);
			}
			
			if(existinguser==null || existinguser.isEmpty())
			{
				return false;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public List<TicketDTO> getAllTickets(String emailId) throws SQLException
	{
		Connection connection = DBConfig.INSTANCE.getConnection();
		String sql = "select ticket_id, createdAt, validUpTo from ticket where user_email = ? order by createdAt;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1,emailId);

		ResultSet resultSet = preparedStatement.executeQuery();
		List<TicketDTO> arrayList = Collections.synchronizedList(new ArrayList<>());

		while (resultSet.next())
		{
			String tid = resultSet.getString(1);
			LocalDateTime createdAt = Utility.convertDateTime(resultSet.getString(2));
			LocalDateTime validUpTo = Utility.convertDateTime(resultSet.getString(3));
			arrayList.add(new TicketDTO(tid,createdAt,validUpTo,emailId));
		}
		resultSet.close();
		connection.close();
		return arrayList;
	}

	public String getEmailId(String ticketId) throws SQLException
	{
		Connection connection = DBConfig.INSTANCE.getConnection();
		String sql = "select user_email from ticket where ticket_id=?;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1,ticketId);
		ResultSet resultSet = preparedStatement.executeQuery();
		String email=null;
		while (resultSet.next()){
			email = resultSet.getString(1);
		}
		return email;
	}

	public User getUser(String emailId) throws SQLException
	{
		Connection connection = DBConfig.INSTANCE.getConnection();
		String sql = "select firstname, lastname, email from user where email=?;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1,emailId);

		ResultSet resultSet = preparedStatement.executeQuery();
		User user = null;
		while (resultSet.next())
		{
			String fname = resultSet.getString(1);
			String lname = resultSet.getString(2);
			String email = resultSet.getString(3);
			user = new User(fname,lname,email);
		}

		connection.close();
		return user;
	}

}
