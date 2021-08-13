package com.company.data;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
	public static Connection makeConnection()
    {
		 String url="jdbc:mysql://localhost:3306/simplewebapp";
	     String username = "kabil";
	     String password = "kabil1998";
		
        Connection connection = null;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }	
}
