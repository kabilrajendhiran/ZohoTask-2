package com.company.user;

import com.company.data.AuthDatabaseContext;

import java.sql.SQLException;

public class UserManager {
    AuthDatabaseContext authDatabaseContext = new AuthDatabaseContext();

    public User getUser(String emailID)
    {
        User user = null;
        try {
          user = authDatabaseContext.getUser(emailID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public String getUserEmailIdFromTicketId(String ticketId)
    {
        String emailId = null;
        try {
           emailId = authDatabaseContext.getEmailId(ticketId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emailId;
    }

}
