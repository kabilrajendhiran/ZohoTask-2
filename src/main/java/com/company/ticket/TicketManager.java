package com.company.ticket;

import com.company.data.AuthDatabaseContext;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class TicketManager {
    private final AuthDatabaseContext dbContext = new AuthDatabaseContext();

    public Ticket getTicket(String ticketId){
        Ticket ticket = null;
        try {
            ticket = dbContext.getTicket(ticketId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticket;
    }

    public String createTicket(String userEmailId)
    {
        try {
            return dbContext.createTicket(userEmailId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean validateTicket(Ticket ticket){
        Ticket ticket1=null;
        try {
            ticket1 = dbContext.getTicket(ticket.getTicketId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LocalDateTime dateTime = ticket.getValidUpto();
        return dateTime.isAfter(LocalDateTime.now()) && ticket1!=null;
    }



    public void removeTicket(String tickedId)
    {
        try {
            dbContext.removeTicket(tickedId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeAllTicket(String emailId)
    {
        try {
            dbContext.removeAllTickets(emailId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TicketDTO> getAllTickets(String emailID)
    {
        try {
            return dbContext.getAllTickets(emailID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




}
