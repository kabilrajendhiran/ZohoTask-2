package com.company.auth;

import com.company.ticket.CookieManager;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class AuthFilter {

    CookieManager cookieManager = new CookieManager();
    public boolean doFilter(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();

        if(cookies!=null)
        {
            for (Cookie cookie: cookies) {
                if(cookie.getName().equals("auth") && !(cookie.getValue()==null || cookie.getValue().isEmpty()))
                {
                    if(cookieManager.validateAuthCookie(cookie))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
