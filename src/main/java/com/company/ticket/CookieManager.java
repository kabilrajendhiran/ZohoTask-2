package com.company.ticket;

import com.company.user.UserManager;

import javax.servlet.http.Cookie;

public class CookieManager {

    TicketManager ticketManager = new TicketManager();
    UserManager userManager = new UserManager();

    // returns ticketID
    public String getAuthCookieValue(Cookie[] cookies)
    {
        for(Cookie ck : cookies)
        {
            if (ck.getName().equals("auth"))
            {
                return ck.getValue();
            }
        }
        return null;
    }

    public boolean validateAuthCookie(Cookie[] cookies)
    {
        String ticketID = getAuthCookieValue(cookies);
        if(ticketID==null)
        {
            return false;
        }
        else
        {
            Ticket ticket = ticketManager.getTicket(ticketID);
            if(ticket==null)
            {
                return false;
            }
            return ticketManager.validateTicket(ticket);
        }

    }

    public boolean validateAuthCookie(Cookie cookie)
    {
        boolean res;

        String ticketID = cookie.getValue();
        if(ticketID==null)
        {
            res = false;
        }
        else
        {
            Ticket ticket = ticketManager.getTicket(ticketID);
            if(ticket==null)
            {
                res = false;
            }
            else
            {
                res = ticketManager.validateTicket(ticket);
            }

        }

        if(!res)
        {
            ticketManager.removeTicket(ticketID);
        }

        return res;

    }

    public void getUserEmail(String ticketID)
    {
        Ticket ticket = ticketManager.getTicket(ticketID);
        if(ticket!=null)
        {

        }
    }


}
