package com.company.data;

import java.sql.Connection;
import java.sql.SQLException;

public enum DBConfig {
	INSTANCE;

    private Connection connection;

    private DBConfig()
    {
        connection = DB.makeConnection();
    }

    public static DBConfig getInstance()
    {
    	return INSTANCE;
    }

    public Connection getConnection()
    {
    	try {
			if(connection.isClosed())
			{
				connection = DB.makeConnection();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return connection;
    }

    
}
